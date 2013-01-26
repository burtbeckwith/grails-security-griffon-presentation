package com.burtbeckwith.griffon

import griffon.transform.Threading
import griffon.util.GriffonExceptionHandler
import groovy.ui.SystemOutputInterceptor

import javax.swing.text.AttributeSet

import org.codehaus.groovy.control.ErrorCollector
import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.codehaus.groovy.control.messages.ExceptionMessage
import org.codehaus.groovy.control.messages.SyntaxErrorMessage
import org.codehaus.groovy.syntax.SyntaxException

/**
 * Based on griffon.plugins.slideware.GroovyCodeEditorController.
 *
 * @author Andres Almiray
 * @author Burt Beckwith
 */
class GroovyConsoleController {

	def model
	def view

	protected GroovyShell shell = new GroovyShell()
	protected int scriptCount = 0
	protected int maxOutputChars = 20000
	protected SystemOutputInterceptor systemOutInterceptor

	void mvcGroupInit(Map<String, Object> args) {
		systemOutInterceptor = new SystemOutputInterceptor({ String str ->
			appendOutput(str, model.styles.outputStyle)
			false
		})
	}

	def runAction = { evt = null ->
		if (!model.code) return

		String scriptText = model.code
		clearOutput()
		systemOutInterceptor.start()
		try {
			def result = shell.run(scriptText, "Script"+(scriptCount++), [])
			finishNormal(result)
		}
		catch (Throwable t) {
			finishException t
		}
		finally {
			systemOutInterceptor.stop()
		}
	}

	@Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
	protected void finishNormal(result) {
		ensureNoDocLengthOverflow model.editorDocument
	}

	@Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
	protected void finishException(t) {
		if (t instanceof MultipleCompilationErrorsException) {
			MultipleCompilationErrorsException mcee = t
			ErrorCollector collector = mcee.errorCollector
			int count = collector.errorCount
			appendOutputNl("${count} compilation error${count > 1 ? 's' : ''}:\n\n", model.styles.commandStyle)

			collector.errors.each { error ->
				if (error instanceof SyntaxErrorMessage) {
					SyntaxException se = error.cause
					int errorLine = se.line
					String message = se.originalMessage
					def doc = model.outputDocument
					doc.insertString(doc.length, message + " at ", model.styles.stacktraceStyle)
					doc.insertString(doc.length, "line: ${se.line}, column: ${se.column}\n\n", model.styles.stacktraceStyle)
				}
				else if (error instanceof Throwable) {
					reportException(error)
				}
				else if (error instanceof ExceptionMessage) {
					reportException(error.cause)
				}
			}
		}
		else {
			reportException(t)
		}
	}

	protected void reportException(Throwable t) {
		appendOutputNl("Exception thrown: ", model.styles.commandStyle)
		appendOutput(t.message + '\n', model.styles.stacktraceStyle)

		StringWriter sw = new StringWriter()
		new PrintWriter(sw).withWriter {pw -> GriffonExceptionHandler.sanitize(t).printStackTrace(pw) }
		appendStacktrace("\n${sw.buffer}\n")
	}

	// Append a string to the output area
	protected void appendOutput(String text, AttributeSet style) {
		def doc = model.outputDocument
		doc.insertString(doc.length, text, style)
		ensureNoDocLengthOverflow(doc)
	}

	protected void appendOutput(Object object, AttributeSet style) {
		appendOutput(object.toString(), style)
	}

	protected void appendStacktrace(text) {
		def doc = model.outputDocument

		// split lines by new line separator
		def lines = text.split(/(\n|\r|\r\n|\u0085|\u2028|\u2029)/)

		// Java Identifier regex
		def ji = /([\p{Alnum}_\$][\p{Alnum}_\$]*)/

		// stacktrace line regex
		def stacktracePattern = /\tat $ji(\.$ji)+\((($ji(\.(java|groovy))?):(\d+))\)/

		lines.each { line ->
			int initialLength = doc.length
			doc.insertString(initialLength, line + '\n', model.styles.stacktraceStyle)
		}

		ensureNoDocLengthOverflow(doc)
	}

	// Append a string to the output area on a new line
	protected void appendOutputNl(text, style) {
		def doc = model.outputDocument
		def len = doc.length
		if (len > 0 && doc.getText(len - 1, 1) != "\n") {
			appendOutput("\n", style)
		}
		appendOutput(text, style)
	}

	protected void clearOutput() {
		if (model.outputDocument.length) {
			model.outputDocument.remove 0, model.outputDocument.length
		}
	}

	protected void ensureNoDocLengthOverflow(doc) {
		if (doc.length > maxOutputChars) {
			doc.remove(0, doc.length - maxOutputChars)
		}
	}
}

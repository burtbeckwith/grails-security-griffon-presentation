package com.burtbeckwith.griffon

import griffon.plugins.slideware.EditorStyles

import java.awt.Color
import java.awt.Font

import javax.swing.JSplitPane
import javax.swing.text.Style
import javax.swing.text.StyleContext
import javax.swing.text.StyledDocument

import org.fife.ui.rsyntaxtextarea.SyntaxConstants

/**
 * Based on griffon.plugins.slideware.GroovyCodeEditorView.
 *
 * @author Andres Almiray
 * @author Burt Beckwith
 */

modifyFont = { target, sizeFilter, sizeMod ->
	def currentFont = target.font
	if (sizeFilter(currentFont.size)) return
	target.font = currentFont.deriveFont((currentFont.size + sizeMod) as float)
}

action(id: 'runAction',
       keyStroke: shortcut('ENTER'),
       enabled: bind { model.editable },
       closure: controller.runAction)

action(id: 'increaseFontAction',
       closure: {modifyFont(it.source, {it > 40}, +2)})

action(id: 'decreaseFontAction',
       closure: {modifyFont(it.source, {it < 5}, -2)})

splitPane(id: 'consolePanel', dividerLocation: 300, orientation: JSplitPane.VERTICAL_SPLIT) {

	rtextScrollPane(id: 'groovyEditorContainer') {
		def rtext = rsyntaxTextArea(id: 'groovyEditor', editable: bind { model.editable },
			syntaxEditingStyle: SyntaxConstants.SYNTAX_STYLE_GROOVY,
			tabSize: 3,
			text: bind('code', source: model, mutual: true),
			cssClass: 'codeEditor') {
				action(runAction)
			}
			model.editorDocument = rtext.document

		keyStrokeAction(
			component: groovyEditor,
			keyStroke: shortcut('shift L'),
			condition: 'in focused window',
			action: increaseFontAction)

		keyStrokeAction(
			component: groovyEditor,
			keyStroke: shortcut('shift S'),
			condition: 'in focused window',
			action: decreaseFontAction)
	}

	scrollPane(id: 'outputWindow') {
		outputArea = textPane(
			editable: false,
			name: 'outputArea',
			contentType: 'text/html',
			background: new Color(255,255,218),
			font: new Font('Monospaced', Font.PLAIN, 18),
			border: emptyBorder(4)
		)

		noparent {
			StyledDocument doc = outputArea.styledDocument
			model.outputDocument = doc

			Style defStyle = StyleContext.defaultStyleContext.getStyle(StyleContext.DEFAULT_STYLE)
			def applyStyle = { Style style, values -> values.each {k, v -> style.addAttribute(k, v)} }

			def styles = EditorStyles.getPlatformStyles()
			model.styles.regular = doc.addStyle('regular', defStyle)
			applyStyle(model.styles.regular, styles.regular)

			model.styles.promptStyle = doc.addStyle('prompt', model.styles.regular)
			applyStyle(model.styles.promptStyle, styles.prompt)

			model.styles.commandStyle = doc.addStyle('command', model.styles.regular)
			applyStyle(model.styles.commandStyle, styles.command)

			model.styles.outputStyle = doc.addStyle('output', model.styles.regular)
			applyStyle(model.styles.outputStyle, styles.output)

			model.styles.resultStyle = doc.addStyle('result', model.styles.regular)
			applyStyle(model.styles.resultStyle, styles.result)

			model.styles.stacktraceStyle = doc.addStyle('stacktrace', model.styles.regular)
			applyStyle(model.styles.stacktraceStyle, styles.stacktrace)
		}
	}
}

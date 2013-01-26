package com.burtbeckwith.griffon

import groovy.beans.Bindable

/**
 * Based on griffon.plugins.slideware.GroovyCodeEditorModel.
 *
 * @author Andres Almiray
 * @author Burt Beckwith
 */
class GroovyConsoleModel {

	@Bindable String code = ''
	@Bindable boolean editable = false

	def editorDocument
	def outputDocument
	Map styles = [:]
}

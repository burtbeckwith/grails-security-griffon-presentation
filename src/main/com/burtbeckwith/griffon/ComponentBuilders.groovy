package com.burtbeckwith.griffon

import griffon.plugins.slideware.BackgroundPanel

import java.awt.Color
import java.awt.Image

import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JLabel

class ComponentBuilders {

	def createHeader = { str ->
		panel opaque: false
	}

	def createFooter = { idx ->
		def footer
		noparent {
			if (idx) {
				footer = panel {
					migLayout(layoutConstraints: 'fill', columnConstraints: '2%[left]2%[left][][right]2%', rowConstraints: '[center]')
					label Utils.config.footer.left, cssClass: 'footer'
					label icon: imageIcon('/images/cc-sa-88x31.png'), cssClass: 'footer'
					label Utils.config.footer.right, cssClass: 'footer'
					label idx.toString(), cssClass: 'footer'
				}
			}
			else {
				footer = panel(new BackgroundPanel(), backgroundPaint: Color.WHITE)
			}
		}
		footer
	}

	def bullet = { Map params = [:], String text ->
		def lines = text.readLines()
		if (lines.size() > 1) {
			def noicon = params.remove('noicon')
			lines.eachWithIndex { String line, int i ->
				if (noicon == null) params.noicon = (i > 0)
				_bullet params, line
			}
		}
		else {
			_bullet params, text
		}
	}

	def _bullet = { Map params = [:], String text ->
		String cssClass = params.remove('cssClass')
		def noicon = params.remove('noicon')
		String paramsName = params.remove('iconUrl')

		String iconName = noicon ? Utils.config.noIconName : (paramsName ?: Utils.config.iconName)
		Map attrs = [cssClass: cssClass ?: 'bullet', border: emptyBorder(20, 0, 10, 0), constraints: 'wrap', icon: imageIcon('/images/' + iconName)]
		label([*:attrs, *:params], text)
	}

	def bullet2 = { Map params = [:], String text ->
		bullet([indent: 3] + params, text) // TODO actually indent
	}

	def createEditor = { Map params ->
		doCreateEditor params, 'GroovyCodeEditor', 'groovyEditorContainer'
	}

	def createSplitEditor = { Map params ->
		doCreateEditor params, 'GroovyConsole', 'consolePanel'
	}

	def doCreateEditor = { Map params, String groupName, String widgetName ->
		def editorWidget
		def defaults = [editable: true]
		String editorId = params.remove('id') ?: 'editor' + System.currentTimeMillis()
		String code = params.remove('code')
		noparent {
			def merged = defaults + params
			def (m, v, c) = controller.createMVCGroup([attrs: merged], groupName, editorId)
			m.code = code
			m.editable = merged.editable // TODO why is this necessary?
			editorWidget = v."$widgetName"
		}
		editorWidget
	}

	def headerLabel = { Map params = [:], String text ->
		boolean noBorder = (params.noBorder instanceof Boolean) && params.noBorder
		if (noBorder) {
			label text, cssClass: 'header', constraints: 'wrap', border: emptyBorder(0, 0, 20, 0)
		}
		else {
			label text, cssClass: 'header', constraints: 'wrap', border: emptyBorder(60, 0, 20, 0)
		}
	}

	def subheader = { Map params = [:], String text ->
		bullet([noicon: true, cssClass: 'subheader', border: emptyBorder(20, 0, 0, 0)], "<html><u>$text</u>")
		blankLine()
	}

	def link = { Map params = [:], String text ->
		bullet([cssClass: 'link'] + params, text)
	}

	def blankLine = { int howMany = 1 ->
		howMany.times {
			bullet ' ', noicon: true
		}
	}

	def defaultLayout = { ->
		migLayout layoutConstraints: '', columnConstraints: '5%[left]5%', rowConstraints: ''
	}

	def imageLabel = { Map params = [:], String url ->
		Icon icon
		if (params.maxHeight) {
			int maxHeight = params.remove('maxHeight')
			int height = params.remove('height')
			int width = params.remove('width')
			Image image = Utils.loadImage("images/$url").getScaledInstance(
				(int)(maxHeight / (double)height * width), maxHeight, Image.SCALE_SMOOTH)
			icon = new ImageIcon(image)
		}
		else {
			icon = imageIcon("/images/$url")
		}
		String text = params.remove('text')
		def attrs = params + [icon: icon, constraints: params.constraints ?: 'wrap']
		if (text) {
			attrs.cssClass = attrs.cssClass ?: 'bullet'
			label attrs, ' ' + text
		}
		else {
			label attrs
		}
	}

	def code = { String text ->
		panel(background: Utils.fromColorName(Utils.config.css.codeBackgroundColor), constraints: 'wrap', border: emptyBorder(12)) {
			migLayout layoutConstraints: '', columnConstraints: '[left]5%', rowConstraints: ''
			text.readLines().eachWithIndex { String line, int i ->
				label line, cssClass: 'code', constraints: 'wrap', border: emptyBorder(0, 0, 0, 20)
			}
		}
	}

	def highlightBullets = { bullets ->

		boolean lastIsBlank = bullets && !bullets[-1].trim()

		bullets = (bullets instanceof CharSequence) ? [bullets] : bullets

		bullets.eachWithIndex { String text, int i ->
			List lines
			if (!text.trim()) {
				lines = ['']
			}
			else {
				lines = text.readLines().collect { it.trim() }
			}

			if (!lines) {
				// TODO
				return
			}

			String cssClass = 'greyed'
			String iconUrl = 'bullet_black-greyed-32x32.png'

			if (i == bullets.size() - 1 || (lastIsBlank && i == bullets.size() - 2)) {
				cssClass = 'bullet'
				iconUrl = null
			}

			if (lines.size() == 1) {
				if (lines[0].trim()) {
					bullet lines[0], cssClass: cssClass, iconUrl: iconUrl
				}
				else {
					blankLine()
				}
			}
			else {
				lines.eachWithIndex { String line, int j ->
					if (line.trim()) {
						if (j == 0) {
							bullet line, cssClass: cssClass, iconUrl: iconUrl
						}
						else {
							bullet line, cssClass: cssClass, iconUrl: iconUrl, noicon: true
						}
					}
					else {
						blankLine()
					}
				}
			}
		}
	}
}

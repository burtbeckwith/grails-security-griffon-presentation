package com.burtbeckwith.griffon

import griffon.plugins.slideware.Slide

import com.lowagie.text.Document
import com.lowagie.text.Image
import com.lowagie.text.Rectangle
import com.lowagie.text.pdf.PdfWriter

class DeckLauncherController extends griffon.plugins.slideware.DeckLauncherController {

	def playAction = { evt = null ->
		execInsideUISync { model.busy = true }
		try {
			app.event('LaunchDeckStart')
			def (m, v, c) = createMVCGroup('DeckPlayer')
			app.windowManager.hide('deckLauncherWindow')
			app.event('LaunchDeckEnd')
			c.show()
		}
		finally {
			execInsideUIAsync { model.busy = false }
		}
	}

	def printAction = { evt = null ->
		execInsideUISync { model.busy = true }
		def (m, v, c) = createMVCGroup('DeckPlayer')

		Map settings = [
			fullScreen: app.config.presentation.fullScreen,
			screenWidth: app.config.presentation.screenWidth,
			screenHeight: app.config.presentation.screenHeight
		]

		try {
			int width = 1024
			int height = 768

			v.deck.layout.skipTransitions = true
			app.config.presentation.fullScreen = false
			app.config.presentation.screenWidth = width
			app.config.presentation.screenHeight = height
			c.show()

			Rectangle size = new Rectangle(width, height)
			Document document = new Document(size, 0f, 0f, 0f, 0f)
			PdfWriter.getInstance(document, new FileOutputStream(app.config.presentation.fileName))
			document.open()
			(0..<v.deck.size()).each { i ->
				Slide slide = v.deck[i]
				def imageSet
				execInsideUISync {imageSet = slide.takeSnapshot() }
				imageSet.each { image ->
					Image img = Image.getInstance(image, null)
					img.setDpi(600i, 600i)
					img.setXYRatio(2.5f)
					document.add(img)
				}
				for(action in v.slideActions[i]) {
					def print = true
					execInsideUISync {
						if (action.maximumNumberOfParameters == 2) {
							print = action.call(false, true)
						} else {
							print = action.call(false)
						}
					}
					if (print == null || print) {
						imageSet = null
						execInsideUISync {imageSet = slide.takeSnapshot() }
						imageSet.each { image ->
							Image img = Image.getInstance(image, null)
							img.setDpi(600i, 600i)
							img.setXYRatio(2.5f)
							document.add(img)
						}
					}
				}
				execInsideUISync {
					v.deck.layout.next(v.deck)
				}
			}
			document.close()
		}
		finally {
			app.config.presentation.fullScreen = settings.fullScreen
			app.config.presentation.screenWidth = settings.screenWidth
			app.config.presentation.screenHeight = settings.screenHeight
			c.hide()
			execInsideUIAsync { model.busy = false }
		}
	}
}

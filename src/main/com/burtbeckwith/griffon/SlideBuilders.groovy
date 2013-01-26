package com.burtbeckwith.griffon

import griffon.plugins.slideware.Slide

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics

class SlideBuilders {

	protected ComponentBuilders componentBuilders

	SlideBuilders(ComponentBuilders componentBuilders) {
		this.componentBuilders = componentBuilders
	}

	def imageSlide = { Map params = [:], String key, Closure body = null ->
		slide(footer: componentBuilders.createFooter(0), backgroundPainter: BackgroundPainters.backgroundImagePainter.curry(key, params),
				transition: params.transition ?: Transitions.defaultTransition) {
			migLayout layoutConstraints: 'fill', columnConstraints: '[center]', rowConstraints: '[center]'
			String source = Utils.config.images[key].source
			if (source) {
				int x = 50
				int y = Utils.config.presentation.screenHeight - 50
				label source, constraints: "pos $x $y".toString(), cssClass: 'imageSource'
			}
			if (body) body()
		}
	}

	def bulletSlide = { Map params = [:], String header, Closure bullets ->

		slide(title: '', backgroundPainter: BackgroundPainters.defaultBackgroundPainter, transition: params.transition ?: Transitions.defaultTransition) {
			componentBuilders.defaultLayout()

			header.readLines().eachWithIndex { String headerLine, int i ->
				boolean noBorder = (i > 0)
				componentBuilders.headerLabel headerLine, noBorder: noBorder
			}

			panel(opaque: false, constraints: 'grow') {
				borderLayout()
				vbox(constraints: BorderLayout.CENTER) {
					bullets()
				}
			}
		}
	}

	def centeredSlide = { Map params = [:], Closure body ->
		slide(title: '', backgroundPainter: BackgroundPainters.defaultBackgroundPainter, transition: params.transition ?: Transitions.defaultTransition) {
			migLayout layoutConstraints: 'fill', columnConstraints: '[center]', rowConstraints: '[center]'
			body()
		}
	}

//	def introSlide = { Closure body ->
//		slide(footer: componentBuilders.createFooter(0), backgroundPainter: BackgroundPainters.defaultBackgroundPainter, transition: Transitions.scaleOutTransition) {
//			migLayout(layoutConstraints: 'fill', columnConstraints: '5%[left]5%', rowConstraints: '40%[top]5%[bottom]5%[bottom]2%[bottom]10%')
//			body()
//		}
//	}

	def colorSlide = { Map params = [:], Color background ->
		def colorSlidePainter = { Slide slide, Graphics g ->
			BackgroundPainters.paintBackground slide, g, background
		}

		slide(footer: componentBuilders.createFooter(0), backgroundPainter: colorSlidePainter,
		      transition: params.transition ?: Transitions.defaultTransition)
	}
}

package com.burtbeckwith.griffon

import griffon.plugins.slideware.GraphicsUtil
import griffon.plugins.slideware.Slide

import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Rectangle

class BackgroundPainters {

	static backgroundPainter = { Slide slide, Graphics g ->
		paintBackground slide, g, Utils.config.slideBackgroundColor ?: Color.WHITE
	}

	static gradientBackgroundPainter = { Slide slide, Graphics g ->
		def bounds = g.clipBounds
		int height = (4 * bounds.height) / 3
		def paint = g.paint
		g.paint = new GradientPaint(0, 0, Color.GRAY, 0, height as float, Color.LIGHT_GRAY.brighter())
		g.fillRect 0, 0, bounds.@width, height
		g.paint = paint
	}

	static backgroundImagePainter = { String imageKey, Map params, Slide slide, Graphics g ->

		def images = Utils.config.images
		boolean scaledWidth = params.scaled == 'width' || !params.scaled
		boolean scaledHeight = params.scaled == 'height' || !params.scaled
		Color background = params.background ?: Color.BLACK

		BackgroundPainters.paintBackground slide, g, background
		g.fillRect bounds.@x, bounds.@y, bounds.@width, bounds.@height
		Rectangle b = GraphicsUtil.scaleBounds(images[imageKey].width, images[imageKey].height, new Rectangle(slide.bounds))

		def (int x, int y, int width, int height) = calculateBounds(scaledWidth, scaledHeight, slide, b, images, imageKey)
		g.drawImage Utils.loadImage("images/${images[imageKey].url}"), x, y, width, height, background, slide
	}

//	static defaultBackgroundPainter = gradientBackgroundPainter
	static defaultBackgroundPainter = backgroundPainter

	private static calculateBounds(boolean scaledWidth, boolean scaledHeight, Slide slide, Rectangle b, images, String imageKey) {
		int x
		int y
		int width
		int height

		if (scaledWidth && scaledHeight) {
			x = 0
			y = 0
			width = slide.width
			height = slide.height
		}
		else if (!scaledWidth && !scaledHeight) {
			x = b.x
			y = b.y
			width = images[imageKey].width
			height = images[imageKey].height
		}
		else if (scaledWidth) {
			double ratio = (double)slide.width / images[imageKey].width
			x = 0
			y = b.y * (1 / ratio)
			width = slide.width
			height = images[imageKey].height * ratio
		}
		else { // scaledHeight
			double ratio = (double)slide.height / images[imageKey].height
			x = b.x * (1 / ratio)
			y = 0
			width = images[imageKey].width * ratio
			height = slide.height
		}

		[x, y, width, height]
	}

	static void paintBackground(Slide slide, Graphics g, Color color) {
		Rectangle bounds = slide.bounds
		g.clearRect bounds.@x, bounds.@y, bounds.@width, bounds.@height
		g.color = color
		g.fillRect bounds.@x, bounds.@y, bounds.@width, bounds.@height
	}
}

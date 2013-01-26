package com.burtbeckwith.griffon

import griffon.util.ApplicationHolder

import java.awt.Color
import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import com.feature50.clarity.css.CSSUtils

class Utils {

	static ConfigObject getConfig() {
		ApplicationHolder.application.config
	}

	static BufferedImage loadImage(String path) {
		ImageIO.read Thread.currentThread().contextClassLoader.getResource(path)
	}

	static Color fromColorName(String name) {
		for (int i = 0; i < CSSUtils.COLOR_NAMES.length; ++i) {
			if (CSSUtils.COLOR_NAMES[i].equalsIgnoreCase(name)) {
				return Color.decode('0x' + CSSUtils.COLOR_VALUES[i][1..-1])
			}
		}
	}
}

package com.burtbeckwith.griffon;

import java.awt.Dimension;

import com.bric.image.transition.ImageInstruction;
import com.bric.image.transition.Transition2D;
import com.bric.image.transition.Transition2DInstruction;

/**
 * Transition that just draws the new slide.
 *
 * @author Burt Beckwith
 */
public class NoEffectTransition extends Transition2D {

	@Override
	public Transition2DInstruction[] getInstructions(float progress, Dimension size) {
		return new Transition2DInstruction[] { new ImageInstruction(false) };
	}
}

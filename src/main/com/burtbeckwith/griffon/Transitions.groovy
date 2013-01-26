package com.burtbeckwith.griffon

import com.bric.image.transition.Transition2D
import com.bric.image.transition.spunk.CollapseTransition2D
import com.bric.image.transition.spunk.GooTransition2D
import com.bric.image.transition.spunk.TossTransition2D
import com.bric.image.transition.spunk.ZoomTransition2D
import com.bric.image.transition.vanilla.BlendTransition2D
import com.bric.image.transition.vanilla.CurtainTransition2D
import com.bric.image.transition.vanilla.DropTransition2D
import com.bric.image.transition.vanilla.PushTransition2D
import com.bric.image.transition.vanilla.ScaleTransition2D
import com.burtbeckwith.griffon.NoEffectTransition

class Transitions {
	static Transition2D blendTransition     = new BlendTransition2D()
	static Transition2D collapseTransition  = new CollapseTransition2D()
	static Transition2D curtainTransition   = new CurtainTransition2D()
	static Transition2D dropTransition      = new DropTransition2D()
	static Transition2D gooTransition       = new GooTransition2D()
	static Transition2D noEffectTransition  = new NoEffectTransition()
	static Transition2D pushLeftTransition  = new PushTransition2D(Transition2D.LEFT)
	static Transition2D pushRightTransition = new PushTransition2D(Transition2D.RIGHT)
	static Transition2D scaleOutTransition  = new ScaleTransition2D(Transition2D.OUT) // good for last 1 or 2
	static Transition2D tossLeftTransition  = new TossTransition2D(Transition2D.LEFT)
	static Transition2D tossRightTransition = new TossTransition2D(Transition2D.RIGHT) // good for last 1 or 2
	static Transition2D zoomRightTransition = new ZoomTransition2D(Transition2D.RIGHT)

	static Transition2D defaultTransition = noEffectTransition
}
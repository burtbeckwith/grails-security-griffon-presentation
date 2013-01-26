import net.miginfocom.swing.MigLayout

import com.burtbeckwith.griffon.Transitions

slide(layout: new MigLayout('fill', '[center]', '[bottom]5%[top]'), transition: Transitions.tossLeftTransition) {
	label 'This presentation made with', constraints: 'wrap', cssClass: 'header'
	label icon: imageIcon('images/griffon-banner.png')
}

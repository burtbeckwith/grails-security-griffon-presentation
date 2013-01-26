import java.awt.BorderLayout
import java.awt.Color

import com.burtbeckwith.griffon.Transitions
import com.burtbeckwith.griffon.Utils

/*
introSlide {
//	label icon: imageIcon('/images/grails-logo.png'), constraints: 'center, wrap'

	imageLabel 'ggx.gif', constraints: 'pos 50 50'

	label Utils.config.title, cssClass: 'H1', constraints: 'wrap'
	label Utils.config.author, cssClass: 'H2', constraints: 'wrap'
	label Utils.config.company, cssClass: 'H2', constraints: 'wrap'
	label Utils.config.authorEmail, cssClass: 'H4', foreground: Color.BLUE, constraints: 'wrap'//, border: emptyBorder(12)
}
*/

slide(footer: createFooter(0), backgroundPainter: backgroundPainter, transition: Transitions.curtainTransition) {
	migLayout(layoutConstraints: 'fill', columnConstraints: '5%[left]5%', rowConstraints: '40%[top]5%[bottom]5%[bottom]2%[bottom]10%')

	panel(opaque: false, constraints: 'grow') {
		borderLayout()
		vbox(constraints: BorderLayout.CENTER) {
			label Utils.config.title, cssClass: 'H1', constraints: 'wrap', border: emptyBorder(20)
			label Utils.config.author, cssClass: 'H2', constraints: 'wrap', border: emptyBorder(20)
			label Utils.config.company, cssClass: 'H2', constraints: 'wrap', border: emptyBorder(20)
		}
		panel(constraints: BorderLayout.EAST, opaque: false) {
			migLayout columnConstraints: '2%[center]2%', rowConstraints: '2%[top]5%[top]5%'
			label icon: imageIcon('/images/ggx.gif'), border: emptyBorder(10), constraints: 'wrap'
			label icon: imageIcon('/images/grails-logo.png'), border: emptyBorder(10), constraints: 'wrap'
		}
	}
}

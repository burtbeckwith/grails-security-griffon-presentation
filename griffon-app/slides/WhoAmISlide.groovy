import java.awt.BorderLayout

import com.burtbeckwith.griffon.Transitions

bulletSlide 'Who Am I', transition: Transitions.collapseTransition, {
	panel(opaque: false, constraints: 'grow') {
		borderLayout()
		vbox(constraints: BorderLayout.CENTER) {
			bullet 'Java developer for over 13 years'
			bullet 'Background in Spring, Hibernate,\nSpring Security'
			bullet 'Grails developer for 5 years'
			bullet 'SpringSource employee on\nthe Grails team'
			bullet '50+ Grails plugins'
		}
		panel(constraints: BorderLayout.EAST, opaque: false) {
			migLayout columnConstraints: '2%[center]2%', rowConstraints: '2%[top]5%[top]5%'
			label icon: imageIcon('/images/grails.png'), border: emptyBorder(10), constraints: 'wrap'
			label icon: imageIcon('/images/programming-grails-small.gif'), border: emptyBorder(10)
		}
	}
}

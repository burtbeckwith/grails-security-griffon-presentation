import com.burtbeckwith.griffon.Transitions

centeredSlide transition: Transitions.blendTransition, {

	label 'Thank you!', cssClass: 'header', constraints: 'wrap'

	imageLabel 'programming-grails.png'

	link '@burtbeckwith', noicon: true
	link 'http://burtbeckwith.com/blog/', noicon: true
}

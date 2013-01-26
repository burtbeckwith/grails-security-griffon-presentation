import com.burtbeckwith.griffon.Utils

bulletSlide 'A3: Broken Authentication\n    and Session Management', {

	bullet '''Don't "roll your own"'''

	panel(background: Utils.config.slideBackgroundColor, border: emptyBorder(20)) {
		migLayout layoutConstraints: 'fill', columnConstraints: '[center]', rowConstraints: '[center]'
		imageLabel 'roll-marijuana-joint-pot-drugs.gif', constraints: 'center'
	}
}

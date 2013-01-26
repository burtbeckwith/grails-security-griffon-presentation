import com.burtbeckwith.griffon.Utils

bulletSlide 'A2: Cross-Site Scripting (XSS)', {

	bullet '"search" for "<script>alert(Boo!)</script>"'

	blankLine()

	code '''
<div id='query'>
Your search: "<script>alert('Boo!')</script>"

</div>
'''

	panel(background: Utils.config.slideBackgroundColor, border: emptyBorder(20)) {
		migLayout layoutConstraints: 'fill', columnConstraints: '[center]', rowConstraints: '[center]'
		imageLabel 'boo.png', constraints: 'center'
	}
}

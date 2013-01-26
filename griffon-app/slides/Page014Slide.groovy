bulletSlide 'A2: Cross-Site Scripting (XSS)', {

	subheader 'Remedies'

	code 'grails.views.default.codec = "html"'

	blankLine()

	code '''
<div id='query'>
Your search: "&lt;script&gt;alert('Boo!')&lt;/script&gt;"
</div>
'''
}

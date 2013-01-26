bulletSlide 'A10: Unvalidated Redirects and Forwards', {
	bullet "Don't do this:"
	link "/some/url?nextPage=/user/home", noicon: true
	bullet "Easy to misuse:"
	link "/some/url?nextPage=http://othersite.com/...", noicon: true
}

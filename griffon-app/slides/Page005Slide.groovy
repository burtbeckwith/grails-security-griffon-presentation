bulletSlide 'OWASP: A1-Injection', {
	bullet 'username = "master_of_disaster" is ok'
	bullet '''but username = "' or '1'='1" will generate'''
	code '''select * from person where username ='' or '1'='1' '''
}

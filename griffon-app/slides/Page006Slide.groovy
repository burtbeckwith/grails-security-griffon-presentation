bulletSlide 'OWASP: A1-Injection', {
	bullet ''' '; drop table users; -- '''
	bullet 'or', noicon: true
	bullet ''' '; truncate table users; -- '''
	bullet "You'll be scrambling to restore the database\nfrom the most recent backup"
}

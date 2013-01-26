bulletSlide 'OWASP: A1-Injection', {
	bullet 'SQL injection is the most common type'
	bullet 'Grails applications are largely immune'
	bullet 'Dynamically generated SQL/HQL query\nwithout properly escaping user input'
	code '''
String sql = " +
   select * from person where username ='" +
   params.username + "'"
'''
}

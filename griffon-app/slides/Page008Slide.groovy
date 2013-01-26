bulletSlide 'OWASP: A1-Injection', {
	bullet 'Likewise for HQL'

	code '''
Person.executeQuery(
   'from Person where username=?',
   [params.username])
'''

	bullet 'or', noicon: true

	code '''
Person.executeQuery(
   'from Person where username=:username',
   [username: params.username])
'''
}

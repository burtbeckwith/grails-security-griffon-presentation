bulletSlide 'OWASP: A1-Injection', {
	bullet 'Use PreparedStatement with placeholders'
	code '''
String sql = "select * from person " +
             "where username = ?"
PreparedStatement ps = conn.prepareStatement(sql)
ps.setString(1, params.username)
'''
}

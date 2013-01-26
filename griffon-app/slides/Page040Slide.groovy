bulletSlide 'A9: Insufficient Transport Layer Protection', {
	bullet 'Can encrypt traffic between servers'
	bullet 'e.g. MySQL allows SSL'
	code '''
GRANT SELECT, INSERT, UPDATE, DELETE
ON database_name.* TO username@servername
IDENTIFIED BY 'the password' REQUIRE SSL;
'''
}

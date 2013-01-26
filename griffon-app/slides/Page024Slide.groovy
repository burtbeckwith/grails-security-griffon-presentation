bulletSlide 'A4: Insecure Direct Object References', {

	bullet 'Or use ACLs'

	code '''
@PreAuthorize("hasPermission(
   #id, 'com.yourapp.CreditCard', read)")
CreditCard getCard(long id) {'
   CreditCard.get(id)'
}
'''
}

bulletSlide 'A4: Insecure Direct Object References', {

	bullet "Do this instead"

	code '''
def userId = ... // retrieve from auth
def user = User.load(userId)
def card = CreditCard.findByIdAndOwner(
	     params.id as Long, user)
'''
}

import java.awt.Color

log4j = {
	appenders {
		console name: 'stdout', layout: pattern(conversionPattern: '%d [%t] %-5p %c - %m%n')
	}

	error 'org.codehaus.griffon'

	info 'griffon.util',
	     'griffon.core',
	     'griffon.swing',
	     'griffon.app'
}

i18n.provider = 'i18n-support'
i18n.basename = 'messages'



def pad = { int i -> i < 10 ? "00$i" : i < 100 ? "0$i" : i.toString() }

presentation {
	fullscreen = true
	screenWidth = 1024
	screenHeight = 768
	fileName = 'GrailsSecurity.pdf'
	order = ['Intro', 'WhoAmI'] + (1..51).collect { "Page${pad(it)}" } + ['BadGuy', 'Duck', 'Griffon', 'QA', 'ThankYou', 'Last']
}

//lookandfeel.lookAndFeel = 'System'
//lookandfeel.theme = 'Nimbus'

lookandfeel.lookAndFeel = 'Substance'
lookandfeel.theme = 'EmeraldDusk'

title = 'Grails Security'
author = 'Burt Beckwith'
company = 'SpringSource'
authorEmail = 'burt@burtbeckwith.com'
footer.left = 'Greach 2.0 2013'
footer.right = 'Burt Beckwith - Grails Security'
slideBackgroundColor = new Color(240, 240, 240)

iconName = 'bullet_grails-32x32.png' // bullet_black-32x32.png
noIconName = 'no-icon-32x32.png'

/*
Colors have to be one of "aliceBlue", "antiqueWhite", "aqua", "aquamarine", "azure", "bakersChocolate", "beige", "bisque", "black", "blanchedAlmond", "blue", "blueViolet", "brass", "brightGold", "bronze", "brown", "burlyWood", "cadetBlue", "chartreuse", "chocolate", "coolCopper", "copper", "coral", "cornflowerBlue", "cornsilk", "crimson", "cyan", "darkBlue", "darkBrown", "darkCyan", "darkGoldenRod", "darkGray", "darkGreen", "darkGreenCopper", "darkKhaki", "darkMagenta", "darkOliveGreen", "darkOrange", "darkOrchid", "darkPurple", "darkRed", "darkSalmon", "darkSeaGreen", "darkSlateBlue", "darkSlateGray", "darkTan", "darkTurquoise", "darkViolet", "darkWood", "deepPink", "deepSkyBlue", "dimGray", "dodgerBlue", "dustyRose", "fadedBrown", "feldspar", "fireBrick", "floralWhite", "forestGreen", "fuchsia", "gainsboro", "ghostWhite", "gold", "goldenRod", "gray", "green", "greenCopper", "greenYellow", "honeyDew", "hotPink", "hunterGreen", "indianRed", "indigo", "ivory", "khaki", "lavender", "lavenderBlush", "lawnGreen", "lemonChiffon", "lightBlue", "lightCoral", "lightCyan", "lightGoldenRodYellow", "lightGray", "lightGreen", "lightPink", "lightSalmon", "lightSeaGreen", "lightSkyBlue", "lightSlateBlue", "lightSlateGray", "lightSteelBlue", "lightWood", "lightYellow", "lime", "limeGreen", "linen", "magenta", "mandarinOrange", "maroon", "mediumAquaMarine", "mediumBlue", "mediumGoldenRod", "mediumOrchid", "mediumPurple", "mediumSeaGreen", "mediumSlateBlue", "mediumSpringGreen", "mediumTurquoise", "mediumVioletRed", "mediumWood", "midnightBlue", "mintCream", "mistyRose", "moccasin", "navajoWhite", "navy", "navyBlue", "neonBlue", "neonPink", "newMidnightBlue", "newTan", "oldGold", "oldLace", "olive", "oliveDrab", "orange", "orangeRed", "orchid", "paleGoldenRod", "paleGreen", "paleTurquoise", "paleVioletRed", "papayaWhip", "peachPuff", "peru", "pink", "plum", "powderBlue", "purple", "quartz", "red", "richBlue", "rosyBrown", "royalBlue", "saddleBrown", "salmon", "sandyBrown", "scarlet", "seaGreen", "seaShell", "semiSweetChocolate", "sienna", "silver", "skyBlue", "slateBlue", "slateGray", "snow", "spicyPink", "springGreen", "steelBlue", "summerSky", "tan", "teal", "thistle", "tomato", "turquoise", "veryLightGrey", "violet", "violetRed", "wheat", "white", "whiteSmoke", "yellow", "yellowGreen"
from com.feature50.clarity.css.CSSUtils.COLOR_NAMES
Also see http://www.w3schools.com/html/html_colornames.asp
*/
css {
	fontScreenWidth = 1024
	fontFamily = 'Te X Gyre Adventor'
	codeFontFamily = 'Courier New' // DejaVu Sans Mono
	color = 'black'
	headerColor = 'darkRed'
	subHeaderColor = 'black'
	footerColor = 'darkSlateGray'
	greyedColor = 'darkGray'
	linkColor = 'blue'
	codeColor = 'lime'
	codeBackgroundColor = 'black'
	imageSourceColor = 'blue'
}

images = [
	duck:    [width: 566,  height: 470, url: 'large_duck.jpg'],
	owasp:   [width: 542,  height: 100, url: 'ologo.png'],
	xkcd:    [width: 1000, height: 308, url: 'xkcd.png', source: 'https://xkcd.com/327/'],
	hacker:  [width: 620,  height: 465, url: 'hacker.jpg', source: 'http://cdn.morguefile.com/imageData/public/files/r/ronnieb/preview/fldr_2005_05_26/file000331196237.jpg'],
	sql:     [width: 300,  height: 199, url: '1573136674_962376ebda_z.jpg', source: 'https://secure.flickr.com/photos/conchur/1573136674'],
	nemesis: [width: 318,  height: 480, url: 'nemesis.jpg', source: 'http://www.compsec.org/security/images/sectools/nemesis.jpg'],
	roll:    [width: 508,  height: 338, url: 'roll-marijuana-joint-pot-drugs.gif', source: 'http://media.parentsociety.com/wp-content/uploads/2012/09/roll-marijuana-joint-pot-drugs.gif']
]

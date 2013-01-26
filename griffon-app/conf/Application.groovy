application {
	title = 'Grails Security'
	startupGroups = ['DeckLauncher2']
	autoShutdown = true
}

mvcGroups {
	'DeckLauncher2' {
		model      = 'griffon.plugins.slideware.DeckLauncherModel'
		view       = 'griffon.plugins.slideware.DeckLauncherView'
		controller = 'com.burtbeckwith.griffon.DeckLauncherController'
	}
	'GroovyConsole' {
		model      = 'com.burtbeckwith.griffon.GroovyConsoleModel'
		view       = 'com.burtbeckwith.griffon.GroovyConsoleView'
		controller = 'com.burtbeckwith.griffon.GroovyConsoleController'
	}
}

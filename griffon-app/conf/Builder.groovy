root {
	'groovy.swing.SwingBuilder' {
		controller = ['Threading']
		view = '*'
	}
}

root.'griffon.builder.trident.TridentBuilder'.view = '*'

root.'griffon.builder.css.CSSBuilder'.view = '*'
root.'griffon.builder.css.CSSBuilder'.controller = ['CSS']

root.'griffon.builder.jide.JideBuilder'.view = '*'

root.'GlazedlistsGriffonAddon'.addon = true

jx {
	'groovy.swing.SwingXBuilder' {
		view = '*'
	}
}

//root.'I18nGriffonAddon'.addon = true
//root.'TransitionsGriffonAddon'.addon = true
//root.'SlidewareGriffonAddon'.addon = true
//root.'MiglayoutGriffonAddon'.addon = true
//root.'JxlayerGriffonAddon'.addon = true
//root.'JbusyComponentGriffonAddon'.addon = true
//root.'LookandfeelGriffonAddon'.addon = true
//root.'SyntaxtextGriffonAddon'.addon = true
//root.'ChartsGriffonAddon'.addon = true
//root.'JoglCompatGriffonAddon'.addon = true
//root.'Jzy3dGriffonAddon'.addon = true
//root.'CoverflowGriffonAddon'.addon = true
//root.'SteelGriffonAddon'.addon = true

import static griffon.util.GriffonApplicationUtils.isMacOSX
import griffon.builder.css.CSSBindings
import groovy.swing.SwingBuilder

import com.burtbeckwith.griffon.Utils

SwingBuilder.lookAndFeel((isMacOSX ? 'system' : 'nimbus'), 'gtk', ['metal', [boldFonts: false]])

CSSBindings.instance.setVariable 'cssConfig', Utils.config.css

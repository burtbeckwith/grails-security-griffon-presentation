import com.burtbeckwith.griffon.DelegatingMetaClass
import com.burtbeckwith.griffon.BackgroundPainters
import com.burtbeckwith.griffon.ComponentBuilders
import com.burtbeckwith.griffon.SlideBuilders
import com.burtbeckwith.griffon.Transitions

backgroundPainter = BackgroundPainters.defaultBackgroundPainter

defaultTransition = Transitions.noEffectTransition

def componentBuilders = new ComponentBuilders()
componentBuilders.metaClass = new DelegatingMetaClass(componentBuilders.metaClass, metaClass)

def slideBuilders = new SlideBuilders(componentBuilders)
slideBuilders.metaClass = new DelegatingMetaClass(slideBuilders.metaClass, metaClass)

bulletSlide = slideBuilders.bulletSlide
centeredSlide = slideBuilders.centeredSlide
colorSlide = slideBuilders.colorSlide
imageSlide = slideBuilders.imageSlide
//introSlide = slideBuilders.introSlide

blankLine = componentBuilders.blankLine
bullet = componentBuilders.bullet
code = componentBuilders.code
createFooter = componentBuilders.createFooter
createHeader = componentBuilders.createHeader
createEditor = componentBuilders.createEditor
createSplitEditor = componentBuilders.createSplitEditor
defaultLayout = componentBuilders.defaultLayout
headerLabel = componentBuilders.headerLabel
subheader = componentBuilders.subheader
highlightBullets = componentBuilders.highlightBullets
imageLabel = componentBuilders.imageLabel
link = componentBuilders.link

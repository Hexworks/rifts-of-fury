package org.codetome.rof

import org.codetome.rof.screens.MainMenuScreen
import org.codetome.rof.screens.OptionsScreen
import org.codetome.zircon.api.Size
import org.codetome.zircon.api.builder.TerminalBuilder
import org.codetome.zircon.api.resource.CP437TilesetResource
import org.codetome.zircon.api.resource.ColorThemeResource
import org.codetome.zircon.api.screen.Screen
import org.codetome.zircon.api.terminal.Terminal
import java.awt.Toolkit
import java.util.function.Consumer

private val THEME = ColorThemeResource.SOLARIZED_DARK_YELLOW.getTheme()
private val FONT = CP437TilesetResource.ROGUE_YUN_16X16.toFont()

fun main(args: Array<String>) {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val columns = screenSize.getWidth() / FONT.getWidth()
    val rows = screenSize.getHeight() / FONT.getHeight()
    val terminalSize = Size.of(columns.toInt(), rows.toInt())

    val terminal = TerminalBuilder.newBuilder()
            .font(FONT)
            .fullScreen()
            .initialTerminalSize(terminalSize)
            .build()

    val mainMenu = MainMenuScreen.create(terminal, THEME)
    val options = OptionsScreen.create(terminal, THEME)

    options.backButton.onMouseReleased(Consumer {
        mainMenu.display()
    })

    mainMenu.optionsButton.onMouseReleased(Consumer {
        options.display()
    })

    mainMenu.quitButton.onMouseReleased(Consumer {
        System.exit(0)
    })

    mainMenu.display()
}
package org.codetome.rof.screens

import org.codetome.zircon.api.Position
import org.codetome.zircon.api.Size
import org.codetome.zircon.api.builder.TerminalBuilder
import org.codetome.zircon.api.component.Button
import org.codetome.zircon.api.component.ColorTheme
import org.codetome.zircon.api.component.builder.ButtonBuilder
import org.codetome.zircon.api.component.builder.LabelBuilder
import org.codetome.zircon.api.component.builder.PanelBuilder
import org.codetome.zircon.api.screen.Screen
import org.codetome.zircon.api.terminal.Terminal
import org.codetome.zircon.internal.graphics.BoxType

class MainMenuScreen(
        private val screen: Screen,
        val newGameButton: Button,
        val optionsButton: Button,
        val quitButton: Button) {

    fun display() = screen.display()

    companion object {

        private val MAIN_MENU_LABEL = "M A I N   M E N U"
        private val NEW_GAME_BUTTON_LABEL = "N E W   G A M E"
        private val OPTIONS_BUTTON_LABEL = "O P T I O N S"
        private val QUIT_BUTTON_LABEL = "Q U I T"

        private val MAIN_MENU_PANEL_WIDTH = 25
        private val MAIN_MENU_PANEL_HEIGHT = 10

        fun create(terminal: Terminal, theme: ColorTheme): MainMenuScreen {
            val (cols, rows) = terminal.getBoundableSize()
            val screen = TerminalBuilder.createScreenFor(terminal)
            val menuPosition = Position.of(
                    (cols - MAIN_MENU_PANEL_WIDTH) / 2,
                    (rows - MAIN_MENU_PANEL_HEIGHT) / 2)
            val mainMenuLabel = LabelBuilder.newBuilder()
                    .text(MAIN_MENU_LABEL)
                    .position(menuPosition.withRelativeRow(-3).withRelativeColumn(4))
                    .build()

            val menuPanel = PanelBuilder.newBuilder()
                    .boxType(BoxType.LEFT_RIGHT_DOUBLE)
                    .wrapInBox()
                    .position(menuPosition)
                    .size(Size.of(MAIN_MENU_PANEL_WIDTH, MAIN_MENU_PANEL_HEIGHT))
                    .build()

            val newGameButton = ButtonBuilder.newBuilder()
                    .text(NEW_GAME_BUTTON_LABEL)
                    .position(Position.of(3, 1))
                    .build()

            val optionsButton = ButtonBuilder.newBuilder()
                    .text(OPTIONS_BUTTON_LABEL)
                    .position(Position.of(4, 3))
                    .build()

            val quitButton = ButtonBuilder.newBuilder()
                    .text(QUIT_BUTTON_LABEL)
                    .position(Position.of(7, 5))
                    .build()


            screen.addComponent(mainMenuLabel)
            menuPanel.addComponent(newGameButton)
            menuPanel.addComponent(optionsButton)
            menuPanel.addComponent(quitButton)


            screen.addComponent(menuPanel)
            screen.applyColorTheme(theme)

            return MainMenuScreen(
                    screen = screen,
                    newGameButton = newGameButton,
                    optionsButton = optionsButton,
                    quitButton = quitButton)
        }
    }
}
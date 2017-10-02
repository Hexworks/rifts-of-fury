package org.codetome.rof.screens

import org.codetome.zircon.api.Position
import org.codetome.zircon.api.Size
import org.codetome.zircon.api.Symbols
import org.codetome.zircon.api.builder.TerminalBuilder
import org.codetome.zircon.api.component.Button
import org.codetome.zircon.api.component.ColorTheme
import org.codetome.zircon.api.component.builder.ButtonBuilder
import org.codetome.zircon.api.component.builder.PanelBuilder
import org.codetome.zircon.api.component.builder.RadioButtonGroupBuilder
import org.codetome.zircon.api.screen.Screen
import org.codetome.zircon.api.terminal.Terminal
import org.codetome.zircon.internal.graphics.BoxType
import java.util.*

class OptionsScreen(private val screen: Screen,
                    val backButton: Button,
                    val applyButton: Button) {

    fun display() = screen.display()

    companion object {

        private val DIFFICULTY_LABEL = "D I F F I C U L T Y"
        private val BACK_LABEL = Symbols.ARROW_LEFT + " B A C K"
        private val APPLY_LABEL = "A P P L Y"

        private val DIFFICULTIES = arrayOf("TINGLE", "ANXIETY", "HORROR")

        private val PANEL_SPACING = 2

        fun create(terminal: Terminal, theme: ColorTheme): OptionsScreen {
            val (cols, rows) = terminal.getBoundableSize()
            val screen = TerminalBuilder.createScreenFor(terminal)

            val backButton = ButtonBuilder.newBuilder()
                    .text(BACK_LABEL)
                    .position(Position.of(PANEL_SPACING, rows - PANEL_SPACING * 2))
                    .build()

            val applyButton = ButtonBuilder.newBuilder()
                    .text(APPLY_LABEL)
                    .position(Position.of(PANEL_SPACING, 0).relativeToRightOf(backButton))
                    .build()

            val difficultyPanel = PanelBuilder.newBuilder()
                    .size(Size.of((cols - PANEL_SPACING) / 3, 9))
                    .position(Position.of(PANEL_SPACING, PANEL_SPACING))
                    .wrapInBox()
                    .boxType(BoxType.LEFT_RIGHT_DOUBLE)
                    .title(DIFFICULTY_LABEL)
                    .build()

            val difficultyRadio = RadioButtonGroupBuilder.newBuilder()
                    .position(Position.of(1, 1))
                    .spacing(1)
                    .size(difficultyPanel.getBoundableSize().minus(Size.of(2, 2)))
                    .build()

            Arrays.asList<String>(*DIFFICULTIES).forEach { diff -> difficultyRadio.addOption(diff, diff) }

            difficultyPanel.addComponent(difficultyRadio)
            screen.addComponent(backButton)
            screen.addComponent(applyButton)
            screen.addComponent(difficultyPanel)

            screen.applyColorTheme(theme)

            return OptionsScreen(
                    screen = screen,
                    backButton = backButton,
                    applyButton = applyButton)
        }
    }
}
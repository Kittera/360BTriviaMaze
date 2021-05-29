package view

import java.awt.Color
import javax.swing.JLabel

class DarkLabel(text: String): JLabel(text) {
    init {
        foreground = Color.WHITE
    }
}

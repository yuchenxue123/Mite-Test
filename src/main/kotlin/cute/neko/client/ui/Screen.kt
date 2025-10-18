package cute.neko.client.ui

import cute.neko.client.ui.font.Fonts
import cute.neko.client.utils.client.mc
import net.minecraft.FontRenderer

interface Screen {
    val font
        get() = Fonts.ARIAL_18

    fun render(mouseX: Int, mouseY: Int, deltaTime: Float) {}

    fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {}

    fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {}
}
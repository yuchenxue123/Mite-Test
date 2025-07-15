package cute.neko.client.ui

import cute.neko.client.utils.client.mc
import net.minecraft.FontRenderer

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

interface Screen {
    val font: FontRenderer
        get() = mc.fontRenderer

    fun render(mouseX: Int, mouseY: Int, deltaTime: Float) {}

    fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {}

    fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {}
}
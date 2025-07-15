package cute.neko.client.module.render

import cute.neko.client.module.Category
import cute.neko.client.module.Module
import cute.neko.client.ui.ClickScreen
import org.lwjgl.input.Keyboard

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

object ModuleClickScreen : Module(
    name = "ClickScreen",
    category = Category.RENDER,
    key = Keyboard.KEY_RSHIFT,
    lock = true
) {

    override fun enable() {
        mc.displayGuiScreen(ClickScreen)
        toggle()
    }
}
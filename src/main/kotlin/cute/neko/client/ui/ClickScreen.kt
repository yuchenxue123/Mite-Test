package cute.neko.client.ui

import cute.neko.client.module.Category
import cute.neko.client.ui.Instance.BUTTON_WIDTH
import net.minecraft.GuiScreen

object ClickScreen : GuiScreen() {

    private val displays = mutableListOf<CategoryDisplay>()

    init {
        build()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, deltaTime: Float) {
        displays.forEach {
            it.render(mouseX, mouseY, deltaTime)
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        displays.forEach {
            it.mouseClicked(mouseX, mouseY, button)
        }
    }

    // mouseRelease
    override fun mouseMovedOrUp(mouseX: Int, mouseY: Int, button: Int) {
        displays.forEach {
            it.mouseReleased(mouseX, mouseY, button)
        }
    }

    private fun build() {
        var x = 20
        Category.entries.forEach { category ->
            displays.add(CategoryDisplay(category, x, 20))
            x += BUTTON_WIDTH + 8
        }
    }

    override fun doesGuiPauseGame(): Boolean = false
}
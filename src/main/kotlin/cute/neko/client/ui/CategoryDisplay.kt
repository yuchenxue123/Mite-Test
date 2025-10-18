package cute.neko.client.ui

import cute.neko.client.module.Category
import cute.neko.client.ui.Instance.BUTTON_HEIGHT
import cute.neko.client.ui.Instance.BUTTON_WIDTH
import cute.neko.client.ui.Instance.isHovered
import cute.neko.client.utils.render.RenderUtils
import java.awt.Color

class CategoryDisplay(val category: Category, x: Int, y: Int) : Screen {

    var renderX = x
    var renderY = y

    private val buttons = mutableListOf<ModuleButton>()

    private var dragging = false
    private var dragX = 0
    private var dragY = 0

    init {
        build()
    }

    override fun render(mouseX: Int, mouseY: Int, deltaTime: Float) {
        if (dragging) {
            renderX = mouseX - dragX
            renderY = mouseY - dragY
        }

        refresh()

        RenderUtils.drawRect(
            renderX,
            renderY,
            BUTTON_WIDTH,
            BUTTON_HEIGHT,
            Color( 40, 40, 40).rgb
        )

        val text = category.name
        font.drawString(
            text,
            renderX + (BUTTON_WIDTH - font.getStringWidth(text)) / 2f,
            renderY + (BUTTON_HEIGHT - font.height) / 2f,
            Color.WHITE.rgb
        )

        buttons.forEach {
            it.render(mouseX, mouseY, deltaTime)
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        val hovered = isHovered(renderX, renderY, BUTTON_WIDTH, BUTTON_HEIGHT, mouseX, mouseY)

        if (hovered && button == 0) {
            dragging = true
            dragX = mouseX - renderX
            dragY = mouseY - renderY
        }

        buttons.forEach {
            it.mouseClicked(mouseX, mouseY, button)
        }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {
        dragging = false

        buttons.forEach {
            it.mouseReleased(renderX, renderY, button)
        }
    }

    private fun build() {
        var y = renderY + BUTTON_HEIGHT

        category.modules.forEach { module ->
            buttons.add(ModuleButton(module).x(renderX).y(y))
            y += BUTTON_HEIGHT
        }
    }

    private fun refresh() {
        var y = renderY + BUTTON_HEIGHT

        buttons.forEach { button ->
            button.x(renderX).y(y)
            y += BUTTON_HEIGHT
        }
    }
}
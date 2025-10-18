package cute.neko.client.ui

import cute.neko.client.module.Module
import cute.neko.client.ui.Instance.BUTTON_HEIGHT
import cute.neko.client.ui.Instance.BUTTON_WIDTH
import cute.neko.client.ui.Instance.isHovered
import cute.neko.client.utils.client.chat
import cute.neko.client.utils.render.RenderUtils
import java.awt.Color

class ModuleButton(val module: Module) : Screen {

    private var x = 0
    private var y = 0

    override fun render(mouseX: Int, mouseY: Int, deltaTime: Float) {
        val color = if (module.state) {
            Color(80, 220, 80).rgb
        } else {
            Color(120, 120, 120).rgb
        }

        RenderUtils.drawRect(
            x,
            y,
            BUTTON_WIDTH,
            BUTTON_HEIGHT,
            color
        )

        val text = module.name
        font.drawString(
            text,
            x + (BUTTON_WIDTH - font.getStringWidth(text)) / 2f,
            y + (BUTTON_HEIGHT - font.height) / 2f,
            Color.WHITE.rgb
        )
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        val hovered = isHovered(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, mouseX, mouseY)

        if (hovered && button == 0 && !module.lock) {
            module.toggle()
        }
    }

    fun x(x: Int) = apply {
        this.x = x
    }

    fun y(y: Int) = apply {
        this.y = y
    }
}
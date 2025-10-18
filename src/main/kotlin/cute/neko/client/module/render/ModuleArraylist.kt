package cute.neko.client.module.render

import cute.neko.client.event.events.ScreenRenderEvent
import cute.neko.client.event.handle
import cute.neko.client.module.Category
import cute.neko.client.module.Module
import cute.neko.client.module.ModuleManager
import cute.neko.client.ui.font.Fonts
import cute.neko.client.utils.render.RenderUtils
import java.awt.Color

object ModuleArraylist : Module("Arraylist", Category.RENDER) {

    private val render = handle<ScreenRenderEvent> {

        val font = Fonts.ARIAL_18

        val x = 2f
        var y = 20f

        ModuleManager.modules
            .filter { it.state }
            .sortedByDescending { font.getStringWidth(it.name) }
            .forEach {

            val name = it.name

            RenderUtils.drawRect(
                x, y,
                font.getStringWidth(name) + 4f,
                font.height + 4f,
                Color(20, 20, 20, 100).rgb
            )

            font.drawString(
                name,
                x + 2f,
                y + 2f,
                Color.white.rgb
            )

            y += font.height + 4f
        }
    }

}
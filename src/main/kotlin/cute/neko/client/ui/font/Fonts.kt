package cute.neko.client.ui.font

import net.minecraft.Minecraft
import net.minecraft.ResourceLocation
import java.awt.Font

object Fonts {

    val ARIAL_18 = FontRenderer(getFont("arial", 18), true, true)

    fun getFont(name: String, size: Int, extension: String = "ttf"): Font {
        return try {
            val inputStream = Minecraft.getMinecraft().resourceManager.getResource(ResourceLocation("neko/font/$name.$extension")).inputStream
            val font = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(Font.PLAIN, size.toFloat())
            inputStream.close()
            font
        } catch (e: Exception) {
            Font("Default", Font.PLAIN, size)
        }
    }

}
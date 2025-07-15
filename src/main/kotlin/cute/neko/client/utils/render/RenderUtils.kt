package cute.neko.client.utils.render

import net.minecraft.Gui
import net.minecraft.Tessellator
import org.lwjgl.opengl.GL11.GL_BLEND
import org.lwjgl.opengl.GL11.GL_LINE_SMOOTH
import org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA
import org.lwjgl.opengl.GL11.GL_QUADS
import org.lwjgl.opengl.GL11.GL_SRC_ALPHA
import org.lwjgl.opengl.GL11.GL_TEXTURE_2D
import org.lwjgl.opengl.GL11.glBegin
import org.lwjgl.opengl.GL11.glBlendFunc
import org.lwjgl.opengl.GL11.glColor4f
import org.lwjgl.opengl.GL11.glDisable
import org.lwjgl.opengl.GL11.glEnable
import org.lwjgl.opengl.GL11.glEnd
import org.lwjgl.opengl.GL11.glVertex2f

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object RenderUtils {

    fun drawRect(x: Float, y: Float, width: Float, height: Float, color: Int) {
        glEnable(GL_BLEND)
        glDisable(GL_TEXTURE_2D)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        glEnable(GL_LINE_SMOOTH)

        glColor(color)
        glBegin(GL_QUADS)

        glVertex2f(x + width, y)
        glVertex2f(x, y)
        glVertex2f(x, y + height)
        glVertex2f(x + width, y + height)
        glEnd()

        glEnable(GL_TEXTURE_2D)
        glDisable(GL_BLEND)
        glDisable(GL_LINE_SMOOTH)
    }

    fun drawRect(x: Int, y: Int, width: Int, height: Int, color: Int) {
        drawRect(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), color)
    }

    fun glColor(red: Int, green: Int, blue: Int, alpha: Int) {
        glColor4f(red / 255f, green / 255f, blue / 255f, alpha / 255f)
    }

    fun glColor(red: Float, green: Float, blue: Float, alpha: Float) {
        glColor4f(red / 255f, green / 255f, blue / 255f, alpha / 255f)
    }

    fun glColor(hex: Int) {
        glColor(hex shr 16 and 0xFF, hex shr 8 and 0xFF, hex and 0xFF, hex shr 24 and 0xFF)
    }

    fun glColor(color: Int, alpha: Float) {
        val r = (color shr 16 and 255).toFloat() / 255.0f
        val g = (color shr 8 and 255).toFloat() / 255.0f
        val b = (color and 255).toFloat() / 255.0f
        glColor(r, g, b, alpha)
    }
}
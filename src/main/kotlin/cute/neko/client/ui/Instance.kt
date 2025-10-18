package cute.neko.client.ui

object Instance {
    const val BUTTON_WIDTH = 100
    const val BUTTON_HEIGHT = 20

    fun isHovered(renderX: Int, renderY: Int, width: Int, height: Int, mouseX: Int, mouseY: Int): Boolean {
        return mouseX > renderX && mouseX < renderX + width && mouseY > renderY && mouseY < renderY + height
    }
}
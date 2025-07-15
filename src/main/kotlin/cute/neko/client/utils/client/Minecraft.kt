package cute.neko.client.utils.client

import net.minecraft.EntityClientPlayerMP
import net.minecraft.Minecraft
import net.minecraft.WorldClient

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

val mc: Minecraft
    inline get() = Minecraft.getMinecraft()

// EntityPlayerSP
val player: EntityClientPlayerMP
    inline get() = mc.thePlayer!!

val world: WorldClient
    inline get() = mc.theWorld!!

fun chat(text: String) {
    mc.ingameGUI.chatGUI.printChatMessage(text)
}
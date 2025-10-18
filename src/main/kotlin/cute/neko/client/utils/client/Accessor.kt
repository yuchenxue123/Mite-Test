package cute.neko.client.utils.client

import net.minecraft.EntityClientPlayerMP
import net.minecraft.Minecraft
import net.minecraft.WorldClient

interface Accessor {
    val mc: Minecraft
        get() = Minecraft.getMinecraft()

    val player: EntityClientPlayerMP
        get() = mc.thePlayer

    val world: WorldClient
        get() = mc.theWorld!!
}
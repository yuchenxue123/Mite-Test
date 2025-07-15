package cute.neko.client.module.combat

import cute.neko.client.event.events.PlayerTickEvent
import cute.neko.client.event.handle
import cute.neko.client.module.Category
import cute.neko.client.module.Module
import cute.neko.client.utils.extension.center
import cute.neko.client.utils.rotation.RotationManager
import cute.neko.client.utils.rotation.RotationUtils
import net.minecraft.EntityArrow
import net.minecraft.EntityItem
import net.minecraft.EntityLivingBase
import net.minecraft.EntityPlayer
import org.lwjgl.input.Keyboard

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object ModuleKillAura : Module(
    name = "KillAura",
    category = Category.COMBAT,
    key = Keyboard.KEY_R
) {

    override fun disable() {
        RotationManager.remove(this)
    }

    private val onPlayerTick = handle<PlayerTickEvent> {
        val target = world.loadedEntityList
            .filterIsInstance<EntityLivingBase>()
            .filter {
                it !is EntityPlayer && player.getDistanceToEntity(it) <= 6
            }
            .minByOrNull {
                player.getDistanceToEntity(it)
            }

        target?.let {
            RotationManager.request(
                this,
                RotationUtils.toRotation(it.boundingBox.center)
            )

            player.playerController.leftClickEntity(it)
            player.swingArm()
        } ?: run {
            RotationManager.remove(this)
        }

    }
}
package cute.neko.client.utils.extension

import cute.neko.client.utils.client.mc
import cute.neko.client.utils.rotation.Rotation
import net.minecraft.AxisAlignedBB
import net.minecraft.Entity
import net.minecraft.Vec3

val Entity.eyes: Vec3
    get() = getPositionEyes(mc.timer.renderPartialTicks)

val Entity.rotation: Rotation
    get() = Rotation(rotationYaw, rotationPitch)

val AxisAlignedBB.center: Vec3
    get() = Vec3.createVectorHelper((minX + maxX) / 2.0, (minY + maxY) / 2.0, (minZ + maxZ) / 2.0)

fun Entity.getPositionEyes(deltaTime: Float): Vec3 {
    if (deltaTime == 1.0f) {
        return Vec3.createVectorHelper(this.posX, this.posY + this.eyeHeight, this.posZ)
    } else {
        val d0 = this.prevPosX + (this.posX - this.prevPosX) * deltaTime
        val d1 = this.prevPosY + (this.posY - this.prevPosY) * deltaTime + this.eyeHeight
        val d2 = this.prevPosZ + (this.posZ - this.prevPosZ) * deltaTime

        return Vec3.createVectorHelper(d0, d1, d2)
    }
}
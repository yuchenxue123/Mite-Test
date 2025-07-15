package cute.neko.client.utils.rotation

import cute.neko.client.utils.client.mc
import cute.neko.client.utils.extension.component1
import cute.neko.client.utils.extension.component2
import cute.neko.client.utils.extension.component3
import cute.neko.client.utils.extension.eyes
import cute.neko.client.utils.extension.minus
import cute.neko.client.utils.extension.toDegrees
import net.minecraft.Entity
import net.minecraft.MathHelper
import net.minecraft.Vec3
import kotlin.math.atan2
import kotlin.math.sqrt

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object RotationUtils {

    fun toRotation(vec: Vec3, fromEntity: Entity = mc.thePlayer!!): Rotation {
        val (diffX, diffY, diffZ) = vec - fromEntity.eyes
        return Rotation(
            MathHelper.wrapAngleTo180_float(atan2(diffZ, diffX).toDegrees().toFloat() - 90f),
            MathHelper.wrapAngleTo180_float(
                -atan2(diffY, sqrt(diffX * diffX + diffZ * diffZ)).toDegrees().toFloat()
            )
        )
    }

    fun angleDifference(a: Float, b: Float) = MathHelper.wrapAngleTo180_float(a - b)
}
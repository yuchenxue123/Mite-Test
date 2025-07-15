package cute.neko.client.utils.extension

import net.minecraft.Vec3
import org.joml.Vector3d
import org.joml.Vector4f
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.round

/**
 * @author yuchenxue
 * @date 2025/05/06
 */

fun Double.decimals(n: Int): Double {
    val bigDecimal = BigDecimal(this)
    val rounded = bigDecimal.setScale(n, RoundingMode.HALF_UP)
    return rounded.toDouble()
}

fun Double.toRadians(): Double = this * 0.017453292519943295
fun Double.toDegrees(): Double = this * 57.29577951308232
fun Double.step(step: Double): Double = round(this / step) * step
fun Double.squared(): Double = this * this

fun Float.toRadians() = this * 0.017453292f
fun Float.toDegrees() = this * 57.29578f
fun Float.decimals(n: Int): Float = this.toDouble().decimals(n).toFloat()
fun Float.step(step: Float): Float = round(this / step) * step
fun Float.squared(): Float = this * this

operator fun Vec3.plus(other: Vec3): Vec3 = addVector(other.xCoord, other.yCoord, other.zCoord)
operator fun Vec3.minus(other: Vec3): Vec3 = other.subtract(this)

operator fun Vec3.component1() = xCoord
operator fun Vec3.component2() = yCoord
operator fun Vec3.component3() = zCoord

operator fun Vector4f.component1() = x
operator fun Vector4f.component2() = y
operator fun Vector4f.component3() = z
operator fun Vector4f.component4() = w

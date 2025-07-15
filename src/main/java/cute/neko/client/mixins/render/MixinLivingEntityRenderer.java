package cute.neko.client.mixins.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import cute.neko.client.utils.rotation.Rotation;
import cute.neko.client.utils.rotation.RotationManager;
import kotlin.Pair;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

@Mixin(RendererLivingEntity.class)
public class MixinLivingEntityRenderer {

    @Unique
    private Pair<Rotation, Rotation> getRotation() {
        var previous = RotationManager.INSTANCE.getPreviousRotation();
        var current = RotationManager.INSTANCE.getCurrentRotation();

        if (previous != null && current != null) {
            return new Pair<>(previous, current);
        }

        return null;
    }

    @ModifyExpressionValue(method = "doRenderLiving", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;prevRotationYawHead:F"))
    private float hookRotationPreYaw(float original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity != Minecraft.getMinecraft().thePlayer) {
            return original;
        }

        var rotation = getRotation();

        if (rotation != null) {
            return rotation.getFirst().getYaw();
        }

        return original;
    }

    @ModifyExpressionValue(method = "doRenderLiving", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;rotationYawHead:F"))
    private float hookRotationYaw(float original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity != Minecraft.getMinecraft().thePlayer) {
            return original;
        }

        var rotation = getRotation();

        if (rotation != null) {
            return rotation.getSecond().getYaw();
        }

        return original;
    }

    @ModifyExpressionValue(method = "doRenderLiving", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;prevRotationPitch:F"))
    private float hookRotationPrePitch(float original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity != Minecraft.getMinecraft().thePlayer) {
            return original;
        }

        var rotation = getRotation();

        if (rotation != null) {
            return rotation.getFirst().getPitch();
        }

        return original;
    }

    @ModifyExpressionValue(method = "doRenderLiving", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;rotationPitch:F"))
    private float hookRotationPitch(float original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity != Minecraft.getMinecraft().thePlayer) {
            return original;
        }

        var rotation = getRotation();

        if (rotation != null) {
            return rotation.getSecond().getPitch();
        }

        return original;
    }

    @ModifyExpressionValue(method = "doRenderLiving", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;prevRenderYawOffset:F"))
    private float hookRotationPreYawOffset(float original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity != Minecraft.getMinecraft().thePlayer) {
            return original;
        }

        var rotation = getRotation();

        if (rotation != null) {
            return rotation.getFirst().getYaw();
        }

        return original;
    }

    @ModifyExpressionValue(method = "doRenderLiving", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;renderYawOffset:F"))
    private float hookRotationYawOffset(float original, @Local(argsOnly = true) EntityLivingBase entity) {
        if (entity != Minecraft.getMinecraft().thePlayer) {
            return original;
        }

        var rotation = getRotation();

        if (rotation != null) {
            return rotation.getSecond().getYaw();
        }

        return original;
    }

}

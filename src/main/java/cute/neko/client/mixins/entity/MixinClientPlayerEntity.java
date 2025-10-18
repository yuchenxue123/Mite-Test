package cute.neko.client.mixins.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import cute.neko.client.event.EventManager;
import cute.neko.client.event.events.PlayerMotionEvent;
import cute.neko.client.event.events.PlayerTickEvent;
import cute.neko.client.module.player.ModuleAntiHunger;
import net.minecraft.EntityClientPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityClientPlayerMP.class)
public abstract class MixinClientPlayerEntity extends MixinClientPlayer {

    @Unique
    private PlayerMotionEvent.Pre playerMotionEvent;

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/ClientPlayer;onUpdate()V", shift = At.Shift.AFTER))
    public void onUpdate(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(PlayerTickEvent.INSTANCE);
    }

    @Redirect(method = "sendMotionUpdates", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isSprinting()Z"))
    public boolean hookAntiHunger(EntityClientPlayerMP instance) {
        return instance.isSprinting() && !ModuleAntiHunger.INSTANCE.getRunning();
    }

    @Inject(method = "sendMotionUpdates", at = @At(value = "HEAD"))
    private void hookMotionPre(CallbackInfo ci) {
        playerMotionEvent = new PlayerMotionEvent.Pre(
                this.posX,
                this.boundingBox.minY,
                this.posZ,
                this.rotationYaw,
                this.rotationPitch,
                this.onGround
        );

        EventManager.INSTANCE.callEvent(playerMotionEvent);
    }

    @ModifyExpressionValue(
            method = "sendMotionUpdates",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/EntityClientPlayerMP;posX:D")
    )
    private double modifyPositionX(double original) {
        if (playerMotionEvent == null) {
            return original;
        }

        return playerMotionEvent.getX();
    }

    @ModifyExpressionValue(
            method = "sendMotionUpdates",
            at = @At(
                    value = "FIELD",
            target = "Lnet/minecraft/AxisAlignedBB;minY:D")
    )
    private double modifyPositionY(double original) {
        if (playerMotionEvent == null) {
            return original;
        }

        return playerMotionEvent.getY();
    }

    @ModifyExpressionValue(
            method = "sendMotionUpdates",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/EntityClientPlayerMP;posZ:D")
    )
    private double modifyPositionZ(double original) {
        if (playerMotionEvent == null) {
            return original;
        }

        return playerMotionEvent.getZ();
    }

    @ModifyExpressionValue(
            method = "sendMotionUpdates",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/EntityClientPlayerMP;rotationYaw:F")
    )
    private float hookYaw(float original) {
        if (playerMotionEvent == null) {
            return original;
        }

        return playerMotionEvent.getYaw();
    }

    @ModifyExpressionValue(
            method = "sendMotionUpdates",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/EntityClientPlayerMP;rotationPitch:F")
    )
    private float hookPitch(float original) {
        if (playerMotionEvent == null) {
            return original;
        }

        return playerMotionEvent.getPitch();
    }

    @Inject(method = "sendMotionUpdates", at = @At(value = "TAIL"))
    private void hookMotionPost(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(
                new PlayerMotionEvent.Post(
                        this.posX,
                        this.boundingBox.minY,
                        this.posZ,
                        this.rotationYaw,
                        this.rotationPitch,
                        this.onGround
                )
        );
    }
}

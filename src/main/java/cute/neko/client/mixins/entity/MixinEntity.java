package cute.neko.client.mixins.entity;

import net.minecraft.AxisAlignedBB;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public boolean onGround;
    @Shadow
    public float rotationPitch;

    @Shadow
    @Final
    public AxisAlignedBB boundingBox;
    @Shadow
    public float rotationYaw;
    @Shadow
    public double posZ;
    @Shadow
    public double posX;
}

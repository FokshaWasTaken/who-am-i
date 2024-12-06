package me.foksha.whoami.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public class MixinLivingEntityRenderer {
    @ModifyExpressionValue(method = "hasLabel(Lnet/minecraft/entity/LivingEntity;D)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getCameraEntity()Lnet/minecraft/entity/Entity;"))
    public Entity obfuscateCameraEntity(Entity original) {
        // hasLabel checks if the rendered entity is equal to the camera entity (aka our player),
        // so by setting the camera entity to null in this instance makes the check always succeed
        // this also allows to keep the other checks in mojang's code, such as checking if the hud is enabled etc.
        return null;
    }
}

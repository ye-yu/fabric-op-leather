package fabric.mod.planc_.opleather.mixin;

import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.interfaces.CurseOfWebbingMessenger;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CobwebBlock.class)
public class CobwebBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void onEntityCollisionStart(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!(entity instanceof PlayerEntity playerEntity)) return;
        final ItemStack stack = playerEntity.getInventory().armor.get(EquipmentSlot.FEET.getEntitySlotId());
        final int level = EnchantmentHelper.getLevel(ModEnchantments.CURSE_OF_WEBBING.enchantment, stack);
        if (entity instanceof CurseOfWebbingMessenger messenger) messenger.setSpeedLevel(level);
        ci.cancel();
    }
}

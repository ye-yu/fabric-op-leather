package fabric.mod.planc_.opleather.mixin;

import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.interfaces.CurseEnchantmentHolder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getEquipmentLevel", at = @At("HEAD"), cancellable = true)
    private static void onGetEnchantmentLevelHead(Enchantment enchantment, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (enchantment != Enchantments.SWEEPING) return;
        if (!(entity instanceof CurseEnchantmentHolder holder)) return;
        final Integer curseOfRageLevel = holder.getEnchantmentList().getOrDefault(ModEnchantments.CURSE_OF_RAGE, 0);
        if (curseOfRageLevel > 0) {
            System.out.printf("Increased sweeping damage: %d%n", curseOfRageLevel * 2);
            cir.setReturnValue(curseOfRageLevel * 2);
            cir.cancel();
        }
    }
}

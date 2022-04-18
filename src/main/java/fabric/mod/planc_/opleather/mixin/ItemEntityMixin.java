package fabric.mod.planc_.opleather.mixin;

import fabric.mod.planc_.opleather.blocks.CursedCauldron;
import fabric.mod.planc_.opleather.blocks.ModBlocks;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow public abstract ItemStack getStack();

    private static final int TICK_COOL_DOWN = 20;
    private int tickCoolDown = TICK_COOL_DOWN;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at=@At("RETURN"))
    private void onTickEnd(CallbackInfo ci) {
        if (world.isClient()) return;
        tickCoolDown = Math.max(--tickCoolDown, 0);
        if (tickCoolDown == 0) {
            tickCoolDown = 20;
            final BlockState blockState = world.getBlockState(getBlockPos());

            // skip if it is not a cauldron block
            if (blockState.getBlock() != ModBlocks.CURSED_CAULDRON.block) return;

            // skip if already been cursed
            // TODO: optimize search if necessary
            if (EnchantmentHelper.getLevel(ModEnchantments.CURSE_OF_WEBBING.enchantment, getStack()) > 0) return;

            if (blockState.get(CursedCauldron.INGREDIENT_ADDED) && ModEnchantments.CURSE_OF_WEBBING.enchantment.isAcceptableItem(getStack())) {
                // add curse of webbing
                getStack().addEnchantment(ModEnchantments.CURSE_OF_WEBBING.enchantment, ModEnchantments.CURSE_OF_WEBBING.enchantment.getMinLevel());
                CursedCauldron.playSoundEffect(world, getBlockPos());
                LeveledCauldronBlock.decrementFluidLevel(blockState, world, getBlockPos());
            } else {
                this.remove(RemovalReason.DISCARDED);
                CursedCauldron.explodeOnIngredientWrong(world, getBlockPos(), this, blockState.get(LeveledCauldronBlock.LEVEL));
            }

        }
    }
}

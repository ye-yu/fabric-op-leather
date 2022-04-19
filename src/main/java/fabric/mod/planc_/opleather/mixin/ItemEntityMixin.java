package fabric.mod.planc_.opleather.mixin;

import fabric.mod.planc_.opleather.blocks.CursedCauldron;
import fabric.mod.planc_.opleather.blocks.ModBlocks;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.ingredients.IngredientCount;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    private static final Logger LOGGER = LogManager.getLogger();

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
            final BlockPos pos = getBlockPos();
            final BlockState state = world.getBlockState(pos);
            final ItemStack stack = getStack();

            // skip if it is not a cauldron block
            if (state.getBlock() != ModBlocks.CURSED_CAULDRON.block) return;
            LOGGER.info("Item is within a block state: {}", state);

            // check if there is any matching curse
            final HashMap<Long, IngredientCount[]> allPossibleIngredientStates = ModEnchantments.ALL_POSSIBLE_INGREDIENT_STATES.get();
            final Long currentCauldronIngredientState = ModEnchantments.getCurrentCauldronIngredientState(state);
            LOGGER.info("onTickEnd: {}", currentCauldronIngredientState);
            final IngredientCount[] ingredientCounts = allPossibleIngredientStates.get(currentCauldronIngredientState);
            LOGGER.info("onTickEnd: Matched ingredients: {}", () -> Objects.isNull(ingredientCounts) ? "null" : Arrays.stream(ingredientCounts).map(Object::toString).toString());

            for (ModEnchantments value : ModEnchantments.values()) {
                if (value.enchantment.getIngredientHash() == currentCauldronIngredientState && value.enchantment.isAcceptableItem(stack)) {
                    // skip if already been cursed
                    if (EnchantmentHelper.getLevel(value.enchantment, stack) > 0) return;
                    stack.addEnchantment(value.enchantment, value.enchantment.getMinLevel());
                    return;
                }
            }

            // no compatible ingredient
            this.remove(RemovalReason.DISCARDED);
            CursedCauldron.explodeOnIngredientWrong(world, pos, this, state.get(LeveledCauldronBlock.LEVEL));
        }
    }
}

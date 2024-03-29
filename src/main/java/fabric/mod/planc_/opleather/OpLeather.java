package fabric.mod.planc_.opleather;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.blocks.ModBlocks;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.items.ModItems;
import fabric.mod.planc_.opleather.schedulers.EndWorldTickScheduler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class OpLeather implements ModInitializer {
    public static final String NAMESPACE = "op-leather";

    public static final Supplier<TagKey<Item>> LEATHER_ARMORS = Suppliers.memoize(() -> TagKey.of(Registry.ITEM_KEY, Identifier.tryParse("leather_armors")));
    @Override
    public void onInitialize() {
        for (ModItems item : ModItems.values()) {
            Registry.register(Registry.ITEM, item.id, item.item.get());
        }

        for (ModEnchantments enchantment : ModEnchantments.values()) {
            Registry.register(Registry.ENCHANTMENT, enchantment.id, enchantment.enchantment);
            enchantment.enchantment.onInitialize();
        }

        for (ModBlocks block : ModBlocks.values()) {
            Registry.register(Registry.BLOCK, block.id, block.block);
            block.onRegisterListener.accept(block);
        }

        ServerTickEvents.END_WORLD_TICK.register($ -> EndWorldTickScheduler.tickAllEvents());
    }
}

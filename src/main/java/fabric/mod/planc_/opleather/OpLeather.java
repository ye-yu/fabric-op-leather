package fabric.mod.planc_.opleather;

import fabric.mod.planc_.opleather.blocks.ModBlocks;
import fabric.mod.planc_.opleather.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;

public class OpLeather implements ModInitializer {
    public static final String NAMESPACE = "op-leather";

    @Override
    public void onInitialize() {
        for (ModItems item : ModItems.values()) {
            Registry.register(Registry.ITEM, item.id, item.item);
        }

        for (ModBlocks block : ModBlocks.values()) {
            Registry.register(Registry.BLOCK, block.id, block.block);
        }
    }
}

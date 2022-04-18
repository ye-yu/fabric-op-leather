package fabric.mod.planc_.opleather.blocks;

import fabric.mod.planc_.opleather.utils.Utils;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum ModBlocks {

    CURSED_CAULDRON(CursedCauldron::new, modBlocks -> ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
        if (state.get(CursedCauldron.INGREDIENT_ADDED)) return 0xea192b;
        return 0x8919ea;
    }, modBlocks.block));

    public final Block block;
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));

    public final Consumer<ModBlocks> onRegisterListener;

    ModBlocks(Supplier<? extends Block> blockSupplier, Consumer<ModBlocks> onRegisterListener) {
        this.block = blockSupplier.get();
        this.onRegisterListener = onRegisterListener;
    }

    ModBlocks(Supplier<? extends Block> blockSupplier) {
        this(blockSupplier, $ -> {});
    }
}

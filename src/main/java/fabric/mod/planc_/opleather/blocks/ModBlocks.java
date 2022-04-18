package fabric.mod.planc_.opleather.blocks;

import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModBlocks {

    CURSED_CAULDRON(CursedCauldron::new);

    public final Block block;
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));

    ModBlocks(Supplier<? extends Block> blockSupplier) {
        this.block = blockSupplier.get();
    }
}

package fabric.mod.planc_.opleather.blocks;

import fabric.mod.planc_.opleather.ingredients.Ingredients;
import fabric.mod.planc_.opleather.utils.Utils;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum ModBlocks {

    CURSED_CAULDRON(CursedCauldron::new, modBlocks -> ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
        int count = 0;
        long red = 0;
        long green = 0;
        long blue = 0;
        for (Ingredients value : Ingredients.values()) {
            final Integer integer = state.get(value.property);

            long r = value.color >> 16 & 0xFF;
            long g = value.color >> 8 & 0xFF;
            long b = value.color & 0xFF;

            if (integer > 0) {
                ++count;
                red = red * count + (long)Math.abs(r * Math.pow(0.3, integer - 1));
                green = green * count + (long)Math.abs(g * Math.pow(0.3, integer - 1));
                blue = blue * count + (long)Math.abs(b * Math.pow(0.3, integer - 1));

                red /= count;
                green /= count;
                blue /= count;
            }
        }


        red &= 0xFF;
        green &= 0xFF;
        blue &= 0xFF;

        if (count > 0) return (int) ((red << 16) + (green << 8) + blue);

        if (state.get(CursedCauldron.BAD_INGREDIENT)) return 0x24061b;

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

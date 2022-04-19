package fabric.mod.planc_.opleather.utils;

import fabric.mod.planc_.opleather.OpLeather;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Utils {
    public static Identifier identifier(String name) {
        return new Identifier(OpLeather.NAMESPACE, name);
    }

    public static HashMap<Identifier, ModEnchantments> id2EnchantmentMap = new HashMap<>();

    public static HashMap<Identifier, ModEnchantments> getId2EnchantmentMap() {
        if (id2EnchantmentMap.size() == 0) {
            for (ModEnchantments value : ModEnchantments.values()) {
                id2EnchantmentMap.put(value.id, value);
            }
        }
        return id2EnchantmentMap;
    }

    public static void permutationInt(int length, Consumer<int[]> consumer) {
        final int[] elements = IntStream.range(0, length).toArray();
        permutationInt(elements.length, elements, consumer);
    }

    public static void permutationInt(
            int n, int[] elements, Consumer<int[]> consumer) {

        if(n == 1) {
            consumer.accept(elements);
        } else {
            for(int i = 0; i < n-1; i++) {
                permutationInt(n - 1, elements, consumer);
                if(n % 2 == 0) {
                    swapInt(elements, i, n-1);
                } else {
                    swapInt(elements, 0, n-1);
                }
            }
            permutationInt(n - 1, elements, consumer);
        }
    }

    public static void swapInt(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}

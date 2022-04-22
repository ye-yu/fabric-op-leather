import fabric.mod.planc_.opleather.utils.Utils;

import java.util.Arrays;

public class UtilsTest {

    public void utilPermutationTest() {
        System.out.printf("%n%n:: utilPermutationTest:%n");
        Utils.permutationInt(3, arr -> System.out.printf(":: Elements: %s%n", Arrays.toString(arr)));
    }

    public void utilCombinationTest() {
        System.out.printf("%n%n:: utilCombinationTest:%n");
        for (int[] arr : Utils.allCombinations(4)) {
            System.out.printf(":: Elements: %s%n", Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        final UtilsTest utilsTest = new UtilsTest();
        utilsTest.utilPermutationTest();
        utilsTest.utilCombinationTest();
    }
}

import fabric.mod.planc_.opleather.utils.Utils;

import java.util.Arrays;

public class UtilsTest {

    public void utilPermutationTest() {
        Utils.permutationInt(3, arr -> System.out.printf(":: Elements: %s%n", Arrays.toString(arr)));
    }

    public static void main(String[] args) {
        final UtilsTest utilsTest = new UtilsTest();
        utilsTest.utilPermutationTest();
    }
}

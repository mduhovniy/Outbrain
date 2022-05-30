import java.lang.reflect.Array;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        String[] a = {"A", "B", "C", "D"};
        String[] b = {"E", "F", "G"};
        String[] c = {"H", "I"};
        String[] d = {"J"};
        String[][] cp = cartesianProduct(String.class, a, b, c, d);
        System.out.println("combination number: " + cp.length);
        int rows = 6;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cp.length; j++)
                if (j % rows == i)
                    System.out.print(" " + Arrays.toString(cp[j]));
            System.out.println();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][] cartesianProduct(Class<T> clazz, T[]... arrays) {
        if (clazz == null || arrays == null)
            return (T[][]) Array.newInstance(clazz, 0, 0);
        T[][] cp = (T[][]) Array.newInstance(clazz, 1, 0);
        for (int a = 0; a < arrays.length; a++) {
            T[] arr = arrays[a];
            if (arr == null || arr.length == 0) continue;
            T[][] next = (T[][]) Array.newInstance(clazz,cp.length*arr.length,0);
            for (int r = 0; r < cp.length; r++) {
                T[] row = cp[r];
                for (int e = 0; e < arr.length; e++) {
                    T el = arr[e];
                    T[] nRow = Arrays.copyOf(row, row.length + 1);
                    nRow[row.length] = el;
                    next[r * arr.length + e] = nRow;
                }
            }
            cp = next;
        }
        return cp;
    }
}
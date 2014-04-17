package lab04;

import java.util.Arrays;

/**
 * Created by Jacek Żyła on 17.04.14.
 */
public class BigObject {

    private final int[] values = new int[100];

    public BigObject(int number) {
        for (int i = 0; i < 100; i++) {
            values[i] = number;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigObject bigObject = (BigObject) o;

        if (!Arrays.equals(values, bigObject.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values != null ? Arrays.hashCode(values) : 0;
    }
}

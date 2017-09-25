package getwreckt.cs2340.rattrack.model;

/**
 * Created by aguy on 9/25/17.
 */

public class CryptHash {
    public static long hash(String input) {
        long answer = 0;
        for (int i = 0; i < input.length(); i++) {
            answer *= 128;
            answer += (long)input.charAt(i);
        }
    }
}

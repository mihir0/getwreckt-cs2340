package getwreckt.cs2340.rattrack.model;

/**
 * Created by aguy on 9/25/17.
 */

public class CryptHash {
    public static Long hash(String input) {
        Long answer = 0l;
        for (int i = 0; i < input.length(); i++) {
            answer *= 128l;
            answer += (long)input.charAt(i);
        }
        return answer;
    }
}

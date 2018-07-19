import java.util.*;

public class FeatureReader {

    private final List<String> a = Arrays.asList("q","w","s","z");
    private final List<String> e = Arrays.asList("w","2","3","4","r","f","d","s");
    private final List<String> y = Arrays.asList("t","5","6","7","u","j","h","g");
    private final List<String> u = Arrays.asList("y","6","7","8","i","k","j","h");
    private final List<String> i = Arrays.asList("u","7","8","9","o","l","k","j");
    private final List<String> o = Arrays.asList("i","8","9","0","p",";","l","k");

    private final List<String> literay = new ArrayList<>();
    private final Map<Character,Integer> repeat = new HashMap<>();


    public List<String> getLiteray() {
        return literay;
    }

    public void print() {
        for (String s:literay) {
            System.out.println(s);

        }
    }

    public void predictedLiteray(String word) {
        char[] chars = word.toCharArray();

        for (char c:chars) {
            if (c == 'a') {
                replaceA(word);
            }
            else if (c == 'e'){
                replaceE(word);
            }
            else if (c == 'y'){
                replaceY(word);
            }
            else if (c == 'u'){
                replaceU(word);
            }
            else if (c == 'i'){
                replaceI(word);
            }
            else if (c == 'o'){
                replaceO(word);
            }

        }
    }

    private void replaceA(String word) {
        for (String s:a) {
            String change= word.replace("a", s);
            literay.add(change);
        }
    }

    private void replaceE(String word) {
        for (String s:e) {
            String change= word.replace("e", s);
            literay.add(change);
        }
    }

    private void replaceY(String word) {
        for (String s:y) {
            String change= word.replace("y", s);
            literay.add(change);
        }
    }

    private void replaceU(String word) {
        for (String s:u) {
            String change= word.replace("u", s);
            literay.add(change);
        }
    }

    private void replaceI(String word) {
        for (String s:i) {
            String change= word.replace("i", s);
            literay.add(change);
        }
    }

    private void replaceO(String word) {
        for (String s:o) {
            String change= word.replace("o", s);
            literay.add(change);
        }
    }
}

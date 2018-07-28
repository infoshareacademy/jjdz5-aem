package com.infoshareacademy.aem;

import java.util.*;

public class FeatureReader {

    private final List<String> a = Arrays.asList("a","q","w","s","z");
    //private final char[] aa = {'a','q','w','s','z'};
    private final List<String> e = Arrays.asList("e","w","2","3","4","r","f","d","s");
    private final List<String> y = Arrays.asList("y","t","5","6","7","u","j","h","g");
    private final List<String> u = Arrays.asList("u","y","6","7","8","i","k","j","h");
    private final List<String> i = Arrays.asList("i","u","7","8","9","o","l","k","j");
    private final List<String> o = Arrays.asList("o","i","8","9","0","p",";","l","k");

    private final List<String> literay = new ArrayList<>();

    public List<String> getLiteray() {
        return literay;
    }

    public void print() {
        for (String s:literay) {
            System.out.println(s);

        }
    }

    public void predictedLiteray(String word) {
        //StringBuilder sb = new StringBuilder(word);
        char[] chars = word.toCharArray();
        int n = 0;
        for (char c:chars) {
            if (c == 'a') {
                replaceX(a, word, n);
                replace(a,word,"a");
            }
            if (c == 'e'){
                replaceX(e, word, n);
                replace(e,word,"e");
            }
            if (c == 'y'){
                replaceX(y, word, n);
                replace(y,word,"y");
            }
            if (c == 'u'){
                replaceX(u, word, n);
                replace(u,word,"u");
            }
            if (c == 'i'){
                replaceX(i, word, n);
                replace(i,word,"i");
            }
            if (c == 'o'){
                replaceX(o, word, n);
                replace(o,word,"o");
            }
            n++;
        }
    }


    private void replaceX(List<String> x, String word, int n) {
        for (String s:x) {
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder(word);
            sb.setCharAt(n,chars[0]);
            String litter = new String(sb);
            literay.add(litter);
        }
    }

    private void replace(List<String> x, String word, String l) {
        for (String s : x) {
            String change= word.replace(l,s);
            literay.add(change);
        }
    }


//
//    String wal = "waluta";
//    String ekst = "ekstremum";
//
//        featureReader.predictedLiteray(wal);
//        featureReader.predictedLiteray(ekst);
//
//
//        for (String x:featureReader.getLiteray()) {
//        System.out.println(x);
//        if (s == x) {
//            s=featureReader.getLiteray().get(0);
}

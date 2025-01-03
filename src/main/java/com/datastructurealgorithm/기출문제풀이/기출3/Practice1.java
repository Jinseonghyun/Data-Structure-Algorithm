package com.datastructurealgorithm.기출문제풀이.기출3;

public class Practice1 {
    public static int solution(String str) {
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        // words 의 문자열에 해당하는 단어를 정수형으로 변환
        for (int i = 0; i < words.length; i++) {
            str = str.replaceAll(words[i], String.valueOf(i));
        }
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution("onetwothreefour"));
        System.out.println(solution("twozerothreezero"));
        System.out.println(solution("three21zero"));
    }
}

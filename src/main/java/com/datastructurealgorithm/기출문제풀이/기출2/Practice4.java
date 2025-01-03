package com.datastructurealgorithm.기출문제풀이.기출2;

import java.util.ArrayList;

public class Practice4 {
    public static int solution(int cacheSize, String[] searches) {
        ArrayList<String> lru = new ArrayList<>();
        int result = 0;

        // 캐시 사이즈 0 인 경우
        if (cacheSize == 0) {
            return searches.length * 5;
        }

        for (String search : searches) {
            // 대소문자 구분 X -> 소문자로 일괄 변경 후 비교
            String s = search.toLowerCase();

            // Hit case: 참조한 것 맨 마지막으로 옮기고 시간 1 증가
            if (lru.contains(s)) {
                lru.remove(s);
                lru.add(s);
                result++;
            } else {
                // Miss case: 캐시에 추가 후 시간 5 증가 (캐시 꽉 찬 경우 제일 오래된 부분 제거)
                if (lru.size() == cacheSize) {
                    lru.remove(0);
                }
                lru.add(s);
                result += 5;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test code
        String[] searches = {"Google", "Google", "Microsoft", "Apple", "Qualcomm", "Google", "Qualcomm"};
        System.out.println(solution(3, searches));
    }
}

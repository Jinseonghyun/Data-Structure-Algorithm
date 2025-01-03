package com.datastructurealgorithm.기출문제풀이.기출2;

import java.util.Arrays;
import java.util.HashMap;

public class Practice3 {

    public static long[] solution(long n, long[] rooms) {
        // room 번호 만큼 배열로 할당 후 체크 ? -> 메모리 문제

        HashMap<Long, Long> map = new HashMap<>();

        long[] result = new long[rooms.length];

        for (int i = 0; i < rooms.length; i++) {
            // 원하는 방이 비어있으면 할당
            if (!map.containsKey(rooms[i])) {
                result[i] = rooms[i];
                // 해당 방을 키로, 다음 방을 값으로 하여 추가
                map.put(rooms[i], (rooms[i] + 1) % n);
            } else {
                // 원하는 방이 없는 경우 그 다음 방 찾아서 할당
                long cur = rooms[i];
                while (map.containsKey(cur)) {
                    cur = map.get(cur);
                }
                result[i] = cur;
                map.put(cur, (cur + 1) % n);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test code
        long[] rooms = {0, 2, 3, 3};
        System.out.println(Arrays.toString(solution(3, rooms)));

        rooms = new long[] {1, 5, 7, 9, 9, 9, 1, 2, 2, 2};
        System.out.println(Arrays.toString(solution(10, rooms)));
    }
}

package com.datastructurealgorithm.기출문제풀이.기출3;

public class Practice3 {
    public static int solution(int[] stones, int maxJump) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        // left, right 설정
        for (int stone : stones) {
            left = Math.min(left, stone);
            right = Math.max(right, stone);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 건널 수 있는지 체크
            int cnt = 0;
            boolean isOk = true;
            for (int stone: stones) {
                // 건너 뛰어야 하는 최대 칸수 카운트 (연속해서 발생해야 카운트 증가)
                if (stone - mid < 0) {
                    cnt++;
                } else {
                    cnt = 0;
                }

                // cnt 가 maxJump 에 도달한 경우는 더이상 건널 수 없음
                if (cnt == maxJump) {
                    isOk = false;
                    break;
                }
            }

            if (isOk) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left - 1;
    }

    public static void main(String[] args) {
        // Test code
        int[] stones = {1, 1, 2, 1, 1};
        System.out.println(solution(stones, 3));

        stones = new int[] {1, 2, 5, 3, 2, 2, 4, 2, 4, 3};
        System.out.println(solution(stones, 3));
    }
}

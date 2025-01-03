package com.datastructurealgorithm.기출문제풀이.기출1;

import java.util.Arrays;

public class Practice1 {
    public static int solution(int[] appleTrees, int a, int b) {
        // 과수원당 주 관리사 1명은 있어야 하므로 result 에 과수원 개수로 초기화
        int result = appleTrees.length;
        for (int i = 0; i < appleTrees.length; i++) {
            // 주 관리사가 관리 가능한 개수 차감
            appleTrees[i] -= a;

            // 관리해야 할 나무가 남아 있으면 부 관리사 추가
            while (appleTrees[i] > 0) {
                appleTrees[i] -= b;
                result++;
            }
        }
        return result;
    }

    public static int solution2(int[] appleTrees, int a, int b) {
        int result = appleTrees.length;
        for (int i = 0; i < appleTrees.length; i++) {
            appleTrees[i] -= a;

            if (appleTrees[i] > 0) {
                result += appleTrees[i] / b;

                if (appleTrees[i] % b != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Test code
        int[] appleTrees = new int[] {1, 2, 3};
        System.out.println(solution(appleTrees, 1, 1));
        appleTrees = new int[] {1, 2, 3};
        System.out.println(solution2(appleTrees, 1, 1));

        System.out.println();
        appleTrees = new int[]{10, 10, 20, 20, 30, 30};
        System.out.println(solution(appleTrees, 5, 3));
        appleTrees = new int[]{10, 10, 20, 20, 30, 30};
        System.out.println(solution2(appleTrees, 5, 3));
        
        // 시간 측정
        System.out.println();
        appleTrees = new int[100000];
        Arrays.fill(appleTrees, 100000);

        long start = System.currentTimeMillis();
        System.out.println(solution(appleTrees, 1, 1));
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");

        Arrays.fill(appleTrees, 100000);
        start = System.currentTimeMillis();
        System.out.println(solution2(appleTrees, 1, 1));
        end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");

//        System.out.println();
//        a = new int[1_000_000];
//        Arrays.fill(a, 1_000_000);
//        System.out.println(solution2(a, 1, 1));
    }
}

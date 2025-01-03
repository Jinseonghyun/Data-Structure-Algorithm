package com.datastructurealgorithm.기출문제풀이.기출2;

public class Practice1 {

    public static int solution(int[][] maps, int stairLen) {
        int cnt = roadCheck(maps, stairLen);

        int[][] roadsT = new int[maps.length][maps.length];
        transpose(roadsT, maps);

        cnt += roadCheck(roadsT, stairLen);
        return cnt;
    }

    public static void transpose(int[][] dst, int[][] src) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[0].length; j++) {
                dst[j][i] = src[i][j];
            }
        }
    }

    static int roadCheck(int[][] maps, int stairLen) {
        int cnt = 0;

        for (int i = 0; i < maps.length; i++) {
            boolean roadState = true;
            
            // 계단 설치 체크 용
            boolean[] installed = new boolean[maps.length];
            for (int j = 0; j < maps.length - 1; j++) {
                // 높이가 같은 경우는 continue
                if (maps[i][j] == maps[i][j + 1]) {
                    continue;
                }
                
                // 높이 차가 1 초과하는 경우
                if (Math.abs(maps[i][j] - maps[i][j + 1]) > 1) {
                    roadState = false;
                    break;
                }
                
                // 내리막길 확인
                if (maps[i][j] > maps[i][j + 1]) {
                    for (int k = j + 1; k <= j + stairLen; k++) {
                        if (k >= maps.length || installed[k] || maps[i][j + 1] != maps[i][k]) {
                            roadState = false;
                            break;
                        }
                        installed[k] = true;
                    }
                } else if (maps[i][j] < maps[i][j + 1]) { // 오르막길 확인
                    for (int k = j; k > j - stairLen; k--) {
                        if (k < 0 || installed[k] || maps[i][j] != maps[i][k]) {
                            roadState = false;
                            break;
                        }
                        installed[k] = true;
                    }
                }

                if (!roadState) {
                    break;
                }
            }

            if (roadState) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // Test code
        int[][] maps = {{2, 2, 2, 2, 2, 2},
                {2, 2, 3, 3, 3, 3},
                {2, 2, 2, 3, 2, 3},
                {2, 1, 1, 1, 1, 2},
                {2, 1, 1, 3, 3, 1},
                {2, 1, 2, 3, 3, 2}};
        System.out.println(solution(maps, 2));
        System.out.println(solution(maps, 3));
    }
}

package com.datastructurealgorithm.기출문제풀이.기출1;

import java.util.ArrayList;

public class Practice3 {
    final static int CLEAN_ROAD = 0;
    final static int WALL = 1;
    final static int WATER = 2;

    final static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int n;
    static int m;
    static Integer[][] roadsBak;
    static ArrayList<int[]> waterPoints;
    static int max = 0;

    public static int solution(int[][] roads) {
        m = roads.length;
        n = roads[0].length;
        roadsBak = new Integer[m][n];
        waterPoints = new ArrayList<>();
        max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (roads[i][j] == WATER) {
                    waterPoints.add(new int[]{j, i});
                }
            }
        }

        dfs(roads, 0);
        return max;
    }

    static void dfs(int[][] roads, int depth) {
        if (depth == 3) {
            // 물이 어떻게 퍼지는지 테스트하기 위한 배열 복사
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    roadsBak[i][j] = roads[i][j];
                }
            }

            // 물이 퍼지는 것 시뮬레이션
            for (int[] point : waterPoints) {
                waterSimulation(point[0], point[1]);
            }

            // 물이 퍼지지 않은 영역 카운트
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (roadsBak[i][j] == CLEAN_ROAD) {
                        cnt++;
                    }
                }
            }

            max = Math.max(max, cnt);
            return;
        }

        // dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (roads[i][j] == CLEAN_ROAD) {
                    roads[i][j] = WALL;
                    dfs(roads, depth + 1);
                    roads[i][j] = CLEAN_ROAD;
                }
            }
        }
    }

    static void waterSimulation(int x, int y) {
        for (int[] dir : dirs) {
            int xNext = x + dir[0];
            int yNext = y + dir[1];

            if (0 <= xNext && xNext < n && 0 <= yNext && yNext < m) {
                if (roadsBak[yNext][xNext] == CLEAN_ROAD) {
                    roadsBak[yNext][xNext] = WATER;
                    waterSimulation(xNext, yNext);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test code
        int[][] roads = {{0, 0, 0},
                         {1, 2, 0},
                         {0, 0, 0}};
        System.out.println(solution(roads));

        roads = new int[][]{{2, 0, 0, 0, 1, 1, 0},
                            {0, 0, 1, 0, 1, 0, 0},
                            {0, 1, 1, 0, 1, 0, 0},
                            {0, 1, 0, 1, 1, 0, 0},
                            {0, 0, 0, 0, 0, 1, 1},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 1, 2, 0}};
        System.out.println(solution(roads));
    }
}

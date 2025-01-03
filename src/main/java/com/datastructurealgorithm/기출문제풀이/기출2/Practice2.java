package com.datastructurealgorithm.기출문제풀이.기출2;

public class Practice2 {
    final static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    final static int UNEXPLORED = 0;
    final static int WALL = 1;
    final static int EXPLORED = 2;

    public static int solution(int[][] map, int[] startPos) {
        int r = startPos[0];
        int c = startPos[1];
        int d = startPos[2];
        int turnCnt = 0;
        int result = 0;

        while (true) {
            if (turnCnt == 4) {
                // 2번 연속 4번 진행한 경우: 한칸 후진 or 벽이면 탐험 종료
                r -= dirs[d][1];
                c -= dirs[d][0];
                turnCnt = 0;

                if (map[r][c] == WALL) {
                    break;
                }
            }

            // 현재 위치 탐험
            if (map[r][c] == UNEXPLORED) {
                map[r][c] = EXPLORED;
                result++;
            }

            // 왼쪽으로 회전
            d = (d + 3) % 4;
            int xNext = c + dirs[d][0];
            int yNext = r + dirs[d][1];
            // 최외곽 지역이 1로 벽이므로 그쪽으로 이동할 경우가 없으므로 boundary 체크 안해줘도 무방
            if (map[yNext][xNext] == UNEXPLORED) {
                // 미탐험 지역이면 한칸 전진
                r = yNext;
                c = xNext;
                turnCnt = 0;
            } else {
                // 이미 탐험했거나 벽인 경우
                // turn 수 카운트 -> 4번 연속 진행했는지 확인 용
                turnCnt++;
            }
        }

        return result;
    }

    static int sol2Result;
    public static int solution2(int[][] map, int[] startPos) {
        int r = startPos[0];
        int c = startPos[1];
        int d = startPos[2];

        sol2Result = 0;
        dfs(map, r, c, d);
        return sol2Result;
    }

    public static void dfs(int[][] map, int r, int c, int d) {
        if (map[r][c] == UNEXPLORED) {
            map[r][c] = EXPLORED;
            sol2Result++;
        }

        for (int i = 0; i < 4; i++) {
            // 왼쪽 회전
            d = (d + 3) % 4;
            int xNext = c + dirs[d][0];
            int yNext = r + dirs[d][1];

            // 미탐험 지역이면 dfs
            if (map[yNext][xNext] == UNEXPLORED) {
                dfs(map, yNext, xNext, d);
                // 다른 곳을 탐험하고 와서 돌아온 뒤 진행하는 형태는 이 문제 조건에서는 맞지 않으므로 return
                return;
            }
        }

        // 2번 연속 4번 진행된 경우 후진 체크
        c -= dirs[d][0];
        r -= dirs[d][1];
        if (map[r][c] != WALL) {
            dfs(map, r, c, d);
        }
    }

    public static void main(String[] args) {
        // Test code
        int[][] map = {{1, 1, 1, 1, 1},
                       {1, 0, 0, 0, 1},
                       {1, 0, 0, 0, 1},
                       {1, 0, 0, 0, 1},
                       {1, 1, 1, 1, 1}};
        int[] startPos = {1, 1, 0};
        System.out.println(solution(map, startPos));

        map = new int[][] { {1, 1, 1, 1, 1},
                            {1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1},
                            {1, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1}};
        System.out.println(solution2(map, startPos));

        System.out.println();
        map = new int[][]{  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        startPos = new int[]{1, 1, 2};
        System.out.println(solution(map, startPos));

        map = new int[][]{  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        System.out.println(solution2(map, startPos));
    }
}

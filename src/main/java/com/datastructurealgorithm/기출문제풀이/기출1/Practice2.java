package com.datastructurealgorithm.기출문제풀이.기출1;

public class Practice2 {
    // O(N)
    public static int solution(int n, int[] days, int[] prices) {
        // dp 테이블 구성
        int[] dp = new int[n + 2];

        for (int i = n; i > 0; i--) {
            // i 번째 날에서 필요한 개발 기간
            int day = i + days[i - 1];
            
            // 기간 내에 개발 가능한 경우
            if (day <= n + 1) {
                dp[i] = Math.max(dp[day] + prices[i - 1], dp[i + 1]);
            } else {
                // 불가능한 경우
                dp[i] = dp[i + 1];
            }
        }

        return dp[1];
    }

    // O(V+E)
    static int _n;
    static int[] _days;
    static int[] _prices;
    static int result;
    public static int solution2(int n, int[] days, int[] prices) {
        _n = n;
        _days = days.clone();
        _prices = prices.clone();

        dfs(0, 0);
        return result;
    }

    public static void dfs(int idx, int price) {
        if (idx >= _n) {
            result = Math.max(result, price);
            return;
        }

        // 기간 내에 개발 가능한 경우
        if (idx + _days[idx] <= _n) {
            dfs(idx + _days[idx], price + _prices[idx]);
        } else {
            // 불가능한 경우
            dfs(idx + _days[idx], price);
        }

        dfs(idx + 1, price);
    }

    public static void main(String[] args) {
        // Test code
        int[] days = {2, 1, 3, 2, 2};
        int[] prices = {10, 20, 30, 40, 60};
        System.out.println(solution(5, days, prices));
        System.out.println(solution2(5, days, prices));

        System.out.println();
        days = new int[]{3, 3, 3, 1, 2, 3, 2, 2};
        prices = new int[]{50, 150, 20, 30, 10, 10, 30, 30};
        System.out.println(solution(8, days, prices));
        System.out.println(solution2(8, days, prices));
    }
}

package dynamic_programming;

/**
 1차 시도(실패) - 2022.01.14.

 */
public class Hard_N으로_표현 {

    /**

     제한사항
     1 <= num <= 32000

     first
     제곱
     n이 num의 약수

     second
     배수, 약수를 이용해 num에 가까워진 값과의 거리를 고려

     */

    public static void main(String[] args) {
        Hard_N으로_표현 solution = new Hard_N으로_표현();
        System.out.println(solution.solution(5,12));
        System.out.println(solution.solution(2,11));
    }

    public int solution(int n, int number) {
        int solution = n;
        int one = n == 1 ? 1 : 2; // 1을 표현하기 위해 필요한 n의 개수
        int squared = 0;
        while (true) {
            Pair square = square(n*(solution+1), number-squared);
            if(square.first == 0) break;
            solution += square.first + 1;
            squared += square.second;
        }
        System.out.println(solution);
        solution+= div(n, number);
        return solution;
    }

    int div(int n, int number) {
        int solution = n;
        int one = n == 1 ? 1 : 2; // 1을 표현하기 위해 필요한 n의 개수
        int mod = number%n;
        solution += number/n;
        solution += mod*one;
        return solution;
    }

    // squaredTimes, squared
    Pair square(int n, int number) {
        int squared = n;
        int squaredTimes = 0;
        while (squared < number) {
            squared *= squared;
            squaredTimes += 1;
        }
        return new Pair(squaredTimes, squared);
    }

    static class Pair {
        int first, second;

        public Pair(){}
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}

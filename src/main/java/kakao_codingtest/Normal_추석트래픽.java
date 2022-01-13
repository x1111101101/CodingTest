package kakao_codingtest;

/**
 https://programmers.co.kr/learn/courses/30/lessons/17676
 2018 카카오 블라인드 1차 코딩테스트 - 정답률: 17.99%

 푼 날짜: 2021.11.21.
 */
public class Normal_추석트래픽 {

    public static void main(String[] args) {
        int v =new Solution().solution(new String[]{"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"});
        System.out.println(v);
    }

    static class Solution {
        public int solution(String[] lines) {
            long[][] times = new long[lines.length][2];
            for(int i = 0; i < lines.length; i++) {
                String log = lines[i];
                String[] sp = log.substring(11, log.length()-1).replaceAll(":|\\s", ".").split("\\.");
                long endTime = Integer.parseInt(sp[0]) * 3600000L
                        + Integer.parseInt(sp[1]) * 60000L
                        + Integer.parseInt(sp[2]) * 1000L
                        + Integer.parseInt(sp[3]);
                long startTime = endTime - Integer.parseInt(sp[4]) * 1000;
                if(sp.length > 5) {
                    startTime -= Integer.parseInt(sp[5]);
                }
                times[i][0] = startTime;
                times[i][1] = endTime;
            }
            long min = Long.MAX_VALUE;
            long max = times[times.length-1][1];
            for(long[] time : times) {
                if(time[0] < min) {
                    min = time[0];
                }
            }
            int maxCount = 0;
            int startIndex = 0;

            while(min <= max) {
                int count = 0;
                long end = min+1000;
                for (int i = startIndex; i < times.length; i++) {
                    long[] time = times[i];
                    if(time[0] < end && time[1] > min) {
                        count++;
                    }
                    if(min-3000 > time[0]) {
                        startIndex = i;
                    }
                    if(time[1] > end+3000) {
                        break;
                    }
                }
                if(count > maxCount) {
                    maxCount = count;
                }
                min++;
            }
            return maxCount;
        }
    }
}

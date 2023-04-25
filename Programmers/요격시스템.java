import java.util.*;

class Solution {
  public int solution(int[][] t) {
    //빨리 시작하는 순으로 정렬
    Integer[][] targets = new Integer[t.length][t[0].length];
    for (int i = 0; i < targets.length; i++) {
      for (int u = 0; u < targets[i].length; u++) {
        targets[i][u] = t[i][u];
      }
    }
    Arrays.sort(targets, (a, b) -> a[0] - b[0]);

    //계산
    int answer = 0;
    int countMissiles = 0; //한번에 제거할 폭격 미사일 개수
    int minEnd = 100000001; //현재까지 살펴본 끝나는 시간 중 최솟값
    for (Integer[] target : targets) {
      int s = target[0];
      int e = target[1];

      if (minEnd <= s) { //만약 현재까지 살펴본 끝나는 시간 중 최솟값보다 현재 시작 시간이 더 느리거나 같다면
        answer++;
        minEnd = e;
        countMissiles = 1; //한번에 폭격 미사일 제거 후, 현재 미사일 추가

      } else { //만약 현재까지 살펴본 끝나는 시간 중 최솟값보다 현재 시작 시간이 더 빠르다면
        minEnd = Math.min(minEnd, e);
        countMissiles++; //한번에 제거할 수 있는 폭격 미사일 추가
      }
    }

    if (countMissiles != 0) { //아직 제거할 폭격 미사일이 남았다면
      answer++;
    }

    return answer;
  }
}
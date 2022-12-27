/**
 * d[i] = i개의 N으로 만들 수 있는 숫자 Set
 */

import java.util.*;

class Solution {
  HashSet<Integer> d[];

  public int solution(int N, int number) {
    d = new HashSet [9];
    for (int i = 0; i < 9; i++) {
      d[i] = new HashSet<Integer>();
    }

    d[1].add(N);

    //DP
    for (int i = 2; i < 9; i++) {
      HashSet<Integer> currentSet = d[i];

      /**
       * i=3 인 경우, d[3] = (d[1] +-/* d[2]) + (d[2] +-/* d[1]) + 'NNN'
       * i=4 인 경우, d[4] = (d[1] +-/* d[3]) + (d[2] +-/* d[2]) + (d[3] +-/* d[1]) + 'NNNN'
       */
      for (int u = 1; u < i; u++) {
        HashSet<Integer> frontSet = d[u];
        HashSet<Integer> rearSet = d[i-u];

        for (int frontNum : frontSet) {
          for (int rearNum : rearSet) {
            currentSet.add(frontNum + rearNum);
            currentSet.add(frontNum - rearNum);
            currentSet.add(frontNum * rearNum);
            if (rearNum != 0) {
              currentSet.add(frontNum / rearNum);
            }
          }
        }
      }

      currentSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
    }

    for (int i = 1; i < 9; i++) {
      if (d[i].contains(number)) return i;
    }

    return -1;
  }
}
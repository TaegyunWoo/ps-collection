import java.util.*;

class Solution {
  List<int[]> answerList = new ArrayList<>();
  int maxScoreGap = 0;

  public int[] solution(int n, int[] info) {
    int[] result = new int[11];

    brute(result, 0, info, n);

    if (answerList.size() == 0) return new int[] {-1};
    return getAnswer();
  }

  private void brute(int[] result, int depth, int[] info, int n) {
    if (n == 0) {
      //결과 저장 (최대 점수 차인지, 우승하는 경우인지 고려해서 저장)
      save(result, info);
      return;
    }
    if (depth == 11) return;

    for (int i = n; i >= 0; i--) {
      result[depth] = i;
      brute(result, depth + 1, info, n - i);
    }
  }

  private void save(int[] result, int[] info) {
    int apeachScore = 0;
    int lionScore = 0;

    for (int i = 0; i < 11; i++) {
      if (result[i] == 0 && info[i] == 0) continue;
      if (result[i] > info[i]) lionScore += 10 - i;
      else apeachScore += 10 - i;
    }

    if (lionScore <= apeachScore) return; //이기지 않는 경우
    if (maxScoreGap > lionScore - apeachScore) return; //최대 점수 차가 아닌 경우

    //최대 점수 차가 더 큰 경우
    if (maxScoreGap < lionScore - apeachScore) {
      maxScoreGap = lionScore - apeachScore;
      answerList.clear();
    }

    int[] tmp = new int[11];
    for (int i = 0; i < 11; i++) {
      tmp[i] = result[i];
    }
    answerList.add(tmp);
  }

  private int[] getAnswer() {
    answerList.sort((a, b) -> {
      for (int i = 10; i >= 0; i--) {
        if (a[i] > b[i]) return -1;
        else if (a[i] < b[i]) return 1;
      }
      return 0;
    });

    return answerList.get(0);
  }
}
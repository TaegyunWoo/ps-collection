import java.util.*;

class Solution {
  public String[] combination = new String[4];
  public HashMap<String, List<Integer>> scoreMap = new HashMap<>(); //<조건,점수>
  public int[] answer;

  public int[] solution(String[] infoAry, String[] queryAry) {
    for (String info : infoAry) {
      String[] parsedInfo = info.split(" ");
      combi(parsedInfo, 0);
    }
    sortAll();

    answer = new int[queryAry.length];
    int answerIdx = 0;
    for (String query : queryAry) {
      String[] parsedQuery = query.split(" ");
      StringBuilder key = new StringBuilder();
      key.append(parsedQuery[0]);
      key.append(parsedQuery[2]);
      key.append(parsedQuery[4]);
      key.append(parsedQuery[6]);
      int scoreQuery = Integer.parseInt(parsedQuery[7]);

      answer[answerIdx++] = getApplicantNum(key.toString(), scoreQuery);
    }

    return answer;
  }

  private void combi(String[] info, int depth) {
    if (depth == 4) {
      //info 추가
      addScore(Integer.parseInt(info[4]));
      return;
    }

    combination[depth] = "-";
    combi(info, depth+1);
    combination[depth] = info[depth];
    combi(info, depth+1);
  }

  private void addScore(int score) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < combination.length; i++) {
      sb.append(combination[i]);
    }
    String key = sb.toString();
    if (!scoreMap.containsKey(key)) {
      scoreMap.put(key, new ArrayList<>());
    }
    scoreMap.get(key).add(score);
  }

  private void sortAll() {
    for (String key : scoreMap.keySet()) {
      scoreMap.get(key).sort(Comparator.naturalOrder());
    }
  }

  //이진탐색 : Low Bound 활용
  private int getApplicantNum(String key, int scoreLimit) {
    List<Integer> scoreList = scoreMap.get(key);
    if (scoreList == null) return 0;
    int min = 0;
    int max = scoreList.size();
    int mid = (min+max)/2;
    while (min < max) {
      if (scoreList.get(mid) >= scoreLimit) {
        max = mid;
      } else {
        min = mid + 1;
      }
      mid = (min+max)/2;
    }

    return scoreList.size() - min;
  }
}
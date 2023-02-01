import java.util.*;

class Solution {
  HashMap<Character, Integer> scoreMap = new HashMap<>();

  public String solution(String[] survey, int[] choices) {
    initScoreMap();

    for (int i = 0; i < survey.length; i++) {
      scoring(survey[i], choices[i]);
    }

    return getAnswer();
  }

  private void initScoreMap() {
    scoreMap.put('R', 0);
    scoreMap.put('T', 0);
    scoreMap.put('C', 0);
    scoreMap.put('F', 0);
    scoreMap.put('J', 0);
    scoreMap.put('M', 0);
    scoreMap.put('A', 0);
    scoreMap.put('N', 0);
  }

  private void scoring(String types, int answer) {
    char type = '0';
    int score = answer - 4;

    if (score < 0) {
      type = types.charAt(0);
    } else if (score > 0) {
      type = types.charAt(1);
    } else {
      return;
    }

    score = Math.abs(score);
    scoreMap.put(type, scoreMap.get(type) + score);
  }

  private String getAnswer() {
    String answer = "";
    int r = scoreMap.get('R');
    int t = scoreMap.get('T');
    int c = scoreMap.get('C');
    int f = scoreMap.get('F');
    int j = scoreMap.get('J');
    int m = scoreMap.get('M');
    int a = scoreMap.get('A');
    int n = scoreMap.get('N');

    if (r < t) answer = answer + "T";
    else answer = answer + "R";
    if (c < f) answer = answer + "F";
    else answer = answer + "C";
    if (j < m) answer = answer + "M";
    else answer = answer + "J";
    if (a < n) answer = answer + "N";
    else answer = answer + "A";

    return answer;
  }
}
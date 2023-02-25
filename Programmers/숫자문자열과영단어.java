import java.util.*;

class Solution {
  HashMap<String, Integer> engMap = new HashMap<>();

  public int solution(String s) {
    StringBuilder answerBuilder = new StringBuilder();
    StringBuilder num = new StringBuilder();

    initEngMap();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c < '0' || c > '9') { //숫자가 아닌 경우
        num.append(c);
        String numString = num.toString();
        if (engMap.containsKey(numString)) {
          answerBuilder.append(engMap.get(numString));
          num.delete(0, num.length());
        }

      } else { //숫자인 경우
        answerBuilder.append(c);
      }
    }

    return Integer.parseInt(answerBuilder.toString());
  }

  private void initEngMap() {
    engMap.put("zero", 0); engMap.put("one", 1);
    engMap.put("two", 2); engMap.put("three", 3);
    engMap.put("four", 4); engMap.put("five", 5);
    engMap.put("six", 6); engMap.put("seven", 7);
    engMap.put("eight", 8); engMap.put("nine", 9);
  }
}
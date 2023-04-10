import java.util.*;

class Solution {
  List<String> answerList = new ArrayList<>();

  public String[] solution(String[] s) {
    for (String originalString : s) {
      String removed110String = delete110(originalString);
      int count110 = (originalString.length() - removed110String.length()) / 3;
      String result = add110(removed110String, count110);

      answerList.add(result);
    }

    String[] answer = new String[answerList.size()];
    for (int i = 0; i < answer.length; i++) {
      answer[i] = answerList.get(i);
    }

    return answer;
  }

  private String delete110(String item) {
    StringBuilder sb = new StringBuilder(item);
    int count = 0;

    for (int i = 0; i < sb.length() - 2; i++) {
      if (sb.charAt(i) == '1' && sb.charAt(i+1) == '1' && sb.charAt(i+2) == '0') {
        count++;
        sb.deleteCharAt(i);
        sb.deleteCharAt(i);
        sb.deleteCharAt(i);
        if (i < 2) i = -1;
        else i -= 3;
      }
    }

    return sb.toString();
  }

  private String add110(String removed110String, int count110) {
    StringBuilder result = new StringBuilder(removed110String);
    StringBuilder appendix = new StringBuilder();
    int insertPointer = 0;

    for (int i = 0; i < result.length(); i++) {
      if (result.charAt(i) == '0') insertPointer = i+1;
    }

    for (int i = 0; i < count110; i++) {
      appendix.append("110");
    }

    result.insert(insertPointer, appendix);

    return result.toString();
  }
}
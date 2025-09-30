import java.util.*;

class Solution {
  public String solution(long n, String[] bans) {
    //사전순 정렬
    Arrays.sort(bans, (a, b) -> {
      if (a.length() == b.length()) return a.compareTo(b);
      else if (a.length() < b.length()) return -1;
      else return 1;
    });

    // 삭제된 주문서를 고려해서 n을 보정 (건너뛴 주문 개수만큼 n 증가)
    for (String ban : bans) {
      long numOfString = calNumOfString(ban);
      if (numOfString <= n) n++;
    }

    return calStringOfNum(n);
  }

  private String calStringOfNum(long num) {
    StringBuilder result = new StringBuilder();

    while (num > 0) {
      long currentNum = num % 26 == 0 ? 26 : num % 26;
      result.insert(0, calCharOfNum((int) currentNum));
      num = currentNum == 26 ? num / 26 - 1 : num / 26;
    }

    return result.toString();
  }

  private char calCharOfNum(int num) {
    return (char) (num + 'a' - 1);
  }

  private long calNumOfString(String s) {
    long result = 0;

    long powerOf26 = 1;
    for (int i = s.length() - 1; i >= 0; i--) {
      long charNum = calNumOfChar(s.charAt(i));
      result += charNum * powerOf26;
      powerOf26 *= 26;
    }

    return result;
  }

  private int calNumOfChar(char c) {
    return c - 'a' + 1;
  }
}
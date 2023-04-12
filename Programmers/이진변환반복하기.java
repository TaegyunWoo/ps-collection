class Solution {
  public int[] solution(String s) {
    if (s.equals("1")) return new int[] {0, 0};

    //s의 1 개수 구하기
    int countOne = getCountOne(s);

    //정답 구하기
    int countTrans = 1;
    int countRemovedZero = s.length() - countOne;
    while (countOne != 1) {
      int[] result = getTransResult(countOne);
      int countTotal = result[0];
      countOne = result[1];

      countTrans++; //변환 횟수 증가
      countRemovedZero += countTotal - countOne; //제거한 0 개수 세기
    }

    return new int[] {countTrans, countRemovedZero};
  }

  private int getCountOne(String s) {
    int countOne = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '1') countOne++;
    }
    return countOne;
  }

  private int[] getTransResult(int oneLength) {
    int countOne = 0;
    int countTotal = 0;

    while (oneLength > 0) {
      if (oneLength % 2 == 1) countOne++;
      oneLength /= 2;
      countTotal++;
    }

    return new int[] {countTotal, countOne};
  }
}
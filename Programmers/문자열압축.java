class Solution {
  static final int MAX = (int) 1e9;

  public int solution(String s) {
    int answer = MAX;

    for (int unitSize = 1; unitSize <= s.length(); unitSize++) {
      int result = getLength(s, unitSize);
      answer = Math.min(answer, result);
    }

    return answer;
  }

  private int getLength(String s, int unitSize) {
    StringBuilder result = new StringBuilder();
    String beforeSubstring = "";
    int beforeCount = 0;

    for (int start = 0; start <= s.length() - unitSize; start += unitSize) {
      int end = start + unitSize;
      String substring = s.substring(start, end);

      if (beforeSubstring.equals(substring)) { //만약 이전 substring과 동일하다면
        beforeCount++;

      } else { //만약 이전 substring과 다르다면
        //이전 substring 정보 append
        if (beforeCount != 0) { //초기값이 아니고
          if (beforeCount != 1) { //하나만 존재하는 것이 아니라면
            result.append(beforeCount); //개수 정보 append
          }
          result.append(beforeSubstring);
        }
        //갱신
        beforeCount = 1;
        beforeSubstring = substring;
      }
    } //for문 종료

    //마지막 substring 추가
    if (beforeCount != 1) { //하나만 존재하는 것이 아니라면
      result.append(beforeCount); //개수 정보 append
    }
    result.append(beforeSubstring);

    //나머지 추가
    if (s.length() % unitSize != 0) { //만약 unitSize가 s 길이의 배수가 아니라면
      String leastSubstring = s.substring(s.length() - (s.length() % unitSize), s.length());
      result.append(leastSubstring);
    }

    return result.length();
  }
}
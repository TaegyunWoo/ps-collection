/**
 * 커서 이동 경우의 수
 * 1. 처음부터 오른쪽으로 쭉 이동
 * 2. 처음부터 왼쪽으로 쭉 이동
 * 3. 처음부터 '연속된 A가 없는 곳'까지 쭉 오른쪽으로 이동 후, 다시 '연속된 A가 없는 곳'까지 왼쪽으로 쭉 이동
 * 4. 처음부터 '연속된 A가 없는 곳'까지 쭉 왼쪽으로 이동 후, 다시 '연속된 A가 없는 곳'까지 오른쪽으로 쭉 이동
 */
class Solution {
  public int solution(String name) {
    int moveCount = (int) 1e9;
    int alphCount = 0;
    for (int i = 0; i < name.length(); i++) {
      char currentChar = name.charAt(i);

      //글자 변경 횟수 count
      alphCount += Math.min(currentChar - 'A', - (currentChar - 'Z' - 1));

      //이동 횟수 count
      if (i < name.length() - 1 && name.charAt(i+1) == 'A') { //만약 다음 문자가 A라면
        int aEndIndex = i + 1;
        while (aEndIndex < name.length() && name.charAt(aEndIndex) == 'A')
          aEndIndex++;
        moveCount = Math.min(moveCount, (i * 2) + name.length() - aEndIndex); //3번 케이스
        moveCount = Math.min(moveCount, (name.length() - aEndIndex)*2 + i); //4번 케이스
      }
    }

    moveCount = Math.min(moveCount, name.length() - 1); //1,2번 케이스 고려

    return alphCount + moveCount;
  }
}
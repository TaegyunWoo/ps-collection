import java.util.*;

class Solution {
  public int solution(int[] citations) {
    //내림차순 정렬
    int[] nums = Arrays.stream(citations).map(i -> -i)
        .sorted().map(i -> -i).toArray();

    //최대 인용수부터 h 조건을 만족하는지 확인
    for (int h = nums[0]; h >= 0; h--) {
      if ( isH(h, nums) ) return h;
    }

    return 0;
  }

  /*
   * 현재 index를 h로 두었을 때, h 조건을 만족하는지 확인하는 메서드
   * return: (true : h조건 만족), (false : h조건 만족X)
   */
  private boolean isH(int h, int[] citations) {
    int greaterCount = 0; //현재 index가 h일 때, h보다 크거나 같은 수의 개수
    int smallerCount = 0; //현재 index가 h일 때, h보다 작거나 같은 수의 개수

    for (int i = 0; i < citations.length; i++) {
      int num = citations[i];
      if (num >= h) greaterCount++;
      if (num < h) smallerCount++;
    }

    if (greaterCount >= h && smallerCount == citations.length - greaterCount) return true;

    return false;
  }
}
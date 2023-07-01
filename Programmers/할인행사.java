/** 완전탐색 (가장 단순한 풀이 : O(10000000)) **/
class Solution {
  public int solution(String[] want, int[] number, String[] discount) {
    int answer = 0;

    for (int startDate = 0; startDate <= discount.length - 10; startDate++) {
      boolean result = canSignUp(discount, startDate, want, number);
      if (result) answer++;
    }

    return answer;
  }

  private boolean canSignUp(String[] discount, int startDate, String[] want, int[] number) {
    //희망 아이템마다 반복
    for (int i = 0; i < want.length; i++) {
      String item = want[i];
      int num = number[i];
      int count = 0;

      //date부터 10일 반복
      for (int u = startDate; u < startDate + 10; u++) {
        if (item.equals(discount[u])) count++;
      }

      //원하는 개수만큼 구매할 수 없다면
      if (num > count) return false;
    }

    //모든 아이템을 원하는 개수만큼 구매할 수 있다면
    return true;
  }
}
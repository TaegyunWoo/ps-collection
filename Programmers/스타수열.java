import java.util.*;

class Solution {
  public int solution(int[] a) {
    //숫자 정보 리스트 구하기
    List<NumInfo> numInfoList = getNumInfoList(a);

    //정답 구하기
    int answer = -1;
    for (NumInfo numInfo : numInfoList) {
      int num = numInfo.num;
      int maxLength = numInfo.count * 2;
      int resultLength = 0;

      if (maxLength <= answer) continue;

      for (int i = 0; i < a.length - 1; i++) {
        if (a[i] == a[i+1]) continue;

        //만약 해당 숫자를 가지고 있다면
        if (a[i] == num || a[i+1] == num) {
          resultLength += 2;
          i++;
        }
      }

      answer = Math.max(answer, resultLength);
    }

    return answer;
  }

  private List<NumInfo> getNumInfoList(int[] a) {
    List<NumInfo> numInfoList = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      numInfoList.add(new NumInfo(i, 0));
    }
    for (int i = 0; i < a.length; i++) {
      numInfoList.get(a[i]).count += 1;
    }

    return numInfoList;
  }

  class NumInfo {
    public int num;
    public int count;
    public NumInfo(int num, int count) {
      this.num = num;
      this.count = count;
    }
  }
}
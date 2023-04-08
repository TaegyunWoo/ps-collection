import java.util.*;

class Solution {
  List<Integer> targetOneIndexList = new ArrayList<>();
  List<Long> answerList = new ArrayList<>();

  public long[] solution(long[] numbers) {

    for (int i = 0; i < numbers.length; i++) {
      long number = numbers[i];
      StringBuilder transedNum = trans(number);

      if (transedNum.charAt(transedNum.length()-1) == '0') { //마지막이 0이면
        transedNum.setCharAt(transedNum.length()-1, '1');

      } else { //마지막이 1이면
        int targetOneIndex = targetOneIndexList.get(i);

        if (targetOneIndex != transedNum.length()) {
          transedNum.setCharAt(targetOneIndex, '0');
          transedNum.setCharAt(targetOneIndex-1, '1');

        } else {
          transedNum.setCharAt(0, '0');
          transedNum.insert(0, '1');
        }
      }

      answerList.add(trans(transedNum)); //정답 추가
    }

    return answerList.stream().mapToLong(i->i).toArray();
  }

  //이진수로 변환
  private StringBuilder trans(long num) {
    StringBuilder result = new StringBuilder();
    boolean isZero = false;
    int firstZeroLength = 0;

    if (num == 0) {
      targetOneIndexList.add(1);
      return new StringBuilder("0");
    }

    while (num > 0) {
      if (num%2 == 0 && !isZero) { //처음 0이 나온 크기 지점 저장
        firstZeroLength = result.length();
        isZero = true;
      }

      result.insert(0, num%2);
      num /= 2;
    }

    targetOneIndexList.add(result.length() - firstZeroLength); //뒤에서부터 처음나온 0 뒤에 있는 1의 위치

    return result;
  }

  //십진수로 변환
  private long trans(StringBuilder transedNum) {
    long result = 0;
    long tmp = 1;
    for (int i = transedNum.length()-1; i >= 0; i--) {
      char c = transedNum.charAt(i);

      if (c == '1') {
        result += tmp;
      }

      tmp *= 2;
    }

    return result;
  }


}
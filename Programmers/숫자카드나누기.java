import java.util.*;

class Solution {
  public int solution(int[] arrayA, int[] arrayB) {
    //sort
    Arrays.sort(arrayA);
    Arrays.sort(arrayB);

    //GCD
    int gcdA = getGCDItemValue(arrayA);
    int gcdB = getGCDItemValue(arrayB);

    //check
    boolean divideWithGcdA = cannotAllItemDivide(gcdA, arrayB);
    boolean divideWithGcdB = cannotAllItemDivide(gcdB, arrayA);

    //answer
    if (divideWithGcdA && divideWithGcdB) return Math.max(gcdA, gcdB);
    else if (divideWithGcdA) return gcdA;
    else if (divideWithGcdB) return gcdB;
    else return 0;
  }

  //모든 원소간의 최대공약수 구하기
  private int getGCDItemValue(int[] array) {
    HashSet<Integer> allDivisorSet = new HashSet<>();
    for (int i = 1; i <= Math.sqrt(array[0]); i++) {
      if (array[0] % i == 0) {
        allDivisorSet.add(i);
        allDivisorSet.add(array[0] / i);
      }
    }

    int result = 0;
    for (int divisor : allDivisorSet) {
      boolean canDivideAll = true;
      for (int i = 1; i < array.length; i++) {
        if (array[i] % divisor != 0) {
          canDivideAll = false;
          break;
        }
      }
      if (canDivideAll) {
        result = Math.max(result, divisor);
      }
    }

    return result;
  }

  //true: 모두 나눠지지 않음, false: 하나라도 나눠지는 원소 존재
  private boolean cannotAllItemDivide(int divisor, int[] array) {
    for (int item : array) {
      if (item % divisor == 0) return false;
    }
    return true;
  }
}
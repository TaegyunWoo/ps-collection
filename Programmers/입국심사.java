import java.util.*;
import java.math.*;

class Solution {
  public long solution(int n, int[] times) {
    BigInteger max = new BigInteger("1000000000").multiply(new BigInteger("1000000000"));
    BigInteger min = new BigInteger("1");
    BigInteger mid = max.add(min).divide(BigInteger.TWO);
    BigInteger bigIntegerN = BigInteger.valueOf(n);
    BigInteger answer = new BigInteger("1000000000").multiply(new BigInteger("1000000000"));

    //max가 min보다 크거나 같을 때까지 반복
    while (max.compareTo(min) != -1) {
      BigInteger peopleCount = countPeople(mid, times);

      if (peopleCount.compareTo(bigIntegerN) != -1) { //peopleCount가 n보다 더 큰거나 같은 경우
        answer = answer.min(mid).add(BigInteger.ZERO);
        max = mid.subtract(BigInteger.ONE);

      } else { //peopleCount가 n보다 더 작은 경우
        min = mid.add(BigInteger.ONE);
      }

      mid = max.add(min).divide(BigInteger.TWO);
    }

    return Long.parseLong(answer.toString());
  }

  private BigInteger countPeople(BigInteger minutes, int[] times) {
    BigInteger count = BigInteger.ZERO;
    for (int time : times) {
      count = count.add(minutes.divide(BigInteger.valueOf(time)));
    }
    return count;
  }
}
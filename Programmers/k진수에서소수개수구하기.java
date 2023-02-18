import java.util.*;

class Solution {
  public int solution(int n, int k) {
    //k진수로 변환
    String totalNum = transport(n, k);

    //판별할 숫자 구하기 (0으로 파싱)
    List<String> numList = getNums(totalNum);

    //소수 세기
    int answer = countPrime(numList);

    return answer;
  }

  private String transport(int n, int k) {
    StringBuilder result = new StringBuilder();

    while (n > 0) {
      result.insert(0, n % k);
      n /= k;
    }

    return result.toString();
  }

  private List<String> getNums(String totalNum) {
    List<String> result = new ArrayList<>();
    String[] numsAry = totalNum.split("0");

    for (String num : numsAry) {
      if (num.isBlank()) continue;
      result.add(num);
    }

    return result;
  }

  private int countPrime(List<String> numList) {
    int count = 0;

    for (String item : numList) {
      long num = Long.parseLong(item);
      double limit = Math.sqrt(num); //루트num 까지만 확인하면 됨
      boolean isPrime = true;

      if (num == 1) continue;

      for (int i = 2; i <= limit; i++) {
        if (num % i == 0) {
          isPrime = false;
          break;
        }
      }

      if (isPrime) count++;
    }

    return count;
  }
}
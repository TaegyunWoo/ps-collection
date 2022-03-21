import java.io.*;
import java.util.*;
import java.math.*;

/**
 * [공식 1]
 * a^{b} = (a^{b/2})^2 (단, b가 짝수일때)
 * a^{b} = (a^{b/2})^2 * a (단, b가 홀수일때)
 *
 * [공식 2]
 * (a * b) % c = ((a % c) * (a % c)) % c
 *
 * [풀이]
 * - 본 문제를 제한시간 안에 풀기 위해서는 아래 두 개의 공식을 활용해야한다.
 * - b가 1이 될 때까지, 공식 1을 재귀적으로 사용한다. 이를 통해, 곱하기 연산횟수를 줄일 수 있다.
 *   예시) 10^10 구하기
 *        10^10 => 곱하기 9번
 *        (10^5)^2 => 곱하기 5번
 *        ((10^2)^2 * 10)^2 => 곱하기 4번
 * - 공식1을 재귀적으로 사용할 때마다, 공식2를 적용한다.
 */
public class Q1629 {

  static long a;
  static long b;
  static long c;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    BigInteger a = BigInteger.valueOf(Integer.parseInt(st.nextToken()));
    BigInteger b = BigInteger.valueOf(Integer.parseInt(st.nextToken()));
    BigInteger c = BigInteger.valueOf(Integer.parseInt(st.nextToken()));

    System.out.print(pow(a, b, c));
  }

  private static BigInteger pow(BigInteger a, BigInteger b, BigInteger c) {
    if (b.toString().equals("1")) {
      return a.mod(c);
    }

    BigInteger result = pow(a, b.divide(BigInteger.valueOf(2)), c);

    if (b.mod(BigInteger.valueOf(2)).toString().equals("0")) {
      return result.mod(c).pow(2).mod(c);
    }

    return result.mod(c).pow(2).mod(c).multiply(a).mod(c);
  }

}
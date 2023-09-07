import java.io.*;

/**
 * [풀이]
 * t의 현재 상태에 따라, 가능한 t의 이전 상태를 모두 확인해보면 된다.
 * t가 abcdA와 같다면, t의 이전 상태는 abcd 이다.
 * t가 Babcd와 같다면, t의 이전 상태는 dcba 이다.
 *
 * [예시]
 * t = BAABAA 라면,
 * t의 가능한 이전 상태는 BAABA와 AABAA이다.
 * 이어서, t = BAABA 라면
 * t의 가능한 두번째 이전 상태는 BAAB와 ABAA이다.
 * 반대로, t = AABAA 라면
 * t의 가능한 두번째 이전 상태는 AABA 밖에 없다.
 */
public class Q12919 {
  static String s;
  static String t;
  static int answer = 0;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    s = br.readLine();
    t = br.readLine();

    //solution
    solution(t);

    //print answer
    System.out.println(answer);
  }

  private static void solution(String currentT) {
    if (s.length() == currentT.length()) { //t의 현재 상태(tmp)가 s와 동일하다면
      if (s.equals(currentT)) answer = 1;
      return;
    }

    if (currentT.charAt(currentT.length() - 1) == 'A') { //t의 마지막 문자가 A라면, t의 마지막 문자 A를 제거한 것이 이전 상태가 될 수 있다.
      String beforeT = currentT.substring(0, currentT.length() - 1);
      solution(beforeT); //t의 마지막 문자 제거 후, 재귀호출
    }

    if (currentT.charAt(0) == 'B') { //t의 첫 문자가 B라면, t의 첫 문자 B를 제거하고 뒤집은 것이 이전 상태가 될 수 있다.
      StringBuilder sb = new StringBuilder(
          currentT.substring(1)
      );
      String beforeT = sb.reverse().toString();
      solution(beforeT); //t의 첫 문자 제거 후 뒤집은 뒤, 재귀호출
    }
  }
}
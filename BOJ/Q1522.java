import java.io.*;

public class Q1522 {
  static final int MAX = (int) 1e9;
  static String input;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    input = br.readLine();

    //solution
    int answer = solution();

    //print answer;
    System.out.println(answer);
  }

  private static int solution() {
    int answer = MAX;

    //count a
    int countA = getACount();

    //aStart부터 a의 개수 만큼 까지의 범위를 모두 a로 바꾸는 경우, 필요한 교체 횟수 세기
    for (int aStart = 0; aStart < input.length(); aStart++) {
      int countB = 0;
      for (int i = 0; i < countA; i++) {
        int idx = (aStart + i) % input.length();
        if (input.charAt(idx) == 'b') countB++; //(aStart ~ aEnd)까지의 범위 내의 b의 개수가 곧 교체 횟수
      }
      answer = Math.min(answer, countB);
    }

    return answer;
  }

  private static int getACount() {
    int count = 0;

    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == 'a') count++;
    }

    return count;
  }
}
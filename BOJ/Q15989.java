import java.io.*;

public class Q15989 {
  static int t;
  static int n;
  static int count = 0;
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());

    //solution
    while (t-- > 0) { //테스트케이스 반복
      n = Integer.parseInt(br.readLine());
      count = 0;

      //합에 존재하는 2의 개수를 0부터 하나씩 늘려가며, 가능한 3의 개수를 계산해서 count
      //Ex) 2의 개수가 0 일때, 3의 개수 -> 0개, 1개, 2개, ..., n/3개까지
      //    따라서 2의 개수가 0일때, n을 만드는 3의 개수는 0개부터 n/3개, 총 n/3 + 1 개
      while (n >= 0) {
        count += n / 3 + 1;
        n -= 2;
      }

      answer.append(count);
      answer.append("\n");
    }

    //print answer
    System.out.println(answer);
  }
}

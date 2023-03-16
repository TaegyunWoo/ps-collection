import java.io.*;

public class Q9655 {
  public static boolean[] gameTable; //true:상근이 Win, false:창영이 Win
  public static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    gameTable = new boolean[n+1];
    br.close();

    //게임이론
    //예외 상황
    if (n == 1 || n == 3) {
      System.out.println("SK");
      return;
    }
    if (n == 2) {
      System.out.println("CY");
      return;
    }

    //게임테이블 초기화
    gameTable[1] = true;
    gameTable[3] = true;

    //gameTable[i] : 돌 갯수가 i 개인 경우, 이기는 사람
    for (int i = 4; i <= n; i++) {
      gameTable[i] = !(gameTable[i-1] || gameTable[i-3]);
    }

    System.out.println(gameTable[n] ? "SK" : "CY");
  }
}

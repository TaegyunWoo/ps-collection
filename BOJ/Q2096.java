import java.io.*;

public class Q2096 {
  static int n;
  static int[][] map;
  static int[][][] d; //d[i][u][0] = i번째 줄의 u번째 칸에 도착할 때의 최소 점수합, d[i][u][1] = i번째 줄의 u번째 칸에 도착할 때의 최대 점수합

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    map = new int[n][3];
    d = new int[n][3][2];
    for (int i = 0; i < n; i++) {
      String[] tmp = br.readLine().split(" ");
      map[i][0] = Integer.parseInt(tmp[0]);
      map[i][1] = Integer.parseInt(tmp[1]);
      map[i][2] = Integer.parseInt(tmp[2]);
    }
    for (int i = 0; i < n; i++) {
      for (int u = 0; u < 3; u++) {
        d[i][u][0] = -1;
        d[i][u][1] = -1;
      }
    }

    //solution
    d[0][0][0] = map[0][0]; d[0][0][1] = map[0][0];
    d[0][1][0] = map[0][1]; d[0][1][1] = map[0][1];
    d[0][2][0] = map[0][2]; d[0][2][1] = map[0][2];
    for (int i = 1; i < n; i++) {
      d[i][0][0] = Math.min(d[i-1][0][0], d[i-1][1][0]) + map[i][0];
      d[i][0][1] = Math.max(d[i-1][0][1], d[i-1][1][1]) + map[i][0];
      d[i][1][0] = Math.min(Math.min(d[i-1][0][0], d[i-1][1][0]), d[i-1][2][0]) + map[i][1];
      d[i][1][1] = Math.max(Math.max(d[i-1][0][1], d[i-1][1][1]), d[i-1][2][1]) + map[i][1];
      d[i][2][0] = Math.min(d[i-1][1][0], d[i-1][2][0]) + map[i][2];
      d[i][2][1] = Math.max(d[i-1][1][1], d[i-1][2][1]) + map[i][2];
    }

    //print answer
    int minAnswer = Math.min(Math.min(d[n-1][0][0], d[n-1][1][0]), d[n-1][2][0]);
    int maxAnswer = Math.max(Math.max(d[n-1][0][1], d[n-1][1][1]), d[n-1][2][1]);
    System.out.println(maxAnswer + " " + minAnswer);
  }
}
import java.io.*;

public class Q20125 {
  public static int n;
  public static char[][] map;

  public static void main(String[] args) throws IOException {
    //input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    map = new char[n][n];
    for (int i = 0; i < n; i++) {
      String s = br.readLine();
      for (int u = 0; u < n; u++) {
        map[i][u] = s.charAt(u);
      }
    }

    //map 전체 탐색하면서, 신체부위 길이 계산
    int[] heartPosition = null;
    int leftArm = 0;
    int rightArm = 0;
    int back = 0;
    int leftLeg = 0;
    int rightLeg = 0;
    for (int i = 0; i < n; i++) {
      for (int u = 0; u < n; u++) {
        if (map[i][u] != '*') continue;

        if (heartPosition == null)
          heartPosition = new int[]{i+1, u};
        else if (i == heartPosition[0] && u < heartPosition[1])
          leftArm++;
        else if (i == heartPosition[0] && u > heartPosition[1])
          rightArm++;
        else if (i > heartPosition[0] && u == heartPosition[1])
          back++;
        else if (i > heartPosition[0] && u == heartPosition[1] - 1)
          leftLeg++;
        else if (i > heartPosition[0] && u == heartPosition[1] + 1)
          rightLeg++;
      }
    }

    //정답 출력
    System.out.println((heartPosition[0] + 1) + " " + (heartPosition[1] + 1));
    System.out.println(leftArm + " " + rightArm + " " + back + " " + leftLeg + " " + rightLeg);
  }
}

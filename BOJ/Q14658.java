import java.io.*;

public class Q14658 {
  static int n;
  static int m;
  static int l;
  static int k;
  static int[][] starAry;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    m = Integer.parseInt(tmp[1]);
    l = Integer.parseInt(tmp[2]);
    k = Integer.parseInt(tmp[3]);
    starAry = new int[k][2];
    for (int i = 0; i < k; i++) {
      tmp = br.readLine().split(" ");
      starAry[i][0] = Integer.parseInt(tmp[0]);
      starAry[i][1] = Integer.parseInt(tmp[1]);
    }

    //solution
    int answer = 0;
    for (int i = 0; i < starAry.length; i++) {
      for (int u = 0; u < starAry.length; u++) {
        int movedX = starAry[u][0];
        int fixedY = starAry[i][1];
        int count = 0;

        for (int[] star : starAry) {
          if (star[0] < movedX || star[0] > movedX + l) continue;
          if (star[1] < fixedY || star[1] > fixedY + l) continue;
          count++;
        }

        answer = Math.max(answer, count);
      }
    }

    //print answer
    System.out.println(k - answer);
  }
}
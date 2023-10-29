import java.io.*;

public class Q1027 {
  static int n;
  static int[] buildings;
  static int[] countAry;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    buildings = new int[n];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      buildings[i] = Integer.parseInt(tmp[i]);
    }
    countAry = new int[n];

    //solution
    for (int i = 0; i < n; i++) {
      int x1 = i;
      int y1 = buildings[i];

      //x1로부터 왼쪽
      for (int u = i-1; u >= 0; u--) {
        int x2 = u;
        int y2 = buildings[u];
        double[] cons = getCons(x1, y1, x2, y2);
        boolean canCount = canSee(cons, x2 + 1, x1 - 1);

        if (canCount) countAry[x1]++;
      }

      //x1로부터 오른쪽
      for (int u = i+1; u < n; u++) {
        int x2 = u;
        int y2 = buildings[u];
        double[] cons = getCons(x1, y1, x2, y2);
        boolean canCount = canSee(cons, x1 + 1, x2 - 1);

        if (canCount) countAry[x1]++;
      }
    }

    //print answer
    int answer = 0;
    for (int i = 0; i < n; i++) {
      answer = Math.max(answer, countAry[i]);
    }
    System.out.println(answer);
  }

  private static boolean canSee(double[] cons, int startX, int endX) {
    boolean result = true;
    for (int i = startX; i <= endX; i++) {
      double limit = cons[0] * i + cons[1];
      if (limit <= buildings[i]) {
        result = false;
        break;
      }
    }
    return result;
  }

  private static double[] getCons(double x1, double y1, double x2, double y2) {
    double a = (y1 - y2) / (x1 - x2);
    double b = y1 - (a * x1);
    return new double[] {a, b};
  }

}
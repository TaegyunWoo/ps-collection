import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Q17266 {
  static int n; //굴다리 길이
  static int m; //가로등 개수
  static int[] lightLocationAry; //가로등 위치 index 정보

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());
    lightLocationAry = new int[m];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < m; i++) {
      lightLocationAry[i] = Integer.parseInt(tmp[i]);
    }

    //solution
    int answer = binarySearch();
    System.out.println(answer);

    List<Integer> list = new ArrayList<>();
  }

  private static int binarySearch() {
    int minHeight = 1;
    int maxHeight = 100000;
    int midHeight = (minHeight + maxHeight) / 2;
    int result = (int) 1e9;

    while (minHeight <= maxHeight) {
      boolean canCover = canCoverAllArea(midHeight);

      if (canCover) {
        maxHeight = midHeight - 1;
        result = Math.min(result, midHeight);
      } else {
        minHeight = midHeight + 1;
      }

      midHeight = (minHeight + maxHeight) / 2;
    }

    return result;
  }

  private static boolean canCoverAllArea(int height) {
    boolean[] area = new boolean[n];
    int beforeEnd = 0;
    for (int light : lightLocationAry) {
      int currentStart = (light - height < 0) ? 0 : light - height;
      int currentEnd = (light + height - 1 >= area.length) ? area.length - 1 : light + height - 1;

      if (beforeEnd > currentStart) currentStart = beforeEnd;

      for (int i = currentStart; i <= currentEnd; i++) {
        area[i] = true;
      }

      beforeEnd = currentEnd;
    }

    for (boolean lightOn : area) {
      if (!lightOn) return false;
    }
    return true;
  }
}
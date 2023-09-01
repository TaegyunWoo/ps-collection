import java.io.*;

public class Q1138 {
  static int n;
  static int[] tallerLeftInfo;
  static int[] result;
  static int[] countTallerLeft;
  static boolean[] visited;
  static int[] answer;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    tallerLeftInfo = new int[n];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      tallerLeftInfo[i] = Integer.parseInt(tmp[i]);
    }
    result = new int[n];
    countTallerLeft = new int[n];
    visited = new boolean[n+1];

    //solution
    permutation(0);
    for (int i = 0; i < answer.length; i++) {
      System.out.print(answer[i] + " ");
    }
  }

  private static void permutation(int depth) {
    if (depth == n) {
      //만족 확인
      if (isValid()) answer = result;
      return;
    }

    for (int height = 1; height <= n; height++) {
      if (answer != null) return;
      if (visited[height]) continue;

      visited[height] = true;
      result[depth] = height;
      countTallerLeft[depth] = getCountTallerLeft(depth);

      permutation(depth+1);

      visited[height] = false;
    }
  }

  private static int getCountTallerLeft(int currentIdx) {
    int count = 0;
    for (int i = 0; i < currentIdx; i++) {
      if (result[i] > result[currentIdx]) count++;
    }
    return count;
  }

  private static boolean isValid() {
    for (int i = 0; i < n; i++) {
      int height = result[i];
      int count = countTallerLeft[i];
      if (tallerLeftInfo[height-1] != count) return false;
    }
    return true;
  }
}
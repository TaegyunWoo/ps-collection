import java.io.*;
import java.util.*;

public class Q1863 {
  static int n;
  static int[] skyline;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    skyline = new int[n];
    for (int i = 0; i < n; i++) {
      String[] tmp = br.readLine().split(" ");
      skyline[i] = Integer.parseInt(tmp[1]);
    }

    //solution
    int answer = solution();

    //print answer
    System.out.println(answer);
  }

  private static int solution() {
    Set<Integer> heightSet = new HashSet<>();
    int count = 0;

    int beforeHeight = 0;
    for (int i = 0; i < n; i++) {
      int height = skyline[i];

      if (height == 0) { //스카이라인이 끝난 경우
        heightSet = new HashSet<>();

      } else if (beforeHeight < height) { //스카이라인이 높아진 경우
        heightSet.add(height);
        count++;

      } else { //스카이라인이 낮아진 경우
        removeBiggerThan(height, heightSet);

        if (!heightSet.contains(height)) { //낮아진 높이가 이전에 살펴본 높이가 아니라면
          heightSet.add(height);
          count++;
        }
      }

      beforeHeight = height;
    } //for문 종료

    return count;
  }

  private static void removeBiggerThan(int num, Set<Integer> target) {
    Set<Integer> tmpSet = new HashSet<>();
    for (int item : target) {
      if (item > num) tmpSet.add(item);
    }
    target.removeAll(tmpSet);
  }
}
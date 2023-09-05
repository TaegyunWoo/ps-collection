import java.io.*;
import java.util.*;

public class Q2531 {
  static int n; //접시 수
  static int d; //초밥 가짓수
  static int k; //연속해서 먹는 접시 수
  static int c; //쿠폰으로 받는 초밥 종류 번호
  static int[] belt; //벨트 정보
  static HashMap<Integer, Integer> dishCount = new HashMap<>(); //<접시종류, 개수>

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    d = Integer.parseInt(tmp[1]);
    k = Integer.parseInt(tmp[2]);
    c = Integer.parseInt(tmp[3]);
    belt = new int[n];
    for (int i = 0; i < n; i++) {
      int num = Integer.parseInt(br.readLine());
      belt[i] = num;
    }

    //solution
    int answer = solution();

    //print answer
    System.out.println(answer);
  }

  private static int solution() {
    int maxCount = 0;

    //init dishCount (k개 접시 추가)
    for (int i = 0; i < k; i++) {
      addDish(belt[i]);
    }
    addDish(c); //쿠폰까지 add

    //get answer
    for (int start = 0; start < n; start++) {
      maxCount = Math.max(maxCount, dishCount.size()); //최대 접시 가짓수 업데이트
      removeDish(belt[start]); //먹은 범위의 가장 앞단 접시 remove
      addDish(belt[(start + k) % n]); //다음 접시 add
    }

    return maxCount;
  }

  private static void addDish(int dish) {
    if (dishCount.containsKey(dish)) dishCount.put(dish, dishCount.get(dish) + 1);
    else dishCount.put(dish, 1);
  }

  private static void removeDish(int dish) {
    if (dishCount.get(dish) == 1) dishCount.remove(dish);
    else dishCount.put(dish, dishCount.get(dish) - 1);
  }
}
import java.io.*;
import java.util.*;

public class Q1253 {
  static int n;
  static List<Integer> nums = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      nums.add(Integer.parseInt(tmp[i]));
    }

    //solution
    nums.sort(Comparator.naturalOrder()); //sort
    int answer = 0;
    for (int i = 0; i < n; i++) {
      if (isGood(i)) answer++;
    }

    //print answer
    System.out.println(answer);
  }

  private static boolean isGood(int idx) {
    int num = nums.get(idx);
    int left = 0;
    int right = n-1;

    while (left < right) {
      if (left == idx) {
        left++;
        continue;
      }
      if (right == idx) {
        right--;
        continue;
      }

      int sum = nums.get(left) + nums.get(right);

      if (sum < num) left++;
      else if (sum > num) right--;
      else if (sum == num) return true;
    }

    return false;
  }
}
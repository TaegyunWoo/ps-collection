import java.io.*;
import java.util.*;

public class Q13144 {
  static int[] numAry;
  static int n;
  static Set<Integer> numSet = new HashSet<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    numAry = new int[n];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      numAry[i] = Integer.parseInt(tmp[i]);
    }

    //solution
    long answer = 0;
    int left = 0;
    int right = 1;
    numSet.add(numAry[0]);

    while (right != n) {
      if (numSet.contains(numAry[right])) { //numAry[right]이 {numAry[left], ..., numAry[right-1]} 수열의 원소와 중복된다면
        //numAry[left]부터 중복되는 원소까지가 각각 수열의 시작점인 경우 계산
        while (numAry[left] != numAry[right]) {
          numSet.remove(numAry[left]);
          answer += right - left;
          left++;
        }
        answer += right - left;
        left++;
      }
      numSet.add(numAry[right]);
      right++;
    }

    //나머지 계산
    for (int i = left; i < n; i++) {
      answer += right - i;
    }

    //정답 출력
    System.out.println(answer);
  }
}
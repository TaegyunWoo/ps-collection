import java.io.*;

public class Q2467 {
  static int n;
  static int[] nums;
  static int answerSum = Integer.MAX_VALUE;
  static int[] answer;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    nums = new int[n];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(tmp[i]);
    }
    answer = new int[2];

    //solution
    for (int i = 0; i < n; i++) {
      int a = nums[i];
      int left = i + 1; //탐색할 b 범위의 시작점
      int right = n - 1; //탐색할 b 범위의 끝점
      int mid = (left + right) / 2; //b의 위치

      //현재 a에 대해, (a+b)가 0에 가장 가까운 b를 이진탐색으로 찾기
      while (left <= right) {
        int b = nums[mid];
        int sum = a + b;

        if (sum > 0) { //(a+b)가 0보다 크다면
          right = mid - 1; //b의 범위를 낮은 값으로 줄이기

        } else if (sum < 0) { //(a+b)가 0보다 작다면
          left = mid + 1; //b의 범위를 높은 값으로 줄이기

        } else { //(a+b)가 0이라면
          System.out.println(a + " " + b); //정답 출력
          return;
        }

        //정답 갱신
        answerSum = getCloseTo0(answerSum, sum);
        if (answerSum == sum) {
          answer[0] = a;
          answer[1] = b;
        }

        mid = (left + right) / 2;
      }
    }

    //print answer
    System.out.println(answer[0] + " " + answer[1]);
  }

  private static int getCloseTo0(int a, int b) {
    int absA = Math.abs(a);
    int absB = Math.abs(b);
    return (absA < absB) ? a : b;
  }
}
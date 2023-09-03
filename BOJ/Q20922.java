import java.io.*;

public class Q20922 {
  static int n;
  static int k;
  static int[] ary;
  static int[] count; //count[num] = num의 등장횟수

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    k = Integer.parseInt(tmp[1]);
    tmp = br.readLine().split(" ");
    ary = new int[n];
    for (int i = 0; i < n; i++) {
      ary[i] = Integer.parseInt(tmp[i]);
    }
    count = new int[100001];

    //solution
    int answer = solution();

    //print answer
    System.out.println(answer);
  }

  //Greedy or Two Pointer
  private static int solution() {
    int start = 0;
    int end = 0;
    int maxLength = 0;

    while (end < n) {
      int num = ary[end];
      count[num]++;

      if (count[num] > k) { //ary[end]를 연속 부분 수열에 포함시켰을 때, k 초과의 개수라면
        while (ary[start] != num) { //ary[start]가 ary[end] 와 동일한 숫자일 때까지, 순열 앞단 제거
          count[ary[start]]--;
          start++;
        }

        //ary[end]와 동일한 숫자 바로 뒤에 start 위치
        count[ary[start]]--;
        start++;
      }

      maxLength = Math.max(maxLength, end - start); //정답 갱신
      end++;
    }

    return maxLength + 1;
  }
}
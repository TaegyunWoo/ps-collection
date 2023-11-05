import java.io.*;
import java.util.*;

public class Q22866 {
  static final int INF = (int) 1e9;
  static int n;
  static int[] heights;
  static Deque<Integer> deque = new ArrayDeque<>();
  static int[][] answerAry;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    heights = new int[n];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      heights[i] = Integer.parseInt(tmp[i]);
    }
    answerAry = new int[n][2];
    for (int i = 0; i < n; i++) {
      answerAry[i][1] = INF;
    }

    //solution

    //currentIdx의 왼쪽 건물 세기
    for (int currentIdx = 0; currentIdx < n; currentIdx++) {
      //currentIdx 건물보다 낮은 건물들은 나머지 건물들의 정답을 계산할 때 사용되지 않는다. -> 따라서 pollLast
      while (!deque.isEmpty() && heights[deque.peekLast()] <= heights[currentIdx]) {
        deque.pollLast();
      }

      //만약 deque이 비었다면
      if (deque.isEmpty()) {
        deque.offerLast(currentIdx);
        continue;
      }

      //이제 currentIdx 건물에서 바라볼 수 있는 건물들만 deque에 남는다. (모두 currentIdx 건물보다 높고, deque의 top부터 오름차순으로 커진다.)
      answerAry[currentIdx][0] += deque.size(); //왼쪽에서 볼 수 있는 건물 개수 더하기
      answerAry[currentIdx][1] = deque.peekLast(); //왼쪽에서 볼 수 있는 건물 중, 가장 가깝고 낮은 번호의 건물 저장

      //이제 currentIdx offer
      deque.offerLast(currentIdx);
    }

    deque.clear();

    //currentIdx의 오른쪽 건물 세기
    for (int currentIdx = n-1; currentIdx >= 0; currentIdx--) {
      //currentIdx 건물보다 낮은 건물들은 나머지 건물들의 정답을 계산할 때 사용되지 않는다. -> 따라서 pollLast
      while (!deque.isEmpty() && heights[deque.peekLast()] <= heights[currentIdx]) {
        deque.pollLast();
      }

      //만약 deque이 비었다면
      if (deque.isEmpty()) {
        deque.offerLast(currentIdx);
        continue;
      }

      //이제 currentIdx 건물에서 바라볼 수 있는 건물들만 deque에 남는다. (모두 currentIdx 건물보다 높고, deque의 top부터 오름차순으로 커진다.)
      answerAry[currentIdx][0] += deque.size(); //오른쪽에서 볼 수 있는 건물 개수 더하기
      //만약 왼쪽 건물 중 가장 가까운 건물보다, 오른쪽 건물 중 가장 가까운 건물이 더 가깝다면
      if (Math.abs(currentIdx - answerAry[currentIdx][1]) > Math.abs(currentIdx - deque.peekLast())) {
        answerAry[currentIdx][1] = deque.peekLast(); //오른쪽에서 볼 수 있는 건물 중, 가장 가깝고 낮은 번호의 건물 저장
      }

      //이제 currentIdx offer
      deque.offerLast(currentIdx);
    }

    //print answer
    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < n; i++) {
      if (answerAry[i][0] == 0) answer.append(0 + "\n");
      else answer.append(answerAry[i][0] + " " + (answerAry[i][1] + 1) + "\n");
    }
    System.out.println(answer.toString());
  }

}
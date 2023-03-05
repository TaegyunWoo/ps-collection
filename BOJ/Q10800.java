import java.io.*;
import java.util.*;

public class Q10800 {
  public static int n = 0; //공 개수
  public static int[] accumulateColorTable = new int[200001];
  public static int[][] ballAry;
  public static int[] answerAry = new int[200001];

  public static void main(String[] args) throws IOException {
    //Input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    ballAry = new int[n][3];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      ballAry[i][0] = i; //인덱스
      ballAry[i][1] = Integer.parseInt(st.nextToken()); //색깔
      ballAry[i][2] = Integer.parseInt(st.nextToken()); //무게
    }

    //ballAry - 무게순 정렬
    Arrays.sort(ballAry, (a, b) -> {
      return a[2] - b[2];
    });

    //------[누적합 계산]-------
    int smallBallWeightSum = 0; //현재 볼(i)보다 작은 볼 무게 합
    int smallBallIndex = 0; //현재 볼(i)보다 작은 무게의 볼 중, 최대 무게를 갖는 볼의 index
    for (int i = 0; i < ballAry.length; i++) {
      int currentIndex = ballAry[i][0]; //현재 볼(i)의 인덱스
      int currentColor = ballAry[i][1]; //현재 볼(i)의 색깔
      int currentWeight = ballAry[i][2]; //현재 볼(i)의 무게
      int smallBallColor = ballAry[smallBallIndex][1]; //현재 볼(i)보다 작은 무게의 볼 중, 최대 무게를 갖는 볼의 색깔
      int smallBallWeight = ballAry[smallBallIndex][2]; //현재 볼(i)보다 작은 무게의 볼 중, 최대 무게를 갖는 볼의 무게

      while (smallBallWeight < currentWeight) {
        smallBallWeightSum += smallBallWeight; //색 구분 없이, 현재 볼(i)보다 작은 볼의 무게 더하기
        accumulateColorTable[smallBallColor] += smallBallWeight; //현재 볼(i)보다 작은 볼의 무게 총합을 색깔별로 저장

        smallBallIndex++;
        smallBallColor = ballAry[smallBallIndex][1]; //업데이트
        smallBallWeight = ballAry[smallBallIndex][2]; //업데이트
      }

      //현재 볼(i)가 먹을 수 있는 볼 무게 총합
      //    = (현재 볼(i)보다 작은 무게의 볼들의 총 무게) - (현재 볼(i)보다 작은 무게의 볼들 중, 색이 같은 볼들의 총 무게)
      answerAry[currentIndex] = smallBallWeightSum - accumulateColorTable[currentColor];
    }

    //------[정답 출력]--------
    for (int i = 0; i < n; i++) {
      System.out.println(answerAry[i]);
    }
  }
}
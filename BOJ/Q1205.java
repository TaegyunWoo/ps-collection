import java.io.*;
import java.util.*;

public class Q1205 {
  public static int n;
  public static int targetScore;
  public static int p;
  public static int[] rankAry;

  public static void main(String[] args) throws IOException {
    //Input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    targetScore = Integer.parseInt(st.nextToken());
    p = Integer.parseInt(st.nextToken());
    rankAry = new int[p];
    Arrays.fill(rankAry, -1);

    if (n != 0) {
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        rankAry[i] = Integer.parseInt(st.nextToken());
      }
    }

    //등수 확인
    int beforeScore = 2000000001;
    int rank = 0;
    boolean isFull = n == p;
    for (int i = 0; i < rankAry.length; i++) {
      if (isFull) { //리스트가 꽉찬 경우
        int beforeRank = rank;
        if (beforeScore > rankAry[i]) rank = i+1; //등수 갱신

        //랭킹에 못드는 경우
        if (i == rankAry.length-1) {
          if (targetScore <= rankAry[i]) {
            rank = -1;
            break;
          }
        }

        if (targetScore > rankAry[i]) {
          if (i != 0 && rankAry[i-1] == targetScore) rank = beforeRank;
          break;
        }

      } else { //리스트가 꽉차지 않은 경우
        if (beforeScore > rankAry[i]) rank = i+1; //등수 갱신

        if (targetScore >= rankAry[i]) break;
      }

      beforeScore = rankAry[i];
    }

    System.out.println(rank);
  }
}

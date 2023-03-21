import java.io.*;
import java.util.*;

public class Q9017 {
  public static int test;
  public static int n;
  public static int[] recordAry;
  public static int[] countAry;
  public static HashMap<Integer, List<Integer>> scoreMap;
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    test = Integer.parseInt(br.readLine());

    while (test-- > 0) {
      //Input
      n = Integer.parseInt(br.readLine());
      recordAry = new int[n];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < recordAry.length; i++) {
        recordAry[i] = Integer.parseInt(st.nextToken());
      }
      countAry = new int[201];
      scoreMap = new HashMap<>();
      for (int i = 1; i <= 200; i++) {
        scoreMap.put(i, new ArrayList<>());
      }

      //Solution
      //팀별 선수 수 count
      for (int i = 0; i < recordAry.length; i++) {
        int team = recordAry[i];
        countAry[team]++;
      }

      //팀별 점수 저장
      int score = 1;
      for (int i = 0; i < recordAry.length; i++) {
        int team = recordAry[i];
        if (countAry[team] != 6) continue; //탈락 팀인 경우
        scoreMap.get(team).add(score++);
      }

      //팀별 점수 정렬
      for (int key : scoreMap.keySet()) {
        scoreMap.get(key).sort(Comparator.naturalOrder());
      }

      //정답 구하기
      int answerTeam = 0;
      int answerScore = (int) 1e9;
      for (int team : scoreMap.keySet()) {
        List<Integer> scoreList = scoreMap.get(team);
        int sum = 0;

        if (scoreList.size() == 0) continue;

        for (int i = 0; i < 4; i++) {
          sum += scoreList.get(i);
        }

        if (answerScore < sum) continue;
        else if (answerScore == sum && scoreList.get(4) > scoreMap.get(answerTeam).get(4)) continue;

        answerTeam = team;
        answerScore = sum;
      }

      answer.append(answerTeam + "\n");
    }

    //정답 출력
    System.out.println(answer.toString());
  }
}

import java.io.*;
import java.util.*;

public class Q3758 {
  public static int test;
  public static int n; //팀 개수
  public static int k; //문제 개수
  public static int t; //본인의 팀 번호
  public static int m; //로그 개수
  public static List<TeamInfo> teamList;
  public static String[] logAry;
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    test = Integer.parseInt(br.readLine());

    while (test-- > 0) {
      //Input
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      t = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      logAry = new String[m];
      for (int i = 0; i < logAry.length; i++) {
        logAry[i] = br.readLine();
      }
      teamList = new ArrayList<>();

      //Solution
      solution();
    }

    //정답 출력
    System.out.println(answer.toString());
  }

  private static void solution() {
    //Init teamList
    for (int i = 0; i < n; i++) {
      teamList.add(new TeamInfo(i+1));
    }

    //Update TeamInfo
    for (int i = 0; i < logAry.length; i++) {
      String[] log = logAry[i].split(" ");
      int teamNum = Integer.parseInt(log[0]);
      int problemNum = Integer.parseInt(log[1]);
      int score = Integer.parseInt(log[2]);
      TeamInfo teamInfo = teamList.get(teamNum-1);

      teamInfo.addScore(problemNum, score);
      teamInfo.increaseSummitCount();
      teamInfo.updateLatestSummitTime(i);
    }

    //Sort
    teamList.sort((a, b) -> {
      if (a.scoreSum > b.scoreSum) return -1;
      else if (a.scoreSum < b.scoreSum) return 1;
      else {
        if (a.totalSummitCount < b.totalSummitCount) return -1;
        else if (a.totalSummitCount > b.totalSummitCount) return 1;
        else return a.latestSummitTime - b.latestSummitTime;
      }
    });

    //Get answer
    int rank = 0;
    for (int i = 0; i < teamList.size(); i++) {
      rank++;
      if (teamList.get(i).teamNum == t) {
        answer.append(rank + "\n");
        break;
      }
    }
  }

  public static class TeamInfo {
    public int teamNum;
    public HashMap<Integer, Integer> maxScoreMap = new HashMap<>();
    public int totalSummitCount;
    public int latestSummitTime;
    public int scoreSum;

    public TeamInfo(int teamNum) {
      this.teamNum = teamNum;
    }

    public void addScore(int problemNum, int score) {
      if (maxScoreMap.containsKey(problemNum)) {
        if (maxScoreMap.get(problemNum) >= score) return;
        else scoreSum -= maxScoreMap.get(problemNum);
      }
      scoreSum += score;
      maxScoreMap.put(problemNum, score);
    }

    public void increaseSummitCount() {
      this.totalSummitCount++;
    }

    public void updateLatestSummitTime(int time) {
      latestSummitTime = time;
    }
  }
}

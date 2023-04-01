import java.util.*;
import java.io.*;

public class Q11501 {
  public static int test;
  public static int n;
  public static Info[] infoAry;
  public static List<Info> infoList;
  public static int stockCount;
  public static long result;
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    test = Integer.parseInt(br.readLine());

    while (test-- > 0) { //테스트케이스 while 문 시작
      //Input
      n = Integer.parseInt(br.readLine());
      infoAry = new Info[n];
      infoList = new ArrayList<>();
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        Info info = new Info(Integer.parseInt(st.nextToken()), i);
        infoAry[i] = info;
        infoList.add(info);
      }
      stockCount = 0;
      result = 0;

      //Solution
      //Sort (desc)
      infoList.sort((a, b) -> {
        return b.cost - a.cost;
      });

      //Decide buy or sell
      int maxInfoIdx = 0;
      for (int currentDate = 0; currentDate < infoAry.length; currentDate++) {
        Info currentInfo = infoAry[currentDate];
        Info maxInfo = infoList.get(maxInfoIdx);

        //만약 지난 날짜의 정보라면
        if (maxInfo.date < currentDate) {
          currentDate--;
          maxInfoIdx++;
          continue;
        }

        if (currentInfo.cost < maxInfo.cost) { //가장 비싼 경우가 남았다면
          buy(currentDate); //현재 날짜에서 구매

        } else if (currentInfo.cost == maxInfo.cost) { //현재 날짜가 가장 비싼 경우라면
          sell(currentDate); //현재 날짜에서 모두 판매
        }
      }

      //정답
      answer.append(result + "\n");
    } //테스트케이스 while 문 종료

    //정답 출력
    System.out.println(answer.toString());
  }

  private static void buy(int date) {
    result -= infoAry[date].cost;
    stockCount++;
  }

  private static void sell(int date) {
    result += stockCount * infoAry[date].cost;
    stockCount = 0;
  }

  public static class Info {
    public int cost;
    public int date;
    public Info(int cost, int date) {
      this.cost = cost;
      this.date = date;
    }
  }
}

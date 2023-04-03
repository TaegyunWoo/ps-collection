import java.io.*;
import java.util.*;

public class Q2304 {
  public static int n;
  public static List<Stick> stickList = new ArrayList<>();
  public static PriorityQueue<Stick> highestStickQueue = new PriorityQueue<>((a, b) -> b.h - a.h);
  public static int answer = 0;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      Stick stick = new Stick(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      stickList.add(stick);
      highestStickQueue.offer(stick);
    }

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    //sort by position
    stickList.sort((a, b) -> a.l - b.l);

    int beforeLeft = 0;
    int currentRoofHeight = 0;

    //앞에서부터 창고 넓이 구하기
    for (int i = 0; i < stickList.size(); i++) {
      Stick stick = stickList.get(i);

      //창고 높이 업데이트
      if (currentRoofHeight <= stick.h) {
        //현재 막대에서 다음 막대 직전까지의 창고 넓이 더하기
        answer += currentRoofHeight * (stick.l - beforeLeft);
        beforeLeft = stick.l;
        currentRoofHeight = stick.h;
      }
    }

    //가장 높은 기둥의 창고 넓이 구하기
    answer += highestStickQueue.peek().h;
    int beforeRight = highestStickQueue.peek().l + 1;
    currentRoofHeight = highestStickQueue.peek().h;
    for (int i = 0; i < stickList.size(); i++) {
      if (stickList.get(i).l <= beforeRight - 1) continue;

      Stick nextStick = stickList.get(i);
      if (nextStick.h < currentRoofHeight) break;

      answer += currentRoofHeight * ((nextStick.l + 1) - beforeRight);
      beforeRight = nextStick.l + 1;
    }

    //뒤에서부터 창고 넓이 구하기
    beforeRight = 0;
    currentRoofHeight = 0;
    for (int i = stickList.size() - 1; i >= 0; i--) {
      Stick stick = stickList.get(i);

      //창고 높이 업데이트
      if (currentRoofHeight < stick.h) {
        //현재 막대에서 다음 막대 직전까지의 창고 넓이 더하기
        answer += currentRoofHeight * (beforeRight - (stick.l + 1));
        beforeRight = stick.l + 1;
        currentRoofHeight = stick.h;
      }
    }
  }

  public static class Stick {
    public int l;
    public int h;
    public Stick(int l, int h) {
      this.l = l;
      this.h = h;
    }
  }
}

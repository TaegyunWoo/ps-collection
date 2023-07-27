import java.util.*;

class Solution {
  static final int INF = (int) 1e9;
  int outdoorTemperature;
  int t1;
  int t2;
  int a;
  int b;
  int[] onboard;
  int answer = INF;

  public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
    //init
    this.outdoorTemperature = temperature + 10;
    this.t1 = t1 + 10;
    this.t2 = t2 + 10;
    this.a = a;
    this.b = b;
    this.onboard = onboard;

    //DP
    int minTemperature = Math.min(this.t1, outdoorTemperature);
    int maxTemperature = Math.max(this.t2, outdoorTemperature);
    answer = dp(minTemperature, maxTemperature);

    return answer;
  }

  private int dp(int minTemperature, int maxTemperature) {
    //d[a][b] = a 시각에 (b-10) 실내 온도로 만드는 최소 총 소비전력
    int[][] d = new int[onboard.length+1][51];
    for (int i = 0; i < d.length; i++) {
      Arrays.fill(d[i], INF);
    }
    d[0][outdoorTemperature] = 0;


    for (int i = 0; i < onboard.length; i++) {
      int temperatureStart = minTemperature; //가능한 실내 온도 시작
      int temperatureEnd = maxTemperature; //가능한 실내 온도 끝

      //만약 승객이 타있다면, 가능한 온도는 t1 ~ t2로 제한
      if (onboard[i] == 1) {
        temperatureStart = t1;
        temperatureEnd = t2;
      }

      //가능한 온도 범위마다 반복
      for (int u = temperatureStart; u <= temperatureEnd; u++) {
        int toLowerPower = INF; //1도 낮출때 필요한 최소 총 소비전력
        int toHigherPower = INF; //1도 높일때 필요한 최소 총 소비전력
        int toKeepPower = d[i][u]; //유지할때 필요한 최소 총 소비전력

        if (u != 0)
          toHigherPower = d[i][u-1];
        if (u != 50)
          toLowerPower = d[i][u+1];

        //만약 실내 온도를 높여야하고, 이전 실내 온도가 실외 온도보다 높거나 같다면 에어컨을 켬
        if (u-1 >= outdoorTemperature)
          toHigherPower += a;
        //만약 실내 온도를 낮춰야하고, 이전 실내 온도가 실외 온도보다 낮거나 같다면 에어컨을 켬
        if (u+1 <= outdoorTemperature)
          toLowerPower += a;
        //만약 실내 온도를 유지해야하고, 이전 실내 온도가 실외 온도와 다르다면 에어컨을 켬
        if (u != outdoorTemperature)
          toKeepPower += b;

        //갱신
        d[i+1][u] = Math.min(toLowerPower, toHigherPower);
        d[i+1][u] = Math.min(d[i+1][u], toKeepPower);
      }

    }

    //Get answer
    return Arrays.stream(d[onboard.length]).min().getAsInt();
  }

}
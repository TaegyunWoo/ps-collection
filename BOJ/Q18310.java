import java.io.*;
import java.util.*;

public class Q18310 {
  private static int n;
  private static int[] houseAry;
  private static long totalDistance;
  private static int minHouseLocation;
  private static int frontHouseCount;
  private static int rearHouseCount;
  private static int beforeHouseLocation;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    houseAry = new int[100001];
    String[] houseNum = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      int index = Integer.parseInt(houseNum[i]);
      houseAry[index]++;
    }

    //solution
    //첫번째 집을 기준으로 초기화
    int firstLocation = 0;
    for (int location = 1; location < houseAry.length; location++) {
      if (houseAry[location] == 0) continue; //집이 없다면

      if (firstLocation == 0) { //첫번째 위치한 집이라면
        firstLocation = location;
        beforeHouseLocation = location;
        minHouseLocation = location;
        continue;
      }

      totalDistance += (location - beforeHouseLocation) * houseAry[location];
      frontHouseCount += houseAry[location];
    }

    //그리디
    beforeHouseLocation = firstLocation;
    for (int location = firstLocation+1; location < houseAry.length; location++) {
      if (houseAry[location] == 0) continue; //집이 없다면

      //안테나가 이동한 거리
      int movedDistance = location - beforeHouseLocation;

      long currentDistance = totalDistance;

      //기존 앞집개수만큼 거리 빼기
      currentDistance -= (long) movedDistance * frontHouseCount;

      //뒷집개수 계산
      rearHouseCount += houseAry[beforeHouseLocation];
      //뒷집개수만큼 거리 더하기
      currentDistance += (long) movedDistance * rearHouseCount;

      //정답 계산
      if (currentDistance < totalDistance) {
        totalDistance = currentDistance;
        minHouseLocation = location;
      }

      //앞집개수 계산
      frontHouseCount -= houseAry[location];
      //이전 위치 저장
      beforeHouseLocation = location;
    }

    //정답 출력
    System.out.println(minHouseLocation);
  }
}
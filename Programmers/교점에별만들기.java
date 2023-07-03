import java.util.*;

class Solution {
  HashSet<Info> crossInfoSet = new HashSet<>();

  public String[] solution(int[][] line) {
    getCrossInfo(line); //정수형 교점 저장
    long[] minXY = getMinXY(); //x, y 최소값 구하기
    updateCrossInfo(minXY); //모든 좌표 양수로 만들기
    long[] maxXY = getMaxXY(); //x, y 최대값 구하기
    StringBuilder[] result = getResult(maxXY); //정답 구하기

    //String[] 변환
    String[] answer = new String[result.length];
    for (int i = 0; i < answer.length; i++) {
      answer[i] = result[i].toString();
    }

    return answer;
  }

  //교점 좌표 정보 구하기
  private void getCrossInfo(int[][] line) {
    for (int i = 0; i < line.length; i++) {
      for (int u = i+1; u < line.length; u++) {
        long a = line[i][0];
        long b = line[i][1];
        long e = line[i][2];
        long c = line[u][0];
        long d = line[u][1];
        long f = line[u][2];

        //평행인 경우
        if (a*d - b*c == 0) continue;

        double x = (double) (b*f - e*d) / (double) (a*d - b*c);
        double y = (double) (e*c - a*f) / (double) (a*d - b*c);

        //교점이 정수형 좌표가 아닌 경우
        if (x != (long) x || y != (long) y) continue;

        //교점 저장
        crossInfoSet.add(new Info((long) x, (long) y));
      }
    }
  }

  private long[] getMinXY() {
    long x = Long.MAX_VALUE;
    long y = Long.MAX_VALUE;

    for (Info info : crossInfoSet) {
      x = Math.min(x, info.x);
      y = Math.min(y, info.y);
    }

    return new long[] {x, y};
  }

  private long[] getMaxXY() {
    long x = -1;
    long y = -1;

    for (Info info : crossInfoSet) {
      x = Math.max(x, info.x);
      y = Math.max(y, info.y);
    }

    return new long[] {x, y};
  }

  private void updateCrossInfo(long[] xy) {
    for (Info info : crossInfoSet) {
      info.x -= xy[0];
      info.y -= xy[1];
    }
  }

  private StringBuilder[] getResult(long[] maxXY) {
    StringBuilder[] result = new StringBuilder[(int) maxXY[1] + 1];

    //init
    for (int i = 0; i < result.length; i++) {
      StringBuilder sb = new StringBuilder();

      for (int u = 0; u <= maxXY[0]; u++) {
        sb.append(".");
      }

      result[i] = sb;
    }

    for (Info info : crossInfoSet) {
      int x = (int) info.x;
      int y = result.length - (int)info.y - 1;

      result[y].setCharAt(x, '*');
    }

    return result;
  }

  class Info {
    public long x;
    public long y;

    public Info(long x, long y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      Info other = (Info) o;
      if (this.x == other.x && this.y == other.y) return true;
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
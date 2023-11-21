import java.io.*;

/**
 * d[r][c][a] = (r,c)칸을 a방향으로 방문하는 경우의 최대 가치
 * d[r][c][0] = (r,c)칸을 위에서 아래로 방문
 * d[r][c][1] = (r,c)칸을 좌에서 우로 방문
 * d[r][c][2] = (r,c)칸을 우에서 좌로 방문
 */
public class Q2169 {
  static final int MINUS_INF = - (int) 1e9;
  static int n;
  static int m;
  static int[][] map;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    m = Integer.parseInt(tmp[1]);
    map = new int[n][m];
    for (int i = 0; i < n; i++) {
      tmp = br.readLine().split(" ");
      for (int u = 0; u < m; u++) {
        map[i][u] = Integer.parseInt(tmp[u]);
      }
    }

    //solution
    //init d array
    int[][][] d = new int[n][m][3];
    for (int i = 0; i < n; i++) {
      for (int u = 0; u < m; u++) {
        for (int j = 0; j < 3; j++) {
          d[i][u][j] = MINUS_INF;
        }
      }
    }
    d[0][0][0] = map[0][0]; d[0][0][1] = map[0][0]; d[0][0][2] = map[0][0];
    for (int i = 1; i < m; i++) {
      d[0][i][1] = d[0][i-1][1] + map[0][i];
    }
    //dp
    for (int r = 1; r < n; r++) {
      //위에서 아래로
      for (int c = 0; c < m; c++) {
        int beforeMaxVal = Math.max(d[r-1][c][0], d[r-1][c][1]);
        beforeMaxVal = Math.max(beforeMaxVal, d[r-1][c][2]); //(r,c-1)칸을 '위에서 아래로', '좌에서 우로', '우에서 좌로' 방문한 결과 중, 가장 큰 것
        d[r][c][0] = beforeMaxVal + map[r][c];
      }
      //좌에서 우로
      for (int c = 1; c < m; c++) {
        int beforeMaxVal = Math.max(d[r][c-1][0], d[r][c-1][1]); //(r,c-1)칸을 '위에서 아래로', '좌에서 우로' 방문한 결과 중, 가장 큰 것
        d[r][c][1] = beforeMaxVal + map[r][c];
      }
      //우에서 좌로
      for (int c = m-2; c >= 0; c--) {
        int beforeMaxVal = Math.max(d[r][c+1][0], d[r][c+1][2]); //(r,c-1)칸을 '위에서 아래로', '우에서 좌로' 방문한 결과 중, 가장 큰 것
        d[r][c][2] = beforeMaxVal + map[r][c];
      }
    }

    //print answer
    int answer = Math.max(d[n-1][m-1][0], d[n-1][m-1][1]);
    System.out.println(answer);
  }


}
class Solution {
  public int solution(int[][] sizes) {
    // 1. 명함의 가로/세로 중, 가장 긴 값으로 지갑의 가로 설정
    int walletW = Math.max(getMax(sizes, true), getMax(sizes, false));

    // 2. 각 명함마다 가로/세로 중 긴 부분을 가로로 설정
    for (int i = 0; i < sizes.length; i++) {
      int w = sizes[i][0];
      int h = sizes[i][1];
      if (w < h) {
        sizes[i][0] = h;
        sizes[i][1] = w;
      }
    }

    // 3. 각 명함의 세로 중 가장 큰 값을 지갑의 세로로 설정
    int walletH = getMax(sizes, false);

    // 4. 정답 출력
    return walletW * walletH;
  }

  private int getMax(int[][] sizes, boolean isW) {
    int i = 0;
    if (!isW) i = 1;

    int max = 0;
    for (int u = 0; u < sizes.length; u++) {
      max = Math.max(max, sizes[u][i]);
    }

    return max;
  }
}
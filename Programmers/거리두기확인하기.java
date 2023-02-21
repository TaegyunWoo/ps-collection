class Solution {
  public int[] solution(String[][] places) {
    int[] answer = new int[5];
    int idx = 0;

    for (String[] place : places) {
      answer[idx++] = checkPlace(place);
    }

    return answer;
  }

  private int checkPlace(String[] place) {
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        char item = place[r].charAt(c);

        if (item != 'P') continue;

        if (isBreak(r, c, place)) return 0;
      }
    }
    return 1;
  }

  private boolean isBreak(int r, int c, String[] place) {
    int[] dr = {-1, 1, 0, 0, -2, 2, 0, 0, -1, -1, 1, 1};
    int[] dc = {0, 0, -1, 1, 0, 0, -2, -2, -1, 1, -1, 1};

    for (int i = 0; i < 12; i++) {
      int nearR = r + dr[i];
      int nearC = c + dc[i];

      //존재하지 않는 위치인 경우
      if (nearR < 0 || nearR >= 5) continue;
      if (nearC < 0 || nearC >= 5) continue;

      //사람이 없는 경우
      if (place[nearR].charAt(nearC) != 'P') continue;

      //파티션 확인
      if (i < 8) { //'2칸 상하좌우'에 있는 경우
        int midR = (r + nearR) / 2;
        int midC = (c + nearC) / 2;
        if (place[midR].charAt(midC) == 'X') continue;

      } else { //'대각선'에 있는 경우
        int midR1 = -1;
        int midR2 = -1;
        int midC1 = -1;
        int midC2 = -1;

        switch (i) {
          case 8:
            midR1 = r - 1;
            midC1 = c;
            midR2 = r;
            midC2 = c - 1;
            break;
          case 9:
            midR1 = r - 1;
            midC1 = c;
            midR2 = r;
            midC2 = c + 1;
            break;
          case 10:
            midR1 = r;
            midC1 = c - 1;
            midR2 = r + 1;
            midC2 = c;
            break;
          case 11:
            midR1 = r;
            midC1 = c + 1;
            midR2 = r + 1;
            midC2 = c;
            break;
        }

        if (place[midR1].charAt(midC1) == 'X' && place[midR2].charAt(midC2) == 'X') continue;
      } //if-else문 종료

      return true;
    } //for문 종료

    return false;
  }
}
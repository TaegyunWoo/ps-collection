class Solution {
  int[] picks;
  String[] minerals;
  int answer = (int) 1e9;

  public int solution(int[] p, String[] m) {
    picks = p;
    minerals = m;

    brute(0, 5, 0, 0);

    return answer;
  }

  private void brute(int mineralPointer, int use, int pick, int score) {
    if (mineralPointer == minerals.length
        || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0 && use == 5)) {
      answer = Math.min(answer, score);
      return;
    }

    String currentMineral = minerals[mineralPointer];

    if (use < 5) { //아직 횟수가 5번 미만이라면
      brute(mineralPointer+1, use+1, pick, score + getScore(pick, currentMineral));

    } else { //횟수가 5번이라면
      //새 곡괭이 선택
      for (int i = 0; i < 3; i++) {
        if (picks[i] == 0) continue;
        picks[i]--;
        brute(mineralPointer, 0, i, score);
        picks[i]++;
      }
    }
  }

  private int getScore(int pick, String mineral) {
    switch (mineral) {
      case "diamond":
        if (pick == 0) return 1;
        else if (pick == 1) return 5;
        else return 25;
      case "iron":
        if (pick == 2) return 5;
        else return 1;
      default:
        return 1;
    }
  }
}
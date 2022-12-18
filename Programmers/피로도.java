class Solution {
  int[] dungeonIndexAry;
  boolean[] visited;
  int[] numAry;
  boolean canVisitDungeons;

  public int solution(int k, int[][] dungeons) {
    //dungeonIndexAry 초기화
    dungeonIndexAry = new int[dungeons.length];
    for (int i = 0; i < dungeonIndexAry.length; i++) {
      dungeonIndexAry[i] = i;
    }
    //visited 초기화
    visited = new boolean[dungeons.length];

    //방문 가능한 던전 개수 찾기
    for (int r = dungeons.length; r > 0; r--) {
      //numAry 초기화
      numAry = new int[r];

      //순열
      permutation(0, r, dungeons, k);

      if (canVisitDungeons) return r;

      //canVisitDungeons 다시 초기화
      canVisitDungeons = false;
    }

    //모든 경우에서 방문할 수 없는 경우
    return 0;
  }

  //순열 메서드
  private void permutation(int depth, int r, int[][] dungeons, int k) {
    //만약 r개만큼 뽑았다면
    if (depth == r) {
      //뽑은 던전 순서대로 방문하는 것이 가능한지 확인
      if ( canVisit(dungeons, k) ) canVisitDungeons = true;
      return;
    }

    for (int i = 0; i < dungeonIndexAry.length; i++) {
      if (visited[i]) continue; //만약 i 번째 던전을 뽑았으면 생략

      //i 번째 던전 뽑기
      visited[i] = true;
      numAry[depth] = i;

      //다음 순열 구하기
      permutation(depth+1, r, dungeons, k);

      //i 번째 던전을 뽑는 모든 경우를 구했으므로, i 번째 던전을 뽑지 않도록 설정
      visited[i] = false;
    }
  }

  //해당 순서로 방문이 가능한지 확인
  private boolean canVisit(int[][] dungeons, int k) {
    for (int i = 0; i < numAry.length; i++) {
      int needScore = dungeons[numAry[i]][0]; //최소 필요 피로도
      int useScore = dungeons[numAry[i]][1]; // 소모 피로도
      if (k < needScore) return false;
      k -= useScore;
    }
    return true;
  }
}
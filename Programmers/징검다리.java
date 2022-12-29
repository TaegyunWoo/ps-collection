import java.util.*;

/**
 * 이진탐색 -> 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값을 찾는 것
 * 즉 '1 ~ distance' 사이의 값 중, n개의 바위를 제거해서 만들 수 있는 '각 지점 사이의 거리의 최솟값'의 최댓값을 찾는 것
 */
class Solution {


  public int solution(int distance, int[] rocks, int n) {
    //정렬
    Arrays.sort(rocks);

    int min = 1;
    int max = distance;
    int mid = (max+min) / 2;
    int answer = 0;

    while (max >= min) {
      boolean possible = canMakeDistance(mid, rocks, n, distance); //mid 값을 '거리의 최솟값' 중 최댓값이라고 했을 때

      if (possible) { //'거리의 최솟값'이 mid값과 동일할 수 있다면
        min = mid + 1; //더 큰 '거리의 최솟값' 중 최댓값을 찾는다.
        answer = Math.max(answer, mid);

      } else { //'거리의 최솟값'이 mid값과 동일할 수 없다면
        max = mid - 1; //더 작은 '거리의 최솟값' 중 최댓값을 찾는다.
      }

      mid = (max + min) / 2;
    }

    return answer;
  }

  private boolean canMakeDistance(int distance, int[] rocks, int n, int finalDistance) {
    int removeCount = 0;
    int sectorDistance = 0;

    for (int i = 0; i < rocks.length + 1; i++) {
      if (i == 0) {
        sectorDistance = rocks[0];
      } else if (i == rocks.length) {
        sectorDistance += finalDistance - rocks[i-1];
      } else {
        sectorDistance += rocks[i] - rocks[i-1];
      }

      if (sectorDistance >= distance) {
        sectorDistance = 0;
      } else {
        removeCount++;
      }

    }

    if (removeCount > n) return false;
    return true;
  }
}
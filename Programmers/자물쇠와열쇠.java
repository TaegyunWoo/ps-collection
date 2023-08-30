class Solution {
  int keyLength;
  int lockLength;
  int mapLength;
  int[][] map;

  public boolean solution(int[][] key, int[][] lock) {
    //init
    keyLength = key.length;
    lockLength = lock.length;
    mapLength = key.length * 2 + lock.length;
    map = new int[mapLength][mapLength];

    //map의 중앙에 자물쇠 기록
    for (int i = 0; i < lock.length; i++) {
      for (int u = 0; u < lock[0].length; u++) {
        map[keyLength + i][keyLength + u] = lock[i][u];
      }
    }

    //key를 90도씩 회전시키며 확인
    for (int i = 0; i < 4; i++) {
      boolean result = validate(key);
      if (result) return true;
      rotateKey(key);
    }

    return false;
  }

  private boolean validate(int[][] key) {
    //map[i][u] 에 키의 최상단·최좌측을 위치시키며, 확인
    for (int i = 0; i < mapLength - keyLength; i++) {
      for (int u = 0; u < mapLength - keyLength; u++) {
        if (i == 0 && u == 0) continue; //(0,0)은 확인할 필요 X

        //map에 key 기록
        for (int j = 0; j < keyLength; j++) {
          for (int k = 0; k < keyLength; k++) {
            map[i + j][u + k] += key[j][k];
          }
        }

        //lock의 모든 부분이 1인지 확인
        boolean isAll1 = true;
        for (int j = keyLength; j < mapLength - keyLength; j++) {
          for (int k = keyLength; k < mapLength - keyLength; k++) {
            if (map[j][k] != 1) {
              isAll1 = false;
              break;
            }
          }
        }

        //다시 lock을 map에서 제거
        for (int j = 0; j < keyLength; j++) {
          for (int k = 0; k < keyLength; k++) {
            map[i + j][u + k] -= key[j][k];
          }
        }

        if (isAll1) return true;
      }
    }

    return false;
  }

  private void rotateKey(int[][] key) {
    //기존 key 복사
    int[][] tmp = new int[keyLength][keyLength];
    for (int i = 0; i < tmp.length; i++) {
      for (int u = 0; u < tmp.length; u++) {
        tmp[i][u] = key[i][u];
      }
    }

    //회전
    int keyR = 0;
    int keyC = 0;
    for (int tmpC = 0; tmpC < tmp.length; tmpC++) {
      for (int tmpR = tmp.length-1; tmpR >= 0; tmpR--) {
        key[keyR][keyC] = tmp[tmpR][tmpC];

        keyC++;

        if (keyC == keyLength) {
          keyR++;
          keyC = 0;
        }
      }
    }
  }
}
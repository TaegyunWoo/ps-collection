import java.util.*;

class Solution {
  List<String> cacheList = new LinkedList<>();

  public int solution(int cacheSize, String[] cities) {
    int seconds = 0;

    for (int i = 0; i < cities.length; i++) {
      String upperName = cities[i].toUpperCase();

      if (cacheList.contains(upperName))  { //캐시 hit
        seconds++;
        cacheList.remove(upperName);
        cacheList.add(upperName);
      } else { //캐시 miss
        seconds += 5;

        if (cacheSize == 0) continue;

        if (cacheList.size() < cacheSize) {
          cacheList.add(upperName);
        } else {
          cacheList.remove(cacheList.get(0));
          cacheList.add(upperName);
        }
      }
    }

    return seconds;
  }
}
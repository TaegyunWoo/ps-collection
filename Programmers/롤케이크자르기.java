import java.util.*;

class Solution {
  HashMap<Integer, Integer> toppingMap = new HashMap<>(); //<토핑종류, 개수>
  HashSet<Integer> dividedToppingSet = new HashSet<>(); //<나눴을 때 가질 수 있는 토핑 종류>

  public int solution(int[] topping) {
    int answer = 0;

    initMap(topping);

    for (int i = 0; i < topping.length; i++) {
      dividedToppingSet.add(topping[i]);
      subOneFromMap(topping[i]);
      if (checkIsSame()) answer++;
    }

    return answer;
  }

  private void initMap(int[] topping) {
    for (int i = 0; i < topping.length; i++) {
      if (toppingMap.containsKey(topping[i])) {
        toppingMap.put(topping[i], toppingMap.get(topping[i]) + 1);
      } else {
        toppingMap.put(topping[i], 1);
      }
    }
  }

  private void subOneFromMap(int topping) {
    if (toppingMap.get(topping) == 1) {
      toppingMap.remove(topping);
    } else {
      toppingMap.put(topping, toppingMap.get(topping) - 1);
    }
  }

  private boolean checkIsSame() {
    return toppingMap.size() == dividedToppingSet.size();
  }
}
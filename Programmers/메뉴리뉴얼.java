import java.util.*;

class Solution {
  public HashMap<String, Info>[] infoMapAry = new HashMap[11];
  public boolean[] visited;
  public List<String> answerList = new ArrayList<>();

  public String[] solution(String[] orders, int[] course) {
    //infoMapAry 초기화
    for (int i = 0; i < infoMapAry.length; i++) {
      infoMapAry[i] = new HashMap<>();
    }

    //각 order 마다, 가능한 조합별로 count
    for (String order : orders) {
      visited = new boolean[order.length()];
      // order를 오름차순으로 정렬
      char[] orderCharAry = order.toCharArray();
      Arrays.sort(orderCharAry);

      for (int i = 0; i < course.length; i++) {
        combination(orderCharAry, 0, course[i]);
      }
    }

    //정답 도출
    for (int i = 0; i < infoMapAry.length; i++) {
      HashMap<String, Info> infoMap = infoMapAry[i];
      List<Info> infoList = new ArrayList<>();

      if (infoMap.size() == 0) continue;

      for (String key : infoMap.keySet()) {
        Info info = infoMap.get(key);
        infoList.add(info);
      }

      //count 내림차순 정렬
      infoList.sort((a, b) -> {
        return b.count - a.count;
      });

      //정답 추가
      int tmp = 0;
      for (int u = 0; u < infoList.size(); u++) {
        if (tmp > infoList.get(u).count) break;
        if (infoList.get(u).count == 1) break;
        answerList.add(infoList.get(u).combination);
        tmp = infoList.get(u).count;
      }
    }

    //정답 오름차순 정렬
    answerList.sort(Comparator.naturalOrder());


    return answerList.toArray(new String[] {});
  }

  private void combination(char[] order, int depth, int r) {
    if (r == 0) {
      //조합을 HashMap에 추가
      addInfo(order);
      return;
    }

    for (int i = depth; i < order.length; i++) {
      visited[i] = true;

      combination(order, i+1, r-1);

      visited[i] = false;
    }

  }

  private void addInfo(char[] order) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < visited.length; i++) {
      if (visited[i]) sb.append(order[i]);
    }

    String key = sb.toString();
    if (infoMapAry[key.length()].containsKey(key)) {
      infoMapAry[key.length()].get(key).increaseCount();
    } else {
      Info info = new Info(key);
      infoMapAry[key.length()].put(key, info);
    }
  }

  public class Info {
    public String combination;
    public int count = 1;
    public Info(String combination) {
      this.combination = combination;
    }
    public void increaseCount() {
      this.count++;
    }
  }
}
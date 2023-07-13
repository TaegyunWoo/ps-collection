import java.util.*;

class Solution {
  List<HashSet<String>> setList = new ArrayList<>();

  public int[] solution(String s) {
    initSetList(s);

    //sort by set size
    setList.sort((a, b) -> a.size() - b.size());

    //get answer
    int[] answer = new int[setList.get(setList.size()-1).size()];
    int answerIdx = 0;
    HashSet<String> tmpSet = new HashSet<>();
    for (HashSet<String> set : setList) {
      for (String num : set) {
        if (tmpSet.contains(num)) continue; //이미 추가된 수라면
        answer[answerIdx++] = Integer.parseInt(num);
        tmpSet.add(num);
      }
    }

    return answer;
  }

  private void initSetList(String s) {
    HashSet<String> set = new HashSet<>();
    StringBuilder num = new StringBuilder();

    for (int i = 0; i < s.length()-1; i++) {
      char c = s.charAt(i);

      if (c == '{') continue;

      if (c == '}') {
        set.add(num.toString());
        num.setLength(0);

        setList.add(set);
        set = new HashSet<>();
      } else if (c >= '0' && c <= '9') {
        num.append(c);
      } else if (c == ',') {
        if (s.charAt(i+1) != '{') {
          set.add(num.toString());
          num.setLength(0);
        }
      }
    }
  }
}
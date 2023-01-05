import java.util.*;

class Solution {
  HashMap<String, Integer> dic = new HashMap<>(); // <단어, 색인번호>
  List<Integer> answerList = new ArrayList<>();

  public int[] solution(String msg) {
    initDic();

    int start = 0;
    int end = 1;
    while (start < msg.length()) {
      String msgWord = msg.substring(start, end);
      if (dic.containsKey(msgWord)) { //dic에 word가 있는 경우
        if (end < msg.length()) {
          end++;
          continue;
        } else {
          answerList.add(dic.get(msgWord));
        }
      } else { //dic에 word가 없는 경우
        dic.put(msgWord, dic.size() + 1);
        end--;
        msgWord = msg.substring(start, end);
        answerList.add(dic.get(msgWord));
      }

      start = end;
      end = start + 1;
    }

    return answerList.stream().mapToInt(i->i).toArray();
  }

  private void initDic() {
    for (char c = 'A'; c <= 'Z'; c++) {
      dic.put(String.valueOf(c), c - 'A' + 1);
    }
  }
}
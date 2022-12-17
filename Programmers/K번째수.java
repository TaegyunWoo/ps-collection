import java.util.*;

class Solution {
  public int[] solution(int[] array, int[][] commands) {
    int[] answer = new int[commands.length];

    //commands 개수만큼 반복
    for (int c = 0; c < commands.length; c++) {
      //부분 배열 만들기
      int i = commands[c][0];
      int j = commands[c][1];
      int k = commands[c][2];
      ArrayList<Integer> tmp = new ArrayList<>();
      for (int index = i-1; index < j; index++) {
        tmp.add(array[index]);
      }

      //오름차순 정렬
      Collections.sort(tmp);

      //k번째 수 저장
      answer[c] = tmp.get(k-1);
    }

    return answer;
  }
}
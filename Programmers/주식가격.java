import java.util.*;

class Solution {
  Stack<Info> stack = new Stack<>();

  public int[] solution(int[] prices) {
    //countAry 초기화
    int[] countAry = new int[prices.length];
    for (int i = 0; i < countAry.length; i++) {
      countAry[i] = countAry.length - i - 1;
    }

    //Stack 계산
    for (int i = 0; i < prices.length; i++) {
      //스택이 비어있는 경우
      if (stack.isEmpty()) {
        stack.push(new Info(i, prices[i]));
        continue;
      }

      //스택 상단 값이 현재 가격보다 더 크다면
      if (prices[i] < stack.peek().price) {
        Info poppedItem = stack.pop(); //스택에서 제거
        countAry[poppedItem.index] -= prices.length - i - 1; //pop된 요소의 count 감소
        //반복
        i--;
        continue;
      }

      //push
      stack.push(new Info(i, prices[i]));
    }

    return countAry;
  }

  class Info {
    public int index;
    public int price;

    public Info(int i, int p) {
      this.index = i;
      this.price = p;
    }
  }
}
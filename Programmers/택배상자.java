import java.util.*;

class Solution {
  Stack<Integer> subStack = new Stack<>();
  int result = 0;

  public int solution(int[] order) {
    int orderIdx = 0;

    //메인벨트: 1~n까지 반복
    for (int box = 1; box <= order.length; box++) {
      if (order[orderIdx] == box) {
        result++;
        orderIdx++;
      } else {
        orderIdx = checkSubStack(order, orderIdx);
        subStack.push(box);
      }
    }

    //서브벨트에 남아있는 것 확인
    checkSubStack(order, orderIdx);

    return result;
  }

  private int checkSubStack(int[] order, int orderIdx) {
    while (!subStack.isEmpty()) {
      if (subStack.peek() == order[orderIdx]) {
        subStack.pop();
        result++;
        orderIdx++;
      } else {
        break;
      }
    }
    return orderIdx;
  }
}
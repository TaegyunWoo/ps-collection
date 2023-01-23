import java.util.*;

class Solution {
  PriorityQueue<Result> queue = new PriorityQueue<>();
  int[] discountCompAry;
  int[] discountRateAry = {10, 20, 30, 40};

  public int[] solution(int[][] users, int[] emoticons) {
    discountCompAry = new int[emoticons.length];

    brute(0, users, emoticons);
    Result answerResult = queue.poll();

    return new int[] {answerResult.subNum, answerResult.earnMoney};
  }

  private void brute(int depth, int[][] users, int[] emoticons) {
    if (depth == discountCompAry.length) {
      //할인율에 따른 가입자수, 판매액 계산 결과 enqueue
      Result result = cal(users, emoticons);
      queue.offer(result);
      return;
    }

    for (int i = 0; i < discountRateAry.length; i++) {
      discountCompAry[depth] = discountRateAry[i];
      brute(depth + 1, users, emoticons);
    }
  }

  private Result cal(int[][] users, int[] emoticons) {
    int totalSubNum = 0;
    int totalEarnMoney = 0;

    for (int i = 0; i < users.length; i++) {
      int userPay = 0;
      int discountRateToBuy = users[i][0];
      int subLimit = users[i][1];

      //구매한 금액 계산
      for (int u = 0; u < emoticons.length; u++) {
        //구매할 수 있는 할인률보다 낮은 경우
        if (discountCompAry[u] < discountRateToBuy) continue;
        userPay += getDiscountResult(emoticons[u], discountCompAry[u]);
      }

      //이모티콘 플러스 가입 여부
      if (userPay >= subLimit) totalSubNum++;
      else totalEarnMoney += userPay;
    }

    return new Result(totalSubNum, totalEarnMoney);
  }

  private int getDiscountResult(int original, int rate) {
    return (original * (100 - rate)) / 100;
  }

  class Result implements Comparable<Result> {
    public int subNum;
    public int earnMoney;
    public Result(int subNum, int earnMoney) {
      this.subNum = subNum;
      this.earnMoney = earnMoney;
    }

    @Override
    public int compareTo(Result other) {
      if (this.subNum > other.subNum) return -1;
      else if (this.subNum == other.subNum) {
        if (this.earnMoney > other.earnMoney) return -1;
      }
      return 1;
    }
  }
}
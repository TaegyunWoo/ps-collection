class Solution {
  int[] weightArray; //index=몸무게, value=존재하는사람수
  double[] dw = {1, 2, 1.0/2.0, 3.0/2.0, 2.0/3.0, 3.0/4.0, 4.0/3.0};

  public long solution(int[] weights) {
    //init
    weightArray = new int[1001];
    for (int i = 0; i < weights.length; i++) {
      weightArray[weights[i]]++;
    }

    //solution
    long answer = 0;
    for (double w : weights) {
      for (int i = 0; i < dw.length; i++) {
        int index = (int) (w * dw[i]);

        if (index != (w * dw[i])) continue; //정수 무게가 아닌 경우
        if (index >= weightArray.length) continue; //존재하지 않는 무게라면

        answer += weightArray[index]; //정답 count
        if (i == 0) answer--; //자기자신은 제외
      }
    }

    return answer / 2; //2번씩 중복되서 세기 때문에, 2를 나눠야함
  }
}
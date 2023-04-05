import java.util.*;

class Solution {
    List<Integer> answerList = new ArrayList<>();

    public int[] solution(int n, long left, long right) {
        int blockNum = (int) (left / n); //블록 번호 구하기 (0부터 시작)
        int itemNum = (int) (left % n); //시작 블록의 몇번째 원소부터 시작하는지 (0부터 시작)
        System.out.println(blockNum);

        //정답 리스트 만들기
        int startValue = blockNum + 1; //처음 시작할 원소 값
        int sameValueCount = 0; //앞부분에 반복되는 숫자 개수
        int totalCount = 0; //전체 반복 횟수
        int item = startValue; //원소 값

        //정답 배열을 만들때까지 반복
        while (answerList.size() <= right - left) {
            sameValueCount++;
            totalCount++;

            //현재 블록 순서 값에 맞게 동일한 숫자를 반복했다면
            if (sameValueCount > blockNum + 1) item++;

            //생략할 앞 부분이 아니라면
            if (totalCount > itemNum) {
                answerList.add(item);
            }

            //다음 블록 순서라면
            if (totalCount % n == 0) {
                item = startValue + (totalCount / n);
                sameValueCount = 0;
                blockNum++;
            }
        }

        return answerList.stream().mapToInt(i->i).toArray();
    }
}
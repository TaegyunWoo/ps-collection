import java.io.*;
import java.util.*;

public class Q10431 {
  public static int testCase;
  public static List<Integer> heightList;
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    testCase = Integer.parseInt(br.readLine());

    while (testCase-- > 0) {
      String[] order = br.readLine().split(" ");
      int count = 0;
      heightList = new LinkedList<>();
      heightList.add(Integer.parseInt(order[1]));

      //계산
      for (int i = 2; i < order.length; i++) {
        int currentHeight = Integer.parseInt(order[i]);
        int higherHeightIndex = heightList.size() - 1;

        //더 큰 수 중, 가장 작은 수 탐색
        while (higherHeightIndex >= 0) {
          if (heightList.get(higherHeightIndex) > currentHeight) {
            if (higherHeightIndex == 0) break;
            higherHeightIndex--;
          }
          else {
            higherHeightIndex++;
            break;
          }
        }

        count += heightList.size() - higherHeightIndex; //이동 횟수 더하기
        heightList.add(higherHeightIndex, currentHeight); //삽입
      }

      answer.append(order[0] + " " + count + "\n");
    }

    System.out.println(answer.toString());
  }
}

import java.io.*;

public class Q1515 {
  public static String input;
  public static int answer;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    input = br.readLine();
    br.close();

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    int num = 1;
    int inputLeft = 0;
    int inputRight = 1;
    boolean wasMatched = false;

    while (true) {
      char[] inputSub = input.substring(inputLeft, inputRight).toCharArray();
      char[] numString = String.valueOf(num).toCharArray();
      int inputSubIdx = 0;

      for (int i = 0; i < numString.length; i++) {
        if (inputSubIdx == inputSub.length) continue;
        if (inputSub[inputSubIdx] == numString[i]) {
          inputSubIdx++;
        }
      }

      if (inputSubIdx == inputSub.length) {
        wasMatched = true;
        inputRight++;
      } else {
        if (wasMatched) {
          inputLeft = inputRight - 1;
          inputRight = inputLeft + 1;
        }
        wasMatched = false;
        num++;
      }

      if (inputRight == input.length() + 1) break;
    }

    answer = num;
  }
}

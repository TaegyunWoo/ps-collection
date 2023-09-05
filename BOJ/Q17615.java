import java.io.*;

public class Q17615 {
  static final int MAX = (int) 1e9;
  static int n;
  static char[] inputAry;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    inputAry = new char[n];
    String tmp = br.readLine();
    for (int i = 0; i < n; i++) {
      inputAry[i] = tmp.charAt(i);
    }

    //solution
    int answer = solution();

    //print answer
    System.out.println(answer);
  }

  private static int solution() {
    int minCount = MAX;

    //R을 뒤로 모으는 경우
    int count = collectBack('R');
    minCount = Math.min(minCount, count);

    //R을 앞으로 모으는 경우
    count = collectFront('R');
    minCount = Math.min(minCount, count);

    //B을 뒤로 모으는 경우
    count = collectBack('B');
    minCount = Math.min(minCount, count);

    //B을 앞으로 모으는 경우
    count = collectFront('B');
    minCount = Math.min(minCount, count);

    return minCount;
  }

  private static int collectBack(char collectChar) {
    int count = 0;
    boolean countAvailable = false;
    char oppositeChar = (collectChar == 'R') ? 'B' : 'R';
    for (int i = n-1; i >= 0; i--) {
      if (inputAry[i] == oppositeChar) {
        countAvailable = true;
      } else {
        if (countAvailable) count++;
      }
    }
    return count;
  }

  private static int collectFront(char collectChar) {
    int count = 0;
    boolean countAvailable = false;
    char oppositeChar = (collectChar == 'R') ? 'B' : 'R';
    for (int i = 0; i < n; i++) {
      if (inputAry[i] == oppositeChar) {
        countAvailable = true;
      } else {
        if (countAvailable) count++;
      }
    }
    return count;
  }
}
import java.io.*;

public class Q2607 {
  public static int n;
  public static String targetWord;
  public static String[] wordAry;
  public static int[] targetWordCharCount;
  public static int answer = 0;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    targetWord = br.readLine();
    wordAry = new String[n-1];
    for (int i = 0; i < n-1; i++) {
      wordAry[i] = br.readLine();
    }

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    //Init targetWordCharCount
    targetWordCharCount = new int['Z' - 'A' + 1];
    for (int i = 0; i < targetWord.length(); i++) {
      int idx = targetWord.charAt(i) - 'A';
      targetWordCharCount[idx]++;
    }

    //Calculate
    for (int i = 0; i < wordAry.length; i++) {
      if ( isSimilar(wordAry[i]) ) answer++;
    }
  }

  //비슷한지 확인
  private static boolean isSimilar(String word) {
    int[] wordCharCount = new int['Z' - 'A' + 1];

    //개수 count
    for (int i = 0; i < word.length(); i++) {
      int idx = word.charAt(i) - 'A';
      wordCharCount[idx]++;
    }

    //개수 비교
    int countGap = 0;
    for (int i = 0; i < targetWordCharCount.length; i++) {
      countGap += Math.abs(targetWordCharCount[i] - wordCharCount[i]);
    }

    //동일한 문자 개수이거나, 하나의 문자만 더하거나 빼서 비슷해진다면
    if (countGap <= 1) return true;

    //하나의 문자만 교체해서 비슷해진다면
    if (countGap == 2) {
      if (targetWord.length() == word.length()) return true;
    }

    return false;
  }


}

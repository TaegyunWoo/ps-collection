import java.io.*;
import java.util.*;

public class Q20920 {
  public static int n;
  public static int m;
  public static String[] inputAry;
  public static HashMap<String, Integer> wordCountMap = new HashMap<>();
  public static List<String> wordList = new ArrayList<>();
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    inputAry = new String[n];
    for (int i = 0; i < n; i++) {
      inputAry[i] = br.readLine();
    }

    //Solution
    solution();

    //정답 출력
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.append(answer);
    bw.flush();
  }

  private static void solution() {
    //단어 등장 횟수 count 및 단어 종류 저장
    for (String word : inputAry) {
      if (word.length() < m) continue;

      if (wordCountMap.containsKey(word)) {
        wordCountMap.put(word, wordCountMap.get(word) + 1);
      } else {
        wordCountMap.put(word, 1);
        wordList.add(word);
      }

    }

    //정렬
    wordList.sort((a, b) -> {
      if (wordCountMap.get(a) > wordCountMap.get(b)) return -1;
      else if (wordCountMap.get(a) < wordCountMap.get(b)) return 1;
      else {
        if (a.length() > b.length()) return -1;
        else if (a.length() < b.length()) return 1;
        else return a.compareTo(b);
      }
    });

    //정답 기록
    for (String word : wordList) {
      answer.append(word + "\n");
    }
  }
}

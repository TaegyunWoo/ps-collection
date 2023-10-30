import java.io.*;
import java.util.*;

public class Q2179 {
  static int n;
  static Map<String, Info> wordInfoMap = new HashMap<>();
  static String[] sortedWords;
  static int maxAllSimilarScore = 0;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    sortedWords = new String[n];
    for (int i = 0; i < n; i++) {
      String word = br.readLine();
      wordInfoMap.put(word, new Info(i));
      sortedWords[i] = word;
    }

    //solution
    Arrays.sort(sortedWords); //sort words
    for (int i = 0; i < n-1; i++) {
      String wordA = sortedWords[i];
      Info infoA = wordInfoMap.get(wordA);

      //wordA 이후 단어들에 대해 검사
      for (int u = i + 1; u < n; u++) {
        String wordB = sortedWords[u];
        Info infoB = wordInfoMap.get(wordB);

        int score = getSimilarScore(wordA, wordB);

        if (score < infoA.maxSimilarScore) break; //만약 점수가 더 낮아지면, 다른 모든 단어를 확인할 필요 X

        //infoA 업데이트
        if (infoA.maxSimilarScore == score) {
          //순서비교
          int maxSimilarWordIdxAboutA
              = wordInfoMap.get(infoA.maxSimilarWord).idx;
          if (maxSimilarWordIdxAboutA > infoB.idx) { //wordB의 순서가 더 빠를 때 갱신
            infoA.maxSimilarWord = wordB;
          }
        } else if (infoA.maxSimilarScore < score) { //갱신
          infoA.maxSimilarScore = score;
          infoA.maxSimilarWord = wordB;
        }

        //infoB 업데이트
        if (infoB.maxSimilarScore == score) {
          //순서비교
          int maxSimilarWordIdxAboutB
              = wordInfoMap.get(infoB.maxSimilarWord).idx;
          if (maxSimilarWordIdxAboutB > infoA.idx) { //wordA의 순서가 더 빠를 때 갱신
            infoB.maxSimilarWord = wordA;
          }
        } else if (infoB.maxSimilarScore < score) { //갱신
          infoB.maxSimilarScore = score;
          infoB.maxSimilarWord = wordA;
        }

        //전체 최대 유사도 갱신
        maxAllSimilarScore = Math.max(maxAllSimilarScore, score);
      } //내부 for문 종료

    } //외부 for문 종료

    //get answer
    String answerS = "";
    int answerSIdx = (int) 1e9;
    String answerT = "";
    for (String word : wordInfoMap.keySet()) {
      Info info = wordInfoMap.get(word);

      if (info.maxSimilarScore == maxAllSimilarScore) { //현재 word가 최대 유사도라면
        if (info.idx < answerSIdx) { //기존 S보다 현재 word가 더 빨리 입력됐다면
          answerS = word; //S 갱신
          answerSIdx = info.idx; //S idx 갱신
          answerT = info.maxSimilarWord; //이때 반드시 T도 갱신
        }
      }
    }

    //print answer
    System.out.println(answerS);
    System.out.println(answerT);
  }

  private static int getSimilarScore(String wordA, String wordB) {
    int idxA = 0;
    int idxB = 0;
    int score = 0;

    while (idxA != wordA.length() && idxB != wordB.length()) {
      if (wordA.charAt(idxA) != wordB.charAt(idxB)) break;
      score++;
      idxA++;
      idxB++;
    }

    return score;
  }

  public static class Info {
    public int idx;
    public int maxSimilarScore;
    public String maxSimilarWord;

    public Info(int idx) {
      this.idx = idx;
      maxSimilarScore = -1;
      maxSimilarWord = null;
    }
  }
}
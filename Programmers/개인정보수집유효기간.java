import java.util.*;

class Solution {
  List<Integer> answerList = new ArrayList<>();
  HashMap<String, Integer> termsMap = new HashMap<>();

  public int[] solution(String today, String[] terms, String[] privacies) {
    initTermsMap(terms);

    for (int i = 0; i < privacies.length; i++) {
      String[] parseResult = privacies[i].split(" ");
      String startDate = parseResult[0];
      String term = parseResult[1];
      String destroyDate = addMonth(startDate, termsMap.get(term));

      if (isDestroyable(today, destroyDate)) answerList.add(i+1);
    }

    return answerList.stream().mapToInt(i->i).toArray();
  }

  private void initTermsMap(String[] terms) {
    for (int i = 0; i < terms.length; i++) {
      String[] parseResult = terms[i].split(" ");
      termsMap.put(parseResult[0], Integer.parseInt(parseResult[1]));
    }
  }

  private String addMonth(String date, int addition) {
    String[] parseResult = date.split("\\.");
    int year = Integer.parseInt(parseResult[0]);
    int month = Integer.parseInt(parseResult[1]);
    int day = Integer.parseInt(parseResult[2]);

    year += addition / 12;
    month += addition % 12;
    if (month > 12) {
      year++;
      month -= 12;
    }

    return year + "." + month + "." + day;
  }

  private boolean isDestroyable(String nowDate, String destroyDate) {
    String[] nowParseResult = nowDate.split("\\.");
    String[] destroyParseResult = destroyDate.split("\\.");
    int nYear = Integer.parseInt(nowParseResult[0]);
    int nMonth = Integer.parseInt(nowParseResult[1]);
    int nDay = Integer.parseInt(nowParseResult[2]);
    int dYear = Integer.parseInt(destroyParseResult[0]);
    int dMonth = Integer.parseInt(destroyParseResult[1]);
    int dDay = Integer.parseInt(destroyParseResult[2]);

    if (dYear < nYear) return true;
    else if (dYear > nYear) return false;
    else {
      if (dMonth < nMonth) return true;
      else if (dMonth > nMonth) return false;
      else {
        if (dDay <= nDay) return true;
        else return false;
      }
    }
  }
}
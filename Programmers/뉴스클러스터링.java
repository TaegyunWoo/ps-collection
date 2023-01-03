import java.util.*;

class Solution {
  Set<String> allElementSet = new HashSet<>(); //모든 원소 저장
  HashMap<String, Integer> elementCountMap1 = new HashMap<>(); //str1의 원소 저장
  HashMap<String, Integer> elementCountMap2 = new HashMap<>(); //str2의 원소 저장

  public int solution(String str1, String str2) {
    parseString(str1, 1);
    parseString(str2, 2);

    double interSize = calInterSize();
    double unionSize = calUnionSize();
    if (unionSize == 0) return 65536;

    return (int) (interSize / unionSize * 65536);
  }

  private void parseString(String str, int strNum) {
    str = str.toUpperCase();

    for (int i = 0; i < str.length()-1; i++) {
      String subString = str.substring(i, i+2);

      if (subString.charAt(0) < 'A' || subString.charAt(0) > 'Z') continue;
      if (subString.charAt(1) < 'A' || subString.charAt(1) > 'Z') continue;

      if (strNum == 1) {
        if (!elementCountMap1.containsKey(subString)) {
          elementCountMap1.put(subString, 1);
        } else {
          elementCountMap1.put(subString, elementCountMap1.get(subString) + 1);
        }
        if (!elementCountMap2.containsKey(subString)) {
          elementCountMap2.put(subString, 0);
        }
      } else {
        if (!elementCountMap2.containsKey(subString)) {
          elementCountMap2.put(subString, 1);
        } else {
          elementCountMap2.put(subString, elementCountMap2.get(subString) + 1);
        }
        if (!elementCountMap1.containsKey(subString)) {
          elementCountMap1.put(subString, 0);
        }
      }

      allElementSet.add(subString);
    }
  }

  //교집합 크기 계산
  private double calInterSize() {
    double size = 0;

    for (String element : allElementSet) {
      size += Math.min(elementCountMap1.get(element), elementCountMap2.get(element));
    }

    return size;
  }

  //합집합 크기 계산
  private double calUnionSize() {
    double size = 0;

    for (String element : allElementSet) {
      size += Math.max(elementCountMap1.get(element), elementCountMap2.get(element));
    }

    return size;
  }
}
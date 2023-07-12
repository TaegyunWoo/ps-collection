import java.util.*;

class Solution {
  char[][] operatorCase = {
      {'+', '-', '*'}, {'+', '*', '-'},
      {'-', '+', '*'}, {'-', '*', '+'},
      {'*', '+', '-'}, {'*', '-', '+'}
  };

  public long solution(String expression) {
    long answer = 0;

    for (char[] operatorAry : operatorCase) {
      List<String> expressionList = toList(expression);

      for (char operator : operatorAry) {
        List<String> resultList = calculate(expressionList, operator + "");
        expressionList = resultList;
      }

      long resultNum = Long.parseLong(expressionList.get(0));
      answer = Math.max(answer, Math.abs(resultNum));
    }

    return answer;
  }

  private List toList(String expression) {
    List<String> result = new ArrayList<>();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c >= '0' && c <= '9') { //숫자라면
        sb.append(c);
      } else { //연산자라면
        result.add(sb.toString()); //숫자 저장
        sb.setLength(0); //비우기
        result.add(c + ""); //연산자 저장
      }
    }
    result.add(sb.toString()); //마지막 숫자 저장

    return result;
  }

  private List<String> calculate(List<String> expressionList, String operator) {
    List<String> resultList = new ArrayList<>();

    for (int index = 0; index < expressionList.size(); index++) {
      String item = expressionList.get(index);

      if (item.equals(operator)) { //계산할 연산자라면
        long operand1 = Long.parseLong(resultList.get(resultList.size()-1));
        long operand2 = Long.parseLong(expressionList.get(index+1));
        long result = 0;

        switch (operator) {
          case "+":
            result = operand1 + operand2;
            break;
          case "-":
            result = operand1 - operand2;
            break;
          case "*":
            result = operand1 * operand2;
            break;
        }

        resultList.remove(resultList.size()-1);
        resultList.add(String.valueOf(result));
        index++;

      } else { //계산할 연산자가 아니라면
        resultList.add(item);
      }
    }

    return resultList;
  }

}
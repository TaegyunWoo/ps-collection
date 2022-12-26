/**
 * - idea: 숫자를 순서대로 붙이면서 가장 큰 수를 만든다.
 *         만약 현재 만들어진 수의 마지막 자리의 수가 현재 새로 붙일 수보다 작으면 그 수를 제거하고,
 *         현재 새로 붙일 수를 붙인다.
 *         그러면 자연스럽게 큰 수가 만들어진다.
 * - Stack 개념 활용해서 풂
 */
class Solution {
  StringBuilder answer = new StringBuilder();

  public String solution(String number, int k) {
    for (int i = 0; i < number.length(); i++) {
      char newNum = number.charAt(i);

      if (answer.length() != 0) {
        char currentNum = answer.charAt(answer.length() - 1);
        if (currentNum < newNum && k > 0) {
          answer.delete(answer.length() - 1, answer.length());
          i--;
          k--;
          continue;
        }
      }

      answer.append(newNum);
    }

    //k만큼 제거하지 못한 경우 고려 (숫자가 같은 경우 ex. num = "222" , k = 1)
    if (k != 0) {
      answer.delete(answer.length() - k, answer.length());
    }

    return answer.toString();
  }
}
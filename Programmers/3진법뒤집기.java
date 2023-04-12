class Solution {
  public int solution(int n) {
    String s = trans(n);
    return trans(s);
  }

  public String trans(int n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      sb.append(n%3);
      n /= 3;
    }
    return sb.toString();
  }

  public int trans(String s) {
    int result = 0;
    int tmp = 1;
    for (int i = s.length()-1; i >= 0; i--) {
      if (s.charAt(i) != '0') {
        result += tmp * (s.charAt(i) - '0');
      }
      tmp *= 3;
    }
    return result;
  }
}
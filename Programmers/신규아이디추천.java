class Solution {
  public String solution(String new_id) {
    new_id = step1(new_id);
    new_id = step2(new_id);
    new_id = step3(new_id);
    new_id = step4(new_id);
    new_id = step5(new_id);
    new_id = step6(new_id);
    new_id = step7(new_id);
    return new_id;
  }

  private String step1(String id) {
    return id.toLowerCase();
  }

  private String step2(String id) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < id.length(); i++) {
      char c = id.charAt(i);
      if (c >= 'a' && c <= 'z') result.append(c);
      else if (c >= '0' && c <= '9') result.append(c);
      else if (c == '-') result.append(c);
      else if (c == '_') result.append(c);
      else if (c == '.') result.append(c);
    }
    return result.toString();
  }

  private String step3(String id) {
    StringBuilder result = new StringBuilder();
    int countDot = 0;
    for (int i = 0; i < id.length(); i++) {
      char c = id.charAt(i);
      if (c == '.') {
        countDot++;
      } else {
        if (countDot > 0) {
          result.append('.');
        }
        countDot = 0;
        result.append(c);
      }
    }
    if (countDot > 0) result.append('.');

    return result.toString();
  }

  private String step4(String id) {
    if (id.length() == 0) return id;
    if (id.charAt(0) == '.') id = id.substring(1, id.length());
    if (id.length() == 0) return id;
    if (id.charAt(id.length()-1) == '.') id = id.substring(0, id.length() - 1);
    return id;
  }

  private String step5(String id) {
    if (id.isEmpty()) return "a";
    return id;
  }

  private String step6(String id) {
    if (id.length() >= 16) {
      id = id.substring(0, 15);
      if (id.charAt(id.length() - 1) == '.') return id.substring(0, 14);
      return id;
    }
    return id;
  }

  private String step7(String id) {
    if (id.length() == 1) {
      return id + id.charAt(id.length()-1) + id.charAt(id.length()-1);
    } else if (id.length() == 2) {
      return id + id.charAt(id.length()-1);
    }
    return id;
  }
}
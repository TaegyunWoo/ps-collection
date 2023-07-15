import java.util.*;

class Solution {
  HashSet<String> combResultSet = new HashSet<>();
  SortedSet<String> matchSet = new TreeSet<>();
  int answer = 0;

  public int solution(String[] user_id, String[] banned_id) {
    brute(user_id, banned_id, 0);

    return answer;
  }

  private void brute(String[] userId, String[] bannedId, int banIdx) {
    if (matchSet.size() == bannedId.length) {
      if (!isDuplicatedResult()) {
        answer++;
      }

      return;
    }

    for (int userIdx = 0; userIdx < userId.length; userIdx++) {
      if (matchSet.contains(userId[userIdx])) continue; //이미 선택한 ID는 제외

      boolean isMatch = valid(userId[userIdx], bannedId[banIdx]); //매칭되는지 확인

      if (!isMatch) continue; //매치되지 않는다면

      matchSet.add(userId[userIdx]); //ID 선택
      brute(userId, bannedId, banIdx+1); //재귀호출
      matchSet.remove(userId[userIdx]); //ID 선택 해제
    }
  }

  private boolean valid(String userId, String bannedId) {
    if (userId.length() != bannedId.length()) return false;

    for (int i = 0; i < userId.length(); i++) {
      if (bannedId.charAt(i) == '*') continue;
      if (userId.charAt(i) != bannedId.charAt(i)) return false;
    }

    return true;
  }

  private boolean isDuplicatedResult() {
    StringBuilder sb = new StringBuilder();
    for (String id : matchSet) {
      sb.append(id);
    }
    String resultString = sb.toString();

    if (combResultSet.contains(resultString)) return true;

    combResultSet.add(resultString);
    return false;
  }
}
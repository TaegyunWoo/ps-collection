import java.util.*;

class Solution {

  public int solution(String skill, String[] skill_trees) {
    //init
    HashSet<Character> skillSet = new HashSet<>();
    for (int i = 0; i < skill.length(); i++) {
      skillSet.add(skill.charAt(i));
    }
    int answer = 0;

    //solution
    for (String skillTree : skill_trees) {
      int skillIdx = 0;
      boolean isValid = true;

      for (int i = 0; i < skillTree.length(); i++) {
        char c = skillTree.charAt(i);

        if (!skillSet.contains(c)) continue; //스킬 순서와 관련 없는 경우
        if (skill.charAt(skillIdx) != c) { //스킬 순서와 관련있지만 순서가 앞선 경우
          isValid = false;
          break;
        }
        skillIdx++;
      }

      if (isValid) answer++;
    }

    return answer;
  }
}
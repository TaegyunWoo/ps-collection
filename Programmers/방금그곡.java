import java.util.*;

class Solution {
  PriorityQueue<MatchedMusic> answerQueue = new PriorityQueue<>();

  public String solution(String m, String[] musicinfos) {
    for (int index = 0; index < musicinfos.length; index++) {
      String[] musicInfo = musicinfos[index].split(",");
      int totalMinutes = getTotalMinutes(musicInfo[0], musicInfo[1]);
      String totalMelody = getTotalMelody(totalMinutes, musicInfo[3]);
      MatchedMusic matchedMusic = findMatchedMusic(index, totalMinutes, musicInfo[2], totalMelody, m);
      if (matchedMusic != null) {
        answerQueue.offer(matchedMusic);
      }
    }

    if (answerQueue.isEmpty()) return "(None)";
    return answerQueue.poll().name;
  }

  private int getTotalMinutes(String start, String end) {
    String[] startAry = start.split(":");
    String[] endAry = end.split(":");
    int startHour = Integer.parseInt(startAry[0]);
    int startMinute = Integer.parseInt(startAry[1]);
    int endHour = Integer.parseInt(endAry[0]);
    int endMinute = Integer.parseInt(endAry[1]);

    return (endHour - startHour) * 60 + (endMinute - startMinute);
  }

  private String getTotalMelody(int totalMinutes, String melodyInfo) {
    StringBuilder totalMelody = new StringBuilder();
    int melodyIndex = 0;
    for (int i = 0; i < totalMinutes; i++) {
      char melodyChar = melodyInfo.charAt(melodyIndex);
      totalMelody.append(melodyChar);
      if (melodyInfo.charAt((melodyIndex+1) % melodyInfo.length()) == '#') {
        char sharp = melodyInfo.charAt((melodyIndex+1) % melodyInfo.length());
        totalMelody.append(sharp);
        melodyIndex = (melodyIndex+2) % melodyInfo.length();
      } else {
        melodyIndex = (melodyIndex+1) % melodyInfo.length();
      }
    }
    return totalMelody.toString();
  }

  private MatchedMusic findMatchedMusic(int index, int totalMinutes, String name, String totalMelody, String m) {
    List<String> totalMelodyList = getMelodyList(totalMelody);
    List<String> mList = getMelodyList(m);
    int totalIndex = 0;
    int mIndex = 0;

    if (totalMelodyList.size() < mList.size()) return null;

    while (totalIndex < totalMelodyList.size()) {
      if (totalMelodyList.get(totalIndex).equals(mList.get(mIndex))) {
        mIndex++;
      } else {
        mIndex = 0;
        if (totalMelodyList.get(totalIndex).equals(mList.get(mIndex))) { //처음 음을 다시 확인
          mIndex++;
        }
      }
      totalIndex++;

      if (mIndex == mList.size()) return new MatchedMusic(index, totalMinutes, name);
    }

    return null;
  }

  private List<String> getMelodyList(String melody) {
    List<String> melodyList = new ArrayList<>();
    for (int i = 0; i < melody.length(); i++) {
      char currentChar = melody.charAt(i);
      if (i != melody.length() - 1) {
        if (melody.charAt(i+1) == '#') {
          String unitMelody = String.valueOf(currentChar) + String.valueOf(melody.charAt(i+1));
          melodyList.add(unitMelody);
          i++;
        } else {
          melodyList.add(String.valueOf(currentChar));
        }
      } else {
        melodyList.add(String.valueOf(currentChar));
      }
    }

    return melodyList;
  }

  class MatchedMusic implements Comparable<MatchedMusic> {
    public int index;
    public int totalMinutes;
    public String name;

    public MatchedMusic() {}

    public MatchedMusic(int index, int totalMinutes, String name) {
      this.index = index;
      this.totalMinutes = totalMinutes;
      this.name = name;
    }

    @Override
    public int compareTo(MatchedMusic other) {
      if (this.totalMinutes > other.totalMinutes) return -1;
      if (this.totalMinutes == other.totalMinutes) {
        if (this.index < other.index) return -1;
      }
      return 1;
    }
  }
}
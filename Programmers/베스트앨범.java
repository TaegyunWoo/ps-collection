import java.util.*;

class Solution {
  Map<String, Integer> genresPlaysMap = new HashMap<>(); //<장르명, 속한 노래들의 총 재생 횟수>
  Map<Integer, Integer> playsMap = new HashMap<>(); //<index, 재생 횟수>
  Map<String, Integer> genresCountMap = new HashMap<>(); //<장르명, 곡개수>
  Map<String, Integer> genresUploadCountMap = new HashMap<>(); //<장르명, 수록된 곡 개수>

  public int[] solution(String[] genres, int[] plays) {
    //장르Map, 재생횟수Map 초기화
    for (int i = 0; i < genres.length; i++) {
      String genre = genres[i];
      int play = plays[i];
      if (genresPlaysMap.containsKey(genre)) {
        genresPlaysMap.put(genre, genresPlaysMap.get(genre) + play);
      } else {
        genresPlaysMap.put(genre, play);
      }
      playsMap.put(i, play);
      if (genresCountMap.containsKey(genre)) {
        genresCountMap.put(genre, genresCountMap.get(genre) + 1);
      } else {
        genresCountMap.put(genre, 1);
      }
    }

    //우선순위 큐 활용
    PriorityQueue<Song> queue = new PriorityQueue<>();
    for (int index = 0; index < plays.length; index++) {
      String genre = genres[index];
      int play = plays[index];
      queue.offer(new Song(index, genre, play));
    }

    //정답출력
    List<Integer> answerList = new ArrayList<>();
    for (int i = 0; i < plays.length; i++) {
      Song song = queue.poll();

      //장르당 최대 2개 곡만 수록 가능하므로, 수록 가능한지 확인
      if (genresUploadCountMap.containsKey(song.genre)) {
        if (genresUploadCountMap.get(song.genre) == 2) continue;
        if (genresUploadCountMap.get(song.genre) == genresCountMap.get(song.genre)) continue;
      }

      //곡 수록하기
      if (genresUploadCountMap.containsKey(song.genre)) {
        genresUploadCountMap.put(song.genre, genresUploadCountMap.get(song.genre) + 1);
      } else {
        genresUploadCountMap.put(song.genre, 1);
      }
      answerList.add(song.index);
    }

    return answerList.stream().mapToInt(i->i).toArray();
  }

  class Song implements Comparable<Song> {
    public int index;
    public String genre;
    public int play;

    public Song(int index, String genre, int play) {
      this.index = index;
      this.genre = genre;
      this.play = play;
    }

    @Override
    public int compareTo(Song other) {
      if (genresPlaysMap.get(this.genre) > genresPlaysMap.get(other.genre)) { //this의 장르의 재생횟수가 더 크다면
        return -1;

      } else if (genresPlaysMap.get(this.genre) == genresPlaysMap.get(other.genre)) { //장르의 재생횟수가 같다면, 장르가 같다는 것을 의미함
        // if (playsMap.get(this.index) > playsMap.get(other.index)) { //this의 재생횟수가 더 크다면
        if (this.play > other.play) { //this의 재생횟수가 더 크다면
          return -1;
        } else if (this.play == other.play) { //this의 재생횟수가 같다면
          if (this.index < other.index) { //this의 index가 더 작다면
            return -1;
          } else { //this의 index가 더 크다면
            return 1;
          }
        } else { //this의 재생횟수가 더 작다면
          return 1;
        }

      } else { //this의 장르의 재생횟수가 더 작다면
        return 1;
      }
    }
  }
}
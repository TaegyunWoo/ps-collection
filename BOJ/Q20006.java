import java.io.*;
import java.util.*;

public class Q20006 {
  public static int p, m;
  public static Player[] playerAry;
  public static List<List<Player>> roomList = new ArrayList<>();
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    p = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    playerAry = new Player[p];
    for (int i = 0; i < playerAry.length; i++) {
      st = new StringTokenizer(br.readLine());
      playerAry[i] = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
    }

    //Solution
    solution();

    //정답 출력
    buildAnswer();
    System.out.println(answer.toString());
  }

  private static void buildAnswer() {
    for (List<Player> room : roomList) {
      //Sort by nickname
      room.sort((a, b) -> {
        return a.nickname.compareTo(b.nickname);
      });

      //Check is full
      if (room.size() == m) answer.append("Started!");
      else answer.append("Waiting!");

      answer.append("\n");

      //player
      for (Player p : room) {
        answer.append(p.level + " " + p.nickname + "\n");
      }
    }
  }

  private static void solution() {
    for (Player newPlayer : playerAry) {
      //Find Correct Room Index
      int roomIndex = getCorrectRoomIndex(newPlayer.level);

      if (roomIndex == -1) { //Create new room and insert player
        createRoom(newPlayer);

      } else { //insert player
        roomList.get(roomIndex).add(newPlayer);
      }
    }
  }

  private static void createRoom(Player newPlayer) {
    List<Player> newRoom = new ArrayList<>();
    newRoom.add(newPlayer);
    roomList.add(newRoom);
  }

  private static int getCorrectRoomIndex(int newLevel) {
    for (int i = 0; i < roomList.size(); i++) {
      List<Player> room = roomList.get(i);
      if (room.size() == m) continue;
      if (room.get(0).level - 10 <= newLevel && room.get(0).level + 10 >= newLevel) return i;
    }
    return -1;
  }

  public static class Player {
    public int level;
    public String nickname;
    public Player(int level, String nickname) {
      this.level = level;
      this.nickname = nickname;
    }
  }
}

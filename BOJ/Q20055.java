import java.io.*;
import java.util.*;

public class Q20055 {
  static int n;
  static int k;
  static Slot[] belt;
  static int putRobotIdx;
  static int removeRobotIdx;


  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    k = Integer.parseInt(tmp[1]);
    tmp = br.readLine().split(" ");
    belt = new Slot[n * 2];
    for (int i = 0; i < belt.length; i++) {
      belt[i] = new Slot(Integer.parseInt(tmp[i]), false);
    }
    putRobotIdx = 0;
    removeRobotIdx = n-1;

    //solution
    int countLoop = 0;
    while (k > 0) {
      countLoop++;
      rotate(); //한칸 회전
      moveRobots(); //모든 로봇 이동
      putRobot(); //로봇 올리기
    }

    //print answer;
    System.out.println(countLoop);
  }

  private static void rotate() {
    putRobotIdx = (putRobotIdx == 0) ? 2 * n - 1 : putRobotIdx - 1;
    removeRobotIdx = (removeRobotIdx == 0) ? 2 * n - 1 : removeRobotIdx - 1;
    belt[removeRobotIdx].hasRobot = false; //로봇 내리기
  }

  private static void moveRobots() {
    //현재 존재하는 모든 로봇들에 대해, 올라간 순서대로 반복
    int idx = removeRobotIdx;
    for (int i = 0; i < 2 * n; i++) {
      //idx 갱신
      idx = (idx == 0) ? 2 * n - 1 : idx - 1;

      int nextIdx = (idx == 2 * n - 1) ? 0 : idx + 1;
      Slot currentSlot = belt[idx];
      Slot nextSlot = belt[nextIdx];

      //이동할 수 있다면 앞으로 한칸 이동
      if (!currentSlot.hasRobot) continue;
      if (nextSlot.durability > 0 && !nextSlot.hasRobot) {
        currentSlot.hasRobot = false;
        nextSlot.hasRobot = true;
        nextSlot.durability--;

        if (nextIdx == removeRobotIdx) belt[removeRobotIdx].hasRobot = false; //로봇 내리기
        if (nextSlot.durability == 0) k--;
      }
    }
  }

  private static void putRobot() {
    Slot putSlot = belt[putRobotIdx];
    if (putSlot.durability > 0) {
      putSlot.hasRobot = true;
      putSlot.durability--;
      if (putSlot.durability == 0) k--;
    }
  }

  public static class Slot {
    public int durability; //내구도
    public boolean hasRobot; //로봇 여부

    public Slot(int durability, boolean hasRobot) {
      this.durability = durability;
      this.hasRobot = hasRobot;
    }
  }
}
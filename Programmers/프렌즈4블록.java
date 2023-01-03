import java.util.*;

class Solution {
  ArrayList<LinkedList<Character>> map = new ArrayList<>();
  ArrayList<PriorityQueue<Integer>> removeQueueList = new ArrayList<>();
  int answer = 0;

  public int solution(int m, int n, String[] board) {
    init(m, n, board);

    while (true) {
      for (int i = 0; i < m; i++) {
        for (int u = 0; u < n; u++) {
          if (map.get(u).size() > i) {
            findRemoveBlocks(i, u);
          }
        }
      }

      if (isAllEmptyQueue()) break;

      for (int i = 0; i < n; i++) {
        removeBlocks(i);
      }
    };

    return answer;
  }

  private void init(int m, int n, String[] board) {
    for (int i = 0; i < n; i++) {
      map.add(new LinkedList<>());
      removeQueueList.add(new PriorityQueue<>(Collections.reverseOrder()));
    }

    for (int i = m-1; i >= 0; i--) {
      for (int u = 0; u < n; u++) {
        map.get(u).add(board[i].charAt(u));
      }
    }
  }

  private void findRemoveBlocks(int currentM, int currentN) {
    int[] dm = {0, 0, 1, 1};
    int[] dn = {0, 1, 0, 1};
    boolean canRemove = true;
    char currentChar = map.get(currentN).get(currentM);

    for (int i = 0; i < 4; i++) {
      int nearM = dm[i] + currentM;
      int nearN = dn[i] + currentN;

      if (nearN >= map.size()) {
        canRemove = false;
        break;
      }
      if (nearM >= map.get(nearN).size()) {
        canRemove = false;
        break;
      }
      if (map.get(nearN).get(nearM) != currentChar) {
        canRemove = false;
        break;
      }
    }

    if (canRemove) {
      for (int i = 0; i < 4; i++) {
        int nearM = dm[i] + currentM;
        int nearN = dn[i] + currentN;

        if (!removeQueueList.get(nearN).contains(nearM))
          removeQueueList.get(nearN).offer(nearM);
      }
    }
  }

  private void removeBlocks(int removeN) {
    PriorityQueue<Integer> removeQueue = removeQueueList.get(removeN);

    while (!removeQueue.isEmpty()) {
      int removeM = removeQueue.poll();
      map.get(removeN).remove(removeM);
      answer++;
    }
  }

  private boolean isAllEmptyQueue() {
    for (int i = 0; i < removeQueueList.size(); i++) {
      if (!removeQueueList.get(i).isEmpty()) return false;
    }
    return true;
  }
}
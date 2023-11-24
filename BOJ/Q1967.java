import java.io.*;
import java.util.*;

public class Q1967 {
  static int n;
  static Map<Integer, Node> nodeMap = new HashMap<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    nodeMap.put(1, new Node(1));

    for (int i = 0; i < n-1; i++) {
      String[] tmp = br.readLine().split(" ");
      int a = Integer.parseInt(tmp[0]);
      int b = Integer.parseInt(tmp[1]);
      int e = Integer.parseInt(tmp[2]);

      Node parent = nodeMap.get(a);
      if (parent == null) {
        parent = new Node(a);
        nodeMap.put(a, parent);
      }
      Node child = null;
      if (nodeMap.containsKey(b)) {
        child = nodeMap.get(b);
      } else {
        child = new Node(b);
        nodeMap.put(b, child);
      }

      parent.childList.add(child);
      parent.childDistList.add(e);
    }

    find(nodeMap.get(1));
    int answer = getMaxSum();

    //print answer
    System.out.println(answer);
  }

  private static void find(Node current) {
    if (current == null) return;

    for (int i = 0; i < current.childList.size(); i++) {
      find(current.childList.get(i));
    }

    int firstMax = 0;
    int firstMaxNum = 0;
    int secondMax = 0;
    for (int i = 0; i < current.childList.size(); i++) {
      current.selectOneMaxSum = Math.max(current.selectOneMaxSum,
          current.childList.get(i).selectOneMaxSum + current.childDistList.get(i));
    }
    for (int i = 0; i < current.childList.size(); i++) {
      if (firstMax < current.childList.get(i).selectOneMaxSum + current.childDistList.get(i)) {
        firstMax = current.childList.get(i).selectOneMaxSum + current.childDistList.get(i);
        firstMaxNum = i;
      }
    }
    for (int i = 0; i < current.childList.size(); i++) {
      if (firstMaxNum == i) continue;
      if (secondMax < current.childList.get(i).selectOneMaxSum + current.childDistList.get(i)) {
        secondMax = current.childList.get(i).selectOneMaxSum + current.childDistList.get(i);
      }
    }

    current.selectOneMaxSum = firstMax;
    current.selectBothMaxSum = firstMax + secondMax;
  }

  private static int getMaxSum() {
    int result = 0;

    for (int key : nodeMap.keySet()) {
      result = Math.max(result, nodeMap.get(key).selectOneMaxSum);
      result = Math.max(result, nodeMap.get(key).selectBothMaxSum);
    }

    return result;
  }

  static class Node {
    public int num;
    public List<Node> childList = new ArrayList<>();
    public List<Integer> childDistList = new ArrayList<>();
    public int selectOneMaxSum = 0;
    public int selectBothMaxSum = 0;

    public Node(int num) {
      this.num = num;
    }
  }
}
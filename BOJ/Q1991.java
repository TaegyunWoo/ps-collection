import java.io.*;
import java.util.*;

public class Q1991 {
  static int n;
  static Node root;
  static Map<Character, Node> nodeMap = new HashMap<>();
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    for (char c = 'A'; c < n + 'A'; c++) {
      nodeMap.put(c, new Node(c));
    }
    root = nodeMap.get('A');
    for (int i = 0; i < n; i++) {
      String[] tmp = br.readLine().split(" ");
      char parentC = tmp[0].charAt(0);
      char leftC = tmp[1].charAt(0);
      char rightC = tmp[2].charAt(0);
      Node parent = nodeMap.get(parentC);
      if (leftC != '.') {
        parent.left = nodeMap.get(leftC);
      }
      if (rightC != '.') {
        parent.right = nodeMap.get(rightC);
      }
    }

    //solution
    preOrder(root);
    answer.append("\n");
    inOrder(root);
    answer.append("\n");
    postOrder(root);

    //print answer
    System.out.println(answer.toString());
  }

  private static void preOrder(Node currentNode) {
    if (currentNode == null) return;

    answer.append(currentNode.c);
    preOrder(currentNode.left);
    preOrder(currentNode.right);
  }

  private static void inOrder(Node currentNode) {
    if (currentNode == null) return;

    inOrder(currentNode.left);
    answer.append(currentNode.c);
    inOrder(currentNode.right);
  }

  private static void postOrder(Node currentNode) {
    if (currentNode == null) return;

    postOrder(currentNode.left);
    postOrder(currentNode.right);
    answer.append(currentNode.c);
  }

  static class Node {
    public char c;
    public Node left = null;
    public Node right = null;
    public Node(char c) {
      this.c = c;
    }
  }
}
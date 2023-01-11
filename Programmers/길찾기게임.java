import java.util.*;

class Solution {
  int[] preorderResult;
  int[] postorderResult;
  int idx = 0;

  public int[][] solution(int[][] nodeinfo) {
    Node[] nodeAry = new Node[nodeinfo.length];
    for (int i = 0; i < nodeinfo.length; i++) {
      nodeAry[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null);
    }

    Arrays.sort(nodeAry, (n1, n2) -> {
      if (n1.y > n2.y) return -1;
      if (n1.y == n2.y) {
        if (n1.x < n2.x) return -1;
        return 1;
      }
      return 1;
    });

    Node root = nodeAry[0];
    for (int i = 1; i < nodeAry.length; i++) {
      insertNode(root, nodeAry[i]);
    }

    preorderResult = new int[nodeAry.length];
    idx = 0;
    preorder(root);

    postorderResult = new int[nodeAry.length];
    idx = 0;
    postorder(root);

    return new int[][] {preorderResult, postorderResult};
  }

  //전위
  private void preorder(Node node) {
    if (node != null) {
      preorderResult[idx++] = node.index;
      preorder(node.left);
      preorder(node.right);
    }
  }

  //후위
  private void postorder(Node node) {
    if (node != null) {
      postorder(node.left);
      postorder(node.right);
      postorderResult[idx++] = node.index;
    }
  }

  //자식노드 삽입
  private void insertNode(Node parent, Node child) {
    if (child.x < parent.x) {
      if (parent.left == null) parent.left = child;
      else insertNode(parent.left, child);
    } else {
      if (parent.right == null) parent.right = child;
      else insertNode(parent.right, child);
    }
  }

  class Node {
    public int index;
    public int x;
    public int y;
    public Node left;
    public Node right;
    public Node(int index, int x, int y, Node left, Node right) {
      this.index = index;
      this.x = x;
      this.y = y;
      this.left = left;
      this.right = right;
    }
  }
}
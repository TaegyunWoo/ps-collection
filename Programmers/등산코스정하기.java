import java.util.*;

class Solution {
  static final int INF = (int) 1e9;
  List<List<Node>> graph = new ArrayList<>();
  int[] intensityTable;
  int answerIntensity = INF;
  int answerSummit = INF;
  HashSet<Integer> gateSet = new HashSet<>();
  HashSet<Integer> summitSet = new HashSet<>();

  public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
    initGraph(n, paths);
    initGateSet(gates);
    initSummitSet(summits);
    initIntensityTable(n);

    dij(gates);
    updateAnswer(summits);

    return new int[] {answerSummit, answerIntensity};
  }

  private void initGateSet(int[] gates) {
    for (int g : gates) {
      gateSet.add(g);
    }
  }

  private void initSummitSet(int[] summits) {
    for (int s : summits) {
      summitSet.add(s);
    }
  }

  private void initIntensityTable(int n) {
    intensityTable = new int[n+1];
    Arrays.fill(intensityTable, INF);
  }

  private void initGraph(int n, int[][] paths) {
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] info : paths) {
      int a = info[0];
      int b = info[1];
      int d = info[2];
      graph.get(a).add(new Node(b, d, false));
      graph.get(b).add(new Node(a, d, false));
    }
  }

  private void updateAnswer(int[] summits) {
    for (int summitIdx : summits) {
      if (answerIntensity > intensityTable[summitIdx]) {
        answerIntensity = intensityTable[summitIdx];
        answerSummit = summitIdx;
      } else if (answerIntensity == intensityTable[summitIdx]) {
        answerSummit = Math.min(answerSummit, summitIdx);
      }
    }
  }

  private void dij(int[] gates) {
    PriorityQueue<Node> queue = new PriorityQueue<>();

    //모든 게이트에서 시작
    for (int gate : gates) {
      intensityTable[gate] = 0;
      queue.offer(new Node(gate, 0, false));
    }

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      int currentIndex = current.index;
      int intensityOfStartToCurrent = current.intensity; //현재 노드까지의 intensity 값

      if (current.reachedSummit) continue; //이미 산봉우리를 방문한 경우
      if (intensityTable[currentIndex] < intensityOfStartToCurrent) continue; //확인할 필요가 없는 경우

      for (Node nextNode : graph.get(currentIndex)) {
        int nextIndex = nextNode.index;
        int intensityOfStartToNext = Math.max(intensityOfStartToCurrent, nextNode.intensity); //다음 노드까지의 intensity값

        if (gateSet.contains(nextIndex)) continue; //다른 출입구를 경유하는 경우
        if (intensityTable[nextIndex] <= intensityOfStartToNext) continue; //다음까지의 intensity가 더 큰 경우

        intensityTable[nextIndex] = intensityOfStartToNext; //nextIndex까지의 intensity 업데이트

        Node updateInfo;
        if (summitSet.contains(nextIndex)) { //만약 nextIndex가 산봉우리라면
          updateInfo = new Node(nextIndex, intensityOfStartToNext, true);
        } else { //만약 nextIndex가 산봉우리가 아니라면
          updateInfo = new Node(nextIndex, intensityOfStartToNext, false);
        }

        queue.offer(updateInfo); //업데이트 정보 enqueue
      }
    }
  }

  class Node implements Comparable<Node> {
    public int index;
    public int intensity;
    public boolean reachedSummit;
    public Node(int index, int intensity, boolean reachedSummit) {
      this.index = index;
      this.intensity = intensity;
      this.reachedSummit = reachedSummit;
    }

    @Override
    public int compareTo(Node other) {
      if (this.intensity < other.intensity) return -1;
      return 1;
    }
  }
}
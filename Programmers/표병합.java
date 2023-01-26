import java.util.*;

class Solution {
  Cell[] table = new Cell[2500];
  List<String> answerList = new ArrayList<>();

  public String[] solution(String[] commands) {
    initTable();

    for (int i = 0; i < commands.length; i++) {
      String[] command = commands[i].split(" ");

      if (command[0].equals("UPDATE") && command.length == 4) {
        int index = rcToIndex(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        String value = command[3];
        update(index, value);

      } else if (command[0].equals("UPDATE") && command.length == 3) {
        update(command[1], command[2]);

      } else if (command[0].equals("MERGE")) {
        int indexA = rcToIndex(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        int indexB = rcToIndex(Integer.parseInt(command[3]), Integer.parseInt(command[4]));
        merge(indexA, indexB);

      } else if (command[0].equals("UNMERGE")) {
        int index = rcToIndex(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        unmerge(index);

      } else if (command[0].equals("PRINT")) {
        int index = rcToIndex(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        print(index);
      }
    }

    String[] answer = new String[answerList.size()];
    for (int i = 0; i < answer.length; i++) {
      answer[i] = answerList.get(i);
    }

    return answer;
  }

  private void update(int index, String value) {
    int rootIndex = findRoot(index);
    table[rootIndex].value = value;
  }

  private void update(String value1, String value2) {
    for (int i = 0; i < table.length; i++) {
      if (value1.equals(table[i].value)) table[i].value = value2;
    }
  }

  private void merge(int indexA, int indexB) {
    int rootOfA = findRoot(indexA);
    int rootOfB = findRoot(indexB);

    if (rootOfA < rootOfB) {
      table[rootOfB].parent = rootOfA; //더 작은 index로 합치기
      if (table[rootOfA].value == null && table[rootOfB].value != null) { //indexA의 값만 null이라면
        table[rootOfA].value = table[rootOfB].value;
      }

    } else if (rootOfA > rootOfB) {
      table[rootOfA].parent = rootOfB; //더 작은 index로 합치기
      if (table[rootOfA].value != null) { //indexA의 값이 null이 아니라면
        table[rootOfB].value = table[rootOfA].value;
      }
    }
  }

  private void unmerge(int index) {
    HashSet<Integer> relatedParentIndexSet = new HashSet<>(); //`unmerge할 cell` 과 연관된 모든 부모 index를 저장하는 Set
    int originalIndex = index;
    String originalValue = table[findRoot(originalIndex)].value;

    //'unmerge할 cell'의 모든 부모 cell 구하기
    while (true) {
      int parentIndex = table[index].parent;
      relatedParentIndexSet.add(parentIndex);

      if (index == parentIndex) break;
      index = parentIndex;
    }

    //모든 cell을 살펴보면서, "'unmerge할 cell'의 부모 cell과 연관된 cell"들의 부모 cell 추가
    for (int i = 0; i < table.length; i++) {
      HashSet<Integer> tmpSet = new HashSet<>();
      boolean flag = false;
      int idx = i;
      while (true) {
        if (relatedParentIndexSet.contains(table[idx].parent)) flag = true;
        tmpSet.add(table[idx].parent);
        if (idx == table[idx].parent) break;
        idx = table[idx].parent;
      }
      if (flag) relatedParentIndexSet.addAll(tmpSet);
      flag = false;
    }

    //`unmerge할 cell` 과 연관된 모든 cell들을 초기화
    for (int i = 0; i < table.length; i++) {
      int parentIndex = table[i].parent;
      if (relatedParentIndexSet.contains(parentIndex)) {
        table[i].parent = i;
        table[i].value = null;
      }
    }

    //`unmerge할 cell` 은 unmerge 직전의 값을 가지고 있으므로
    table[originalIndex].value = originalValue;
  }

  private void print(int index) {
    int rootIndex = findRoot(index);
    if (table[rootIndex].value == null) answerList.add("EMPTY");
    else answerList.add(table[rootIndex].value);
  }

  private void initTable() {
    for (int i = 0; i < table.length; i++) {
      table[i] = new Cell(i, null);
    }
  }

  private int findRoot(int index) {
    if (table[index].parent == index) return index;
    return findRoot(table[index].parent);
  }

  private int rcToIndex(int r, int c) {
    return (r-1) * 50 + (c - 1);
  }

  class Cell {
    public int parent;
    public String value;
    public Cell(int parent, String value) {
      this.parent = parent;
      this.value = value;
    }
  }
}
import java.util.*;

class Solution {
  Cell[] cellAry;
  int currentCursor;
  Stack<Integer> removeHistStack = new Stack<>();

  public String solution(int n, int k, String[] cmd) {
    //초기화
    initCellAry(n);
    currentCursor = k;

    //명령어 처리
    for (String order : cmd) {
      String[] parsed = order.split(" ");

      switch (parsed[0]) {
        case "D" :
          move(Integer.parseInt(parsed[1]));
          break;
        case "U" :
          move(-Integer.parseInt(parsed[1]));
          break;
        case "C" :
          removeCurrentCursor();
          break;
        case "Z" :
          rollback();
          break;
      }
    }

    //정답 출력
    StringBuilder answer = new StringBuilder();
    for (Cell cell : cellAry) {
      if (cell.isRemoved) answer.append("X");
      else answer.append("O");
    }

    return answer.toString();
  }

  private void initCellAry(int n) {
    cellAry = new Cell[n];
    for (int i = 0; i < n; i++) {
      cellAry[i] = new Cell(i-1, i+1);
    }
  }

  private void move(int step) {
    int limit = Math.abs(step);
    boolean goUp = step < 0;

    if (goUp) { //위로 이동
      for (int i = 0; i < limit; i++) {
        currentCursor = cellAry[currentCursor].frontIndex;
      }
    } else { //아래로 이동
      for (int i = 0; i < limit; i++) {
        currentCursor = cellAry[currentCursor].rearIndex;
      }
    }
  }

  private void removeCurrentCursor() {
    int front = cellAry[currentCursor].frontIndex;
    int rear = cellAry[currentCursor].rearIndex;

    cellAry[currentCursor].isRemoved = true; //제거처리
    removeHistStack.push(currentCursor); //삭제 기록

    if (cellAry[currentCursor].rearIndex == cellAry.length) { //맨 뒤 셀을 지운 것이라면
      cellAry[front].rearIndex = rear; //앞 셀의 rear 포인터 업데이트
      move(-1); //위로 한칸 이동

    } else if (cellAry[currentCursor].frontIndex == -1) { //맨 앞 셀을 지운 것이라면
      cellAry[rear].frontIndex = front; //뒤 셀의 front 포인터 업데이트
      move(1); //아래로 한칸 이동

    } else { //맨 앞·뒤 셀을 지운 것이 아니라면
      cellAry[front].rearIndex = rear; //앞 셀의 rear 포인터 업데이트
      cellAry[rear].frontIndex = front; //뒤 셀의 front 포인터 업데이트
      move(1); //아래로 한칸 이동
    }
  }

  private void rollback() {
    int target = removeHistStack.pop();
    int front = cellAry[target].frontIndex;
    int rear = cellAry[target].rearIndex;

    cellAry[target].isRemoved = false;
    if (front != -1) cellAry[front].rearIndex = target;
    if (rear != cellAry.length) cellAry[rear].frontIndex = target;
  }

  class Cell {
    public boolean isRemoved = false;
    public int frontIndex;
    public int rearIndex;

    public Cell(int frontIndex, int rearIndex) {
      this.frontIndex = frontIndex;
      this.rearIndex = rearIndex;
    }
  }
}
import java.util.*;

class Solution {
  PriorityQueue<File> queue = new PriorityQueue<>();
  List<String> answer = new ArrayList<>();

  public String[] solution(String[] files) {
    for (int i = 0; i < files.length; i++) {
      File file = parse(files[i], i);
      queue.offer(file);
    }

    while (!queue.isEmpty()) {
      answer.add(queue.poll().name);
    }

    return answer.stream().toArray(String[]::new);
  }

  private File parse(String name, int index) {
    StringBuilder head = new StringBuilder();
    StringBuilder number = new StringBuilder();
    StringBuilder tail = new StringBuilder();

    boolean isHead = true;
    boolean isNum = false;
    boolean isTail = false;
    for (int i = 0; i < name.length(); i++) {
      char currentChar = name.charAt(i);

      if (isHead && currentChar >= '0' && currentChar <= '9') {
        isNum = true;
        isHead = false;
        isTail = false;
      } else if (isNum && (currentChar < '0' || currentChar > '9')) {
        isTail = true;
        isHead = false;
        isNum = false;
      }

      if (number.length() > 5) {
        isTail = true;
        isHead = false;
        isNum = false;
      }

      if (isHead) {
        head.append(currentChar);
      } else if (isNum) {
        number.append(currentChar);
      } else {
        tail.append(currentChar);
      }
    }

    return new File(name, head.toString(), number.toString(), tail.toString(), index);
  }

  class File implements Comparable<File> {
    public String name;
    public String head;
    public String number;
    public String tail;
    public int index;

    public File(String name, String head, String number, String tail, int index) {
      this.name = name;
      this.head = head;
      this.number = number;
      this.tail = tail;
      this.index = index;
    }

    @Override
    public int compareTo(File other) {
      int headCompareResult = this.head.toUpperCase().compareTo(other.head.toUpperCase());
      int numberCompareResult = Integer.valueOf(this.number).compareTo(Integer.valueOf(other.number));

      if (headCompareResult < 0) {
        return -1;
      } else if (headCompareResult == 0) {
        if (numberCompareResult < 0) {
          return -1;
        } else if (numberCompareResult == 0) {
          if (this.index < other.index) {
            return -1;
          }
          return 1;
        }
        return 1;
      }
      return 1;
    }
  }
}
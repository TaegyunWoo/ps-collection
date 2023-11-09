import java.io.*;

public class Q9527 {
  static long a;
  static long b;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    a = Long.parseLong(tmp[0]);
    b = Long.parseLong(tmp[1]);

    //solution
    System.out.println(countOneFrom0ToNum(b) - countOneFrom0ToNum(a-1));

  }

  public static long countOneFrom0ToNum(long num) {
    int length = 0;
    for (long i = num; i > 0; i/=2) {
      length++;
    }

    long result = 0;
    long repeatGap = 1;
    for (int i = 0; i < length; i++) {
      result += (num + 1) / repeatGap / 2 * repeatGap;
      if (((num + 1) / repeatGap) % 2 == 1) {
        result += (num + 1) % repeatGap;
      }
      repeatGap *= 2;
    }

    return result;
  }
}
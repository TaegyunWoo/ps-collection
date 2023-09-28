import java.io.*;
import java.util.Arrays;

public class Q2138 {
  static final int MAX = (int) 1e9;
  static int n;
  static char[] lights;
  static char[] target;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    lights = new char[n];
    String tmp = br.readLine();
    for (int i = 0; i < n; i++) {
      lights[i] = tmp.charAt(i);
    }
    tmp = br.readLine();
    target = new char[n];
    for (int i = 0; i < n; i++) {
      target[i] = tmp.charAt(i);
    }

    //solution
    char[] ary = new char[n];

    //첫스위치를 누르지 않고 수행
    for (int i = 0; i < n; i++) ary[i] = lights[i];
    int result1 = greedy(ary, 0);

    //첫스위치를 누르고 수행
    for (int i = 0; i < n; i++) ary[i] = lights[i];
    onOrOff(ary, 0);
    int result2 = greedy(ary, 1);

    //print answer
    int answer = Math.min(result1, result2);
    if (answer == MAX) System.out.println(-1);
    else System.out.println(answer);
  }

  private static int greedy(char[] array, int count) {
    for (int i = 1; i < n; i++) {
      if (array[i-1] != target[i-1]) {
        onOrOff(array, i);
        count++;
      }
    }

    for (int i = 0; i < n; i++) {
      if (array[i] != target[i]) return MAX;
    }
    return count;
  }

  private static void onOrOff(char[] array, int idx) {
    array[idx] = (array[idx] == '0') ? '1' : '0';
    if (idx != 0) array[idx-1] = (array[idx-1] == '0') ? '1' : '0';
    if (idx != n-1) array[idx+1] = (array[idx+1] == '0') ? '1' : '0';
  }
}

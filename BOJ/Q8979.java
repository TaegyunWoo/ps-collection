import java.io.*;
import java.util.*;

public class Q8979 {
  public static int n;
  public static int k;
  public static List<Info> infoList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    //Input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      infoList.add(new Info(Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken())));
    }

    br.close();

    //정렬
    infoList.sort((a, b) -> {
      if (a.gold < b.gold) {
        return 1;
      } else if (a.gold > b.gold) {
        return -1;
      } else {
        if (a.silver < b.silver) {
          return 1;
        } else if (a.silver > b.silver) {
          return -1;
        } else {
          if (a.copper < b.copper) {
            return 1;
          } else if (a.copper > b.copper) {
            return -1;
          } else {
            return 0;
          }
        }
      }
    });

    //등수 계산
    if (infoList.get(0).index == k) {
      System.out.println(1);
      return;
    }

    int answer = 1;
    for (int i = 1; i < infoList.size(); i++) {
      Info currentInfo = infoList.get(i);
      Info beforeInfo = infoList.get(i-1);

      if (currentInfo.gold != beforeInfo.gold
          || currentInfo.silver != beforeInfo.silver
          || currentInfo.copper != beforeInfo.copper) {
        answer = i + 1;
      }

      if (currentInfo.index == k) break;
    }

    System.out.println(answer);
  }

  public static class Info {
    public int index;
    public int gold;
    public int silver;
    public int copper;

    public Info(int i, int g, int s, int c) {
      this.index = i;
      this.gold = g;
      this.silver = s;
      this.copper = c;
    }
  }
}

import java.util.*;

class Solution {
  HashMap<String, List<Msg>> messageMap = new HashMap<>();
  ArrayList<Msg> allMessageList = new ArrayList<>();

  public String[] solution(String[] record) {
    for (int i = 0; i < record.length; i++) {
      String[] line = record[i].split(" ");
      String order = line[0];
      String uid = line[1];

      if (order.equals("Enter")) {
        String nickname = line[2];
        changeMessage(uid, nickname);
        addEnterMessage(uid, nickname);

      } else if (order.equals("Change")) {
        String nickname = line[2];
        changeMessage(uid, nickname);

      } else {
        addLeaveMessage(uid);
      }
    }

    String[] result = new String[allMessageList.size()];
    for (int i = 0; i < allMessageList.size(); i++) {
      result[i] = allMessageList.get(i).msg;
    }
    return result;
  }

  private void changeMessage(String uid, String newNickname) {
    List<Msg> relatedMessageList = messageMap.get(uid);
    if (relatedMessageList == null) return;
    for (int i = 0; i < relatedMessageList.size(); i++) {
      Msg msgObj = relatedMessageList.get(i);
      String oldNickname = msgObj.msg.substring(0, msgObj.msg.indexOf("님"));
      msgObj.setMsg(msgObj.msg.replaceFirst(oldNickname, newNickname));
    }
  }

  private void addEnterMessage(String uid, String nickname) {
    String msg = nickname + "님이 들어왔습니다.";
    Msg msgObj = new Msg(msg);
    if (messageMap.containsKey(uid)) {
      messageMap.get(uid).add(msgObj);
    } else {
      ArrayList<Msg> list = new ArrayList<>();
      list.add(msgObj);
      messageMap.put(uid, list);
    }
    allMessageList.add(msgObj);
  }

  private void addLeaveMessage(String uid) {
    Msg msgObj = messageMap.get(uid).get(0);
    String nickname = msgObj.msg.substring(0, msgObj.msg.indexOf("님"));
    String msg = nickname + "님이 나갔습니다.";

    Msg newMsg = new Msg(msg);
    messageMap.get(uid).add(newMsg);
    allMessageList.add(newMsg);
  }

  class Msg {
    public String msg;
    public Msg(String msg) {
      this.msg = msg;
    }
    public void setMsg(String msg) {
      this.msg = msg;
    }
  }
}
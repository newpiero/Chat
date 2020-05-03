package Server.Core;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogsChats {
    private static File fileLogServer = new File("logServer.txt");
    private static File fileLogChat = new File("logChat.txt");




    public static void saveMsgServerLogFile(String msgLogs) {

        try (FileWriter outServer = new FileWriter(fileLogServer, true)) {
            outServer.write(msgLogs + "\n");
            outServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveMsgChatLogFile(String msg) {
        try (FileWriter outChat = new FileWriter(fileLogChat, true)) {
            outChat.write(msg + "\n");
            outChat.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static List<String> loadMsgChatLogFileEnd100() {

        List<String> list = new MyArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileLogChat))) {

            String str;
            while ((str = br.readLine()) != null) {
                list.add(str + "\n");
            }
            while (list.size() > 100) {
                list.remove(0);
            }
//
//            long startIndex = fileLogChat.length();
//
//            for (int i = 0; i < str.length; i++) {
//                raf.seek(startIndex);
//                raf.readLine();
//                str[i] = raf.readLine() + "\n";
//
//                startIndex-=(int)str[i].chars().count();
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static class MyArrayList<S> extends ArrayList {
        private static final long serialVersionUID = 1L;

        @Override
        public String toString() {
            String result = "+";
            for (int i = 0; i < this.size(); i++) {
                result += " " + this.get(i);
            }
            return result;
        }
    }


}

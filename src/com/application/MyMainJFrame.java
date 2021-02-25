package com.application;

import com.application.receiver.JFrameReceiver;
import com.application.sender.MyJFrame;

public class MyMainJFrame {
   public static MyJFrame sender1;
   public static MyJFrame sender2;
   public static JFrameReceiver receiver;
    public static void main(String[] args) {
        sender1=new MyJFrame("sender1","test1");
        sender2=new MyJFrame("sender2","test2");
        try{
            receiver=new JFrameReceiver("Receiver");
        }catch(Exception e){e.printStackTrace();}
    }
}

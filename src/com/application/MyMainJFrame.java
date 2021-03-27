package com.application;

import com.application.sender.MyJFrame;
import com.application.sender_receiver.MyFrame;
import com.application.receiver.JFrameReceiver;



public class MyMainJFrame {

   public static MyJFrame sender1;
   public static MyJFrame sender2;
   public static MyJFrame sender3;
   public static JFrameReceiver receiver;
    public static void main(String[] args) {
        collaborativeEditor();
    }

    private static void twoSenderAndReceiver(){
        sender1=new MyJFrame("sender1","test1");
        sender2=new MyJFrame("sender2","test2");
        try{
            receiver=new JFrameReceiver("Receiver");
        }catch(Exception e){e.printStackTrace();}
    }

    private static void senderReceiver(){
        com.application.sender_receiver.MyFrame sender_receiver1;
        com.application.sender_receiver.MyFrame sender_receiver2;
        com.application.sender_receiver.MyFrame sender_receiver3;
        com.application.sender_receiver.MyFrame sender_receiver4;
        sender_receiver1=new MyFrame("sender1");
        sender_receiver2=new MyFrame("sender2");
        sender_receiver3=new MyFrame("sender3");
        sender_receiver4=new MyFrame("sender4");
        com.application.sender_receiver.MyFrame.start();
    }

    private static void collaborativeEditor(){
        MyFrame sender_receiver1;
        MyFrame sender_receiver2;
        MyFrame sender_receiver3;
        MyFrame sender_receiver4;
        sender_receiver1=new MyFrame("sender1");
        sender_receiver2=new MyFrame("sender2");
        sender_receiver3=new MyFrame("sender3");
        sender_receiver4=new MyFrame("sender4");
        MyFrame.start();
    }


}

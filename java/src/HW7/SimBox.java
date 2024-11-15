package HW7;
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 7 Problem 3
   First, study how this class should work with the test code in SimMain.java
   carefully!

   Student Name: Tiernan Lindauer
   Student UIN: 134003853
   Acknowledgements:
*/

import java.util.*;

class SimBox implements Runnable {
    static final int MAX_SIZE = 10; // max size of message queue

    class Message {
        String sender; // sender of the message
        String recipient; // recipient of the message
        String msg; // content of the message

        Message(String sender, String recipient, String msg) {
            this.sender = sender; // init sender
            this.recipient = recipient; // init recipient
            this.msg = msg; // init msg
        }
    }

    private final LinkedList<Message> messages; // shared message queue
    private LinkedList<Message> myMessages; // private message queue
    private String myId; // id of this SimBox
    private boolean stop = false; // flag to stop the thread

    public SimBox(String myId) {
        messages = new LinkedList<Message>(); // init shared queue
        this.myId = myId; // init id
        this.myMessages = new LinkedList<Message>(); // init private queue
        new Thread(this).start(); // start thread
    }

    public SimBox(String myId, SimBox s) {
        this.messages = s.messages; // share message queue
        this.myId = myId; // init id
        this.myMessages = new LinkedList<Message>(); // init private queue
        new Thread(this).start(); // start thread
    }

    public String getId() {
        return myId; // return id
    }

    public void stop() {
        stop = true; // set stop flag
    }

    public void send(String recipient, String msg) {
        synchronized (messages) {
            messages.add(new Message(myId, recipient, msg)); // add msg to shared queue
        }
    }

    public List<String> retrieve() {
        List<String> result = new ArrayList<>();
        synchronized (myMessages) {
            for (Message m : myMessages) {
                result.add("From " + m.sender + " to " + m.recipient + " " + m.msg); // format msg
            }
            myMessages.clear(); // clear private queue
        }
        return result; // return formatted msgs
    }

    public void run() {
        for (; ; ) { // loop forever
            synchronized (messages) {
                synchronized (myMessages) {
                    Iterator<Message> iter = messages.iterator();
                    while (iter.hasNext()) {
                        Message m = iter.next();
                        if (m.recipient.equals(myId)) {
                            iter.remove(); // remove from shared queue
                            myMessages.add(m); // add to private queue
                        }
                    }
                }
                while (messages.size() > MAX_SIZE) {
                    messages.removeFirst(); // remove oldest msgs
                }
            }
            if (stop) return; // stop if flag is set
            try {
                Thread.sleep(1000); // sleep for 1 sec
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // handle interrupt
            }
        }
    }
}
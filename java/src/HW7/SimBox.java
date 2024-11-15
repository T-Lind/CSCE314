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
    static final int MAX_SIZE = 10;

    class Message {
        String sender;
        String recipient;
        String msg;

        Message(String sender, String recipient, String msg) {
            this.sender = sender;
            this.recipient = recipient;
            this.msg = msg;
        }
    }

    private final LinkedList<Message> messages;
    private LinkedList<Message> myMessages;
    private String myId;
    private boolean stop = false;

    public SimBox(String myId) {
        messages = new LinkedList<Message>();
        this.myId = myId;
        this.myMessages = new LinkedList<Message>();
        new Thread(this).start();
    }

    public SimBox(String myId, SimBox s) {
        this.messages = s.messages;
        this.myId = myId;
        this.myMessages = new LinkedList<Message>();
        new Thread(this).start();
    }

    public String getId() {
        return myId;
    }

    public void stop() {
        // make it so that this Runnable will stop
        stop = true;
    }

    public void send(String recipient, String msg) {
        // add a message to the shared message queue (messages)
        // you will have to synchronize the message queue
        synchronized (messages) {
            messages.add(new Message(myId, recipient, msg));
        }
    }

    public List<String> retrieve() {
        // return the contents of myMessages
        // and empty myMessages
        // you will have to synchronize myMessages
        // each message should be in the following format:
        //   From (the sender) to (the recipient) (actual message)
        List<String> result = new ArrayList<>();
        synchronized (myMessages) {
            for (Message m : myMessages) {
                result.add("From " + m.sender + " to " + m.recipient + " " + m.msg);
            }
            myMessages.clear();
        }
        return result;
    }

    public void run() {
        // loop forever
        // 1. Approximately once every second move all messages
        //    addressed to this mailbox from the shared message queue
        //    to the private myMessages queue
        //    To do so, you need to synchronize messages and myMessages.
        //    Furthermore, you need to explicitly use the iterator() of messages
        //    with a while loop.  A for-each loop will not work here.
        // 2. Also approximately once every second, if the message
        //    queue has more than MAX_SIZE messages, delete oldest messages
        //    so that size is at most MAX_SIZE. This part of code is provided
        //    below.

        for (; ; ) { // loop forever
            synchronized (messages) {
                synchronized (myMessages) {
                    Iterator<Message> iter = messages.iterator();
                    while (iter.hasNext()) {
                        Message m = iter.next();
                        if (m.recipient.equals(myId)) {
                            iter.remove();
                            myMessages.add(m);
                        }
                    }
                }
                while (messages.size() > MAX_SIZE) {
                    messages.removeFirst();
                }
            }
            if (stop) return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } // endfor
    } // end run()
} // end SimBox
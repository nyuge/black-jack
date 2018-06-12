package common;

// Built-in modules
import java.util.LinkedList;
import java.util.List;


public class EventList {
    private List<String> messageList;

    public EventList() {
        this.messageList = new LinkedList<>();
    }

    public void addMessage(String message) {
        this.messageList.add(message);
    }

    public boolean isEmpty() {
        return this.messageList.isEmpty();
    }

    public void showMessages() {
        for (String message : this.messageList) {
            System.out.println(message);
        }
    }

}
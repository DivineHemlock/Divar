package DB;

import java.util.ArrayList;

public class Chat
{
    public static int counter = 0;
    private String userAUsername;
    private String userBUsername;
    private String ID;
    private ArrayList<Message> messages = new ArrayList<>();

    public Chat(String user_a, String user_b) {
        this.userAUsername = user_a;
        this.userBUsername = user_b;
        this.ID = String.valueOf(Chat.counter);
        this.messages = new ArrayList<>();
        Chat.counter += 1;
    }

    public void addMessage (Message message)
    {
        this.messages.add(message);
    }

    public String getUserAUsername() {
        return userAUsername;
    }

    public void setUserAUsername(String userAUsername) {
        this.userAUsername = userAUsername;
    }

    public String getUserBUsername() {
        return userBUsername;
    }

    public void setUserBUsername(String userBUsername) {
        this.userBUsername = userBUsername;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}

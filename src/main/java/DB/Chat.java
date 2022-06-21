package DB;

import java.util.ArrayList;

public class Chat
{
    private User user_a;
    private User user_b;
    private ArrayList<Message> messages = new ArrayList<>();

    public Chat(User user_a, User user_b) {
        this.user_a = user_a;
        this.user_b = user_b;
        this.messages = new ArrayList<>();
    }

    public void addMessage (Message message)
    {
        this.messages.add(message);
    }

    public User getUser_a() {
        return user_a;
    }

    public void setUser_a(User user_a) {
        this.user_a = user_a;
    }

    public User getUser_b() {
        return user_b;
    }

    public void setUser_b(User user_b) {
        this.user_b = user_b;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}

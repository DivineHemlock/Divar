import java.io.Serializable;

public class Message implements Serializable {
    private String receiver;
    private String sender;

    public Message(String receiver, String sender) {
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

package DB;

public class Message
{
    private String text;
    private String senderUsername;
    private String receiverUsername;

    public Message(String text, String sender , String receiver) {
        this.text = text;
        this.senderUsername = sender;
        this.receiverUsername = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}

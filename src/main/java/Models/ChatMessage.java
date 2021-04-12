package Models;

import java.time.LocalDateTime;

public class ChatMessage implements Memo{

    private String text;
    private LocalDateTime time;
    private String chatroomID;
    private boolean isSeen;
    private Owner owner;

    public MemoType memoType;

    public enum Owner {
        one,
        two
    }

    public ChatMessage(String content, LocalDateTime time, String chatroomID, Owner owner) {
        this.text = content;
        this.time = time;
        this.chatroomID = chatroomID;
        this.isSeen = false;
        this.owner = owner;
        this.memoType = MemoType.chatmsg;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    @Override
    public MemoType getMemoType() {
        return MemoType.chatmsg;
    }

    @Override
    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getTimeString(){
        return getTime().toString();
    }

    public Owner getOwner() {
        return owner;
    }
}

package Models;

import java.time.LocalDateTime;

public class ChatMessage implements Memo{

    private String content;
    private LocalDateTime time;
    private String chatroomID;
    private boolean isSeen;
    private Owner owner;

    public MemoType memoType;

    enum Owner {
        one,
        two
    }

    public ChatMessage(String content, LocalDateTime time, String chatroomID, Owner owner) {
        this.content = content;
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
}

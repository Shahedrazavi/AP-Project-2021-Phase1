package Models;

public interface Memo {


    public enum MemoType {
        tweet,
        message,
        chatmsg
    }

    public MemoType getMemoType();

    public String getText();
}

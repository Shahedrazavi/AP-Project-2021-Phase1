package Models;

public class Message implements Memo {

    private String text;
    private MemoType memoType;

    public Message(String text) {
        this.text = text;
        this.memoType = MemoType.message;
    }

    @Override
    public MemoType getMemoType() {
        return MemoType.message;
    }

    @Override
    public String getText() {
        return text;
    }
}

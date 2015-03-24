package task.markup.core;

/**
 * Created by user on 23.03.15.
 */
public class Lexema {

    private TokenType type;
    private int openPositionIndex;
    private int sequenceLength;
    private boolean needCloseTag;


    public Lexema() {
    }

    public Lexema(TokenType type, int openPositionIndex, int sequenceLength, boolean needCloseTag) {
        this.type = type;
        this.openPositionIndex = openPositionIndex;
        this.sequenceLength = sequenceLength;
        this.needCloseTag = needCloseTag;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public int getOpenPositionIndex() {
        return openPositionIndex;
    }

    public void setOpenPositionIndex(int openPositionIndex) {
        this.openPositionIndex = openPositionIndex;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    public void setSequenceLength(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }


    public boolean isNeedCloseTag() {
        return needCloseTag;
    }

    public void setNeedCloseTag(boolean needCloseTag) {
        this.needCloseTag = needCloseTag;
    }

}

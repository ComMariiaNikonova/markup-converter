package task.markup.core;

/**
 * Created by user on 23.03.15.
 */
public class Lexema {

    private TokenType type;
    private int openPositionIndex;
    private int closePositionIndex;
    private int sequenceLength;

    private boolean needCloseTag;
    private TokenType closeTagType;

    public Lexema() {
    }

    public Lexema(TokenType type, int openPositionIndex, int closePositionIndex, int sequenceLength, boolean needCloseTag, TokenType closeTagType) {
        this.type = type;
        this.openPositionIndex = openPositionIndex;
        this.closePositionIndex = closePositionIndex;
        this.sequenceLength = sequenceLength;
        this.needCloseTag = needCloseTag;
        this.closeTagType = closeTagType;
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

    public int getClosePositionIndex() {
        return closePositionIndex;
    }

    public void setClosePositionIndex(int closePositionIndex) {
        this.closePositionIndex = closePositionIndex;
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

    public TokenType getCloseTagType() {
        return closeTagType;
    }

    public void setCloseTagType(TokenType closeTagType) {
        this.closeTagType = closeTagType;
    }
}

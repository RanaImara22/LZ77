class Tag {
    private int position;
    private int length;
    private char nextChar;

    public static final char END_OF_INPUT = '\0';  // null recognize when it has reached the end of the compressed data.
    public static final int MAX_LENGTH = 255; //determines the maximum number of characters that can be matched in the sliding window.
    public Tag(int position, int length, char nextChar) {
        this.position = position;
        this.length = length;
        this.nextChar = nextChar;
    }

    public int getPosition() {
        return position;
    }

    public int getLength() {
        return length;
    }

    public char getNextChar() {
        return nextChar;
    }

    @Override
    public String toString() {
        return "<" + position + "," + length + "," +"\""+ nextChar +"\""+ ">";
    }
}
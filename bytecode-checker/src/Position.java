public class Position {
    private int startLine;
    private int startColumn;
    private int endLine;
    private int endColumn;
    private int id;

    public Position(int startLine, int startColumn, int endLine, int endColumn, int id) {
        this.startLine = startLine;
        this.startColumn = startColumn;
        this.endLine = endLine;
        this.endColumn = endColumn;
        this.id = id;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getId() {
        return id;
    }
}

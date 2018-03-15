package parser.symbol_table;

public class Variable {

    private String name;
    private String content;

    public Variable(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Variable() {
        this.name = "";
        this.content = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object x) {

        if(x instanceof Variable) {

            // The name is same
            if(((Variable)x).getName() == this.name) {
                return true;
            }
        }

        return false;

    }

    public String toString() {
        return name + ": " + content;
    }
}

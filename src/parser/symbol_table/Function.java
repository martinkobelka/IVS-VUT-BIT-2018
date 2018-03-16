package parser.symbol_table;

import java.util.List;

public class Function {

    private int countOfArguments;
    private String name;

    private String[] arguments;
    private String content;

    public Function(String name, String content, String[] arguments) {
        this.name = name;
        this.content = content;
        this.arguments = arguments;
        this.countOfArguments = arguments.length;
    }

    public int getCountOfArguments() {
        return countOfArguments;
    }

    public void setCountOfArguments(int countOfArguments) {
        this.countOfArguments = countOfArguments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

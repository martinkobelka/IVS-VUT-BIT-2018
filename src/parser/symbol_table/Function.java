package parser.symbol_table;

public class Function {

    private int countOfArguments;
    private String header;

    private String[] arguments;
    private String content;
    private String name;

    public Function(String header, String content) {
        this.header = header;
        this.content = content;
        this.name = "";
    }

    public boolean equals(Object o) {
        if(o instanceof Function) {
            if(((Function)o).getName().equals(name)) {
                return true;
            }
        }

        return false;
    }


    public int getCountOfArguments() {
        return countOfArguments;
    }

    public void setCountOfArguments(int countOfArguments) {
        this.countOfArguments = countOfArguments;
    }

    public String getheader() {
        return header;
    }

    public void setheader(String header) {
        this.header = header;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public TableOfVariables getArgumentsAsTable(double[] values) {

        TableOfVariables tableOfVariables = new TableOfVariables();

        int counter = 0;
        for(String argument: arguments) {

            tableOfVariables.addVariable(new Variable(
                    arguments[counter],
                    String.valueOf(values[counter])
            ));

            counter++;
        }

        return tableOfVariables;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return header + " = " + content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

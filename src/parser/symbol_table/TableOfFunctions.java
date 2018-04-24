package parser.symbol_table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class TableOfFunctions {

    private ObservableList<Function> functions;

    public TableOfFunctions() {
        functions = FXCollections.observableArrayList();

        addBuiltInFunctions();
    }

    private void addBuiltInFunctions() {

        Function f = new Function("f(x)", "x+1");
        f.setName("f");
        f.setCountOfArguments(1);
        f.setArguments(new String[] {"x"});

        functions.add(
                f
        );

    }

    public ObservableList<Function> getFunctions() {
        return functions;
    }

    public void addFunction(Function function) {
        this.functions.add(function);

        this.functions.sort(new Comparator<Function>() {
            @Override
            public int compare(Function o1, Function o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public boolean isFunctionDeclared(Function function) {

        for(Function actualFunction : functions) {
            if(function.equals(actualFunction)) {
                return true;
            }
        }

        return false;
    }

    public Function returnByName(String name) {

        for(Function actualFunction : functions) {
            if(actualFunction.getName().equals(name)) {
                return actualFunction;
            }
        }

        return null;
    }

    public void removeFunction(Function function) {
        this.functions.remove(function);
    }
}

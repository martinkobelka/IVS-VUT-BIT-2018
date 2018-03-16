package parser.symbol_table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TableOfVariables {

    private ObservableList<Variable> variables;

    public TableOfVariables() {
        this.variables = FXCollections.observableArrayList();

        this.addBuiltInConstats();
    }

    /**
     * Add built in constant into
     */
    private void addBuiltInConstats() {

        this.variables.add(
            new Variable("e", "2.71828")
        );

        this.variables.add(
                new Variable("pi", "3.1416")
        );
    }

    public boolean isVariableExists(Variable variable) {

        return variables.contains(variable);

    }

    public void addVariable(Variable variable) {
        variables.add(variable);
    }

    public Variable returnByContent(String content) {

        for(Variable variable : variables) {

            if(variable.getName().equals(content)) {
                return variable;
            }

        }

        System.err.println("Error variable");

        return null;
    }

    public ObservableList<Variable> getVariables() {
        return variables;
    }
}

package parser.symbol_table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import my_math.My_math;
import my_math.Type_of_constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableOfVariables {

    private ObservableList<Variable> variables;

    private My_math my_math;

    public TableOfVariables() {
        this.variables = FXCollections.observableArrayList();

        my_math = new My_math();

        this.addBuiltInConstats();
    }

    /**
     * Add built in constant into
     */
    private void addBuiltInConstats() {

        this.variables.add(
            new Variable("e", String.valueOf(my_math.return_constant(Type_of_constant.EULER_NUMBER)))
        );

        this.variables.add(
                new Variable("pi", String.valueOf(my_math.return_constant(Type_of_constant.PI)))
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

    public static boolean isBuiltInVariable(Variable name) {

        return name.getName().equals("e") || name.getName().equals("pi");

    }

}

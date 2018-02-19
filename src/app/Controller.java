package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import module_example.Class_example;

public class Controller{

    @FXML
    private Button my_button;

    @FXML
    public void test_method(ActionEvent actionEvent) {
        my_button.setText(Class_example.return_value());
    }
}

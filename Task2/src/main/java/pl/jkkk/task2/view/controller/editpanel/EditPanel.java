package pl.jkkk.task2.view.controller.editpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox comboBoxSelectObject;

    @FXML
    private HBox paneTextField;

    @FXML
    private VBox paneComboBoxType;
    @FXML
    private ComboBox comboBoxType;

    @FXML
    private VBox paneFunctionType;
    @FXML
    private ComboBox comboBoxFunctionType;
    @FXML
    private HBox paneFunctionTypePaneParamFirst;
    @FXML
    private HBox paneFunctionTypePaneParamSecond;
    @FXML
    private HBox paneFunctionTypePaneParamThird;
    @FXML
    private HBox paneFunctionTypePaneParamFourth;

    private Initializer initializer;

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializer = new Initializer(
                comboBoxSelectObject, paneTextField, paneComboBoxType, comboBoxType,
                paneFunctionType, comboBoxFunctionType, paneFunctionTypePaneParamFirst,
                paneFunctionTypePaneParamSecond, paneFunctionTypePaneParamThird,
                paneFunctionTypePaneParamFourth
        );
    }

    public void onActionReturn(ActionEvent actionEvent) {

    }

    public void onActionConfirm(ActionEvent actionEvent) {

    }
}
    
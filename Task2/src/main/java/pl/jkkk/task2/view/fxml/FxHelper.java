package pl.jkkk.task2.view.fxml;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class FxHelper {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    private FxHelper() {
    }

    public static void changeTheme(String pathPanel, String title,
                                   String pathCssDarkTheme, String pathCssLightTheme,
                                   ConfigurableApplicationContext applicationContext) {
        if (StageController.getGlobalCssStyling().equals(pathCssDarkTheme)) {
            StageController.setGlobalCssStyling(pathCssLightTheme);
            StageController.reloadStage(pathPanel, title, applicationContext);
        } else if (StageController.getGlobalCssStyling().equals(pathCssLightTheme)) {
            StageController.setGlobalCssStyling(pathCssDarkTheme);
            StageController.reloadStage(pathPanel, title, applicationContext);
        }
    }

    public static void fillComboBox(ComboBox comboBox, List list) {
        List items = comboBox.getItems();
        items.clear();
        list.forEach((it) -> items.add(it));
        comboBox.getSelectionModel().selectFirst();
    }

    public static void fillListView(ListView listView, List list) {
        listView.getItems().addAll(list);
    }

    public static String getValueFromComboBox(ComboBox comboBox) {
        return comboBox.getSelectionModel().getSelectedItem().toString();
    }
}
    
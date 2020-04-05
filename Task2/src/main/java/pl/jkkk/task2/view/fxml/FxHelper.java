package pl.jkkk.task2.view.fxml;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.Collection;
import java.util.List;

public class FxHelper {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    private FxHelper() {
    }

    public static void fillComboBox(ComboBox comboBox, Collection collection) {
        List items = comboBox.getItems();
        items.clear();
        collection.forEach((it) -> items.add(it));
        comboBox.getSelectionModel().selectFirst();
    }

    public static void fillListView(ListView listView, List collection) {
        listView.getItems().addAll(collection);
    }

    public static String getValueFromComboBox(ComboBox comboBox) {
        return comboBox.getSelectionModel().getSelectedItem().toString();
    }
}
    
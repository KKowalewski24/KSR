package pl.jkkk.task2.view.fxml;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

public class FxHelper {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    private FxHelper() {
    }

    /*--------------------------------------------------------------------------------------------*/
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

    /*--------------------------------------------------------------------------------------------*/
    public static void fillComboBox(ComboBox comboBox, List list) {
        List items = comboBox.getItems();
        items.clear();
        list.forEach((it) -> items.add(it));
        comboBox.getSelectionModel().selectFirst();
    }

    public static String getValueFromComboBox(ComboBox comboBox) {
        return comboBox.getSelectionModel().getSelectedItem().toString();
    }

    public static void fillListView(ListView listView, List list) {
        listView.getItems().addAll(list);
    }

    public static void setPaneVisibility(boolean value, Pane... panes) {
        Arrays.stream(panes).forEach((it) -> it.setVisible(value));
    }

    public static void setLabelTextInPane(Pane pane, int childrenNumber, String text) {
        Label label = (Label) pane.getChildren().get(childrenNumber);
        label.setText(text);
    }

    public static int getSelectedTabIndex(TabPane tabPane) {
        return tabPane.getSelectionModel().getSelectedIndex();
    }

    /*--------------------------------------------------------------------------------------------*/
    public static void showProgressIndicator(Runnable runnable,
                                             ProgressIndicator progressIndicator) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Platform.runLater(() -> {
                        progressIndicator.setVisible(true);
                        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
                    });
                    runnable.run();
                    Platform.runLater(() -> progressIndicator.setVisible(false));
                } catch (Exception e) {
                    progressIndicator.setVisible(false);
                    e.printStackTrace();
                }

                return null;
            }
        };

        new Thread(task).start();
    }
}
    
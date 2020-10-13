package views.loader;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowLoader  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Pane graph = loader.load(getClass().getResource("/views/fxml/FXMLWindow.fxml"));

        Scene scene = new Scene(graph, 1000, 733);
        stage.setScene(scene);
        stage.setTitle("Lista de Partidas");

        stage.show();

    }
}

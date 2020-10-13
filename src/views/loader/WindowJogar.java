package views.loader;

import controller.WindowJogarController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowJogar {

    public void start(){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("/views/fxml/FXMLWindowJogar.fxml").openStream());
            Scene scene = new Scene(graph, 600,700);

            WindowJogarController ctrl = loader.getController();

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Jogar");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

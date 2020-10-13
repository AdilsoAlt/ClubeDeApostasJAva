package views.loader;

import controller.WindownCadastroApostadorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroApostador {


    public void start(){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("/views/fxml/FXMLWindowCadastroApostador.fxml").openStream());
            Scene scene = new Scene(graph, 400, 300);

            WindownCadastroApostadorController ctrl = loader.getController();

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cadastro Apostador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




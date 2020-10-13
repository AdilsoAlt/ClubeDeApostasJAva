package views.loader;

import controller.WindownCadastroApostadorController;
import controller.WindownCadastroEditorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCadastroEditor {


    public void start(){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("/views/fxml/FXMLWindowCadastroEditar.fxml").openStream());
            Scene scene = new Scene(graph, 400, 300);

            WindownCadastroEditorController ctrl = loader.getController();

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Editar Cadastro");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




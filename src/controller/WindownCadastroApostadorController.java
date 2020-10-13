package controller;

import DAO.ApostaDAO;
import DAO.ApostadorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import model.Apostador;

public class WindownCadastroApostadorController {

    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtIdade;
    @FXML private Spinner sNumDiv;
    @FXML private Label lbMensagem;

    public void initialize(){
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        this.sNumDiv.setValueFactory(svf);
        sNumDiv.setEditable(false);
    }

    public void saveApostador(ActionEvent actionEvent) {

        Apostador novoApostador = new Apostador();
        novoApostador.setNome(txtNome.getText());
        novoApostador.setCpf(txtCpf.getText());
        novoApostador.setTelefone(txtTelefone.getText());
        novoApostador.setIdade(Integer.parseInt(txtIdade.getText()));
        Integer x = 0;

        x = Integer.valueOf((Integer) sNumDiv.getValue());
        novoApostador.setNumeroDivorcios(x);

        ApostadorDAO a = new ApostadorDAO();
        if (a.save(novoApostador) == true){
            lbMensagem.setText("Cadastrado com Sucesso");
        }else{
            lbMensagem.setText("Ocorreu um erro ao tentar cadastrar o Apostador");
        }

    }

    public void clearApostador(ActionEvent actionEvent) {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtIdade.setText("");

    }


}

package controller;

import DAO.ApostadorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import model.Apostador;

public class WindownCadastroEditorController {

    @FXML
    private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtIdade;
    @FXML private Spinner sNumDiv;
    @FXML private Label lbMensagem;

    @FXML
    public void initialize(){
//        txtNome.setEditable(false);
//        txtTelefone.setEditable(false);
//        txtIdade.setEditable(false);
//        sNumDiv.setEditable(false);
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        this.sNumDiv.setValueFactory(svf);
        sNumDiv.setEditable(false);
    }


    public void findByCpf(ActionEvent actionEvent) {

        String cpf = txtCpf.getText();
        txtCpf.setEditable(false);
        txtNome.setEditable(true);
        txtTelefone.setEditable(true);
        txtIdade.setEditable(true);
        sNumDiv.setEditable(true);
        Apostador apostador;

        ApostadorDAO a = new ApostadorDAO();

            apostador = a.find(cpf);
             System.out.println(apostador.getNome());
            if (apostador != null){

                txtCpf.setText(apostador.getCpf());
                txtNome.setText(apostador.getNome());
                txtTelefone.setText(apostador.getTelefone());
                txtIdade.setText(Integer.toString(apostador.getIdade()));
                Integer x = apostador.getNumeroDivorcios();
                sNumDiv.getValueFactory().setValue(x);

            }else{
                lbMensagem.setText("Apostador não encontrado!");
            }

    }


    public void saveChanges(ActionEvent actionEvent) {
        Apostador novoApostador = new Apostador();
        novoApostador.setNome(txtNome.getText());
        novoApostador.setCpf(txtCpf.getText());
        novoApostador.setTelefone(txtTelefone.getText());
        novoApostador.setIdade(Integer.parseInt(txtIdade.getText()));
        Integer x = 0;

        x = Integer.valueOf((Integer) sNumDiv.getValue());
        novoApostador.setNumeroDivorcios(x);

        ApostadorDAO a = new ApostadorDAO();
        if (a.update(novoApostador) == true){
            lbMensagem.setText("Cadastrado com Sucesso");
        }else{
            lbMensagem.setText("Ocorreu um erro ao tentar cadastrar o Apostador");
        }

    }


    public void deleteMember(ActionEvent actionEvent) {

        String cpf = txtCpf.getText();
        ApostadorDAO a = new ApostadorDAO();
        if (a.delete(cpf)){
            lbMensagem.setText("Apostador excluído com sucesso!");
        }
        else{
            lbMensagem.setText("Erro ao excluir apostador!");
        }
    }
}

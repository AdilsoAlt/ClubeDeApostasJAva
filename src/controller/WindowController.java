package controller;

import DAO.ApostaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import views.RowApostaTable;
import views.loader.WindowCadastroApostador;
import views.loader.WindowCadastroEditor;
import views.loader.WindowJogar;


import java.util.ArrayList;

import java.util.List;

public class WindowController {

    @FXML private TableView tableView;
    @FXML private TableColumn<RowApostaTable, String> cData;
    @FXML private TableColumn<RowApostaTable, String> cJuiz;
    @FXML private TableColumn<RowApostaTable, String> cApostador1;
    @FXML private TableColumn<RowApostaTable, String> cApostador2;
    @FXML private TableColumn<RowApostaTable, String> cJogada1;
    @FXML private TableColumn<RowApostaTable, String> cJogada2;
    @FXML private TableColumn<RowApostaTable, String> cGanhador;
    @FXML private TableColumn<RowApostaTable, String> cPremio;
    @FXML private TextField txtFilterName;
    @FXML private Button btnAtualizar;

    private boolean loaded;
    private List<RowApostaTable> tableData;
    private List<RowApostaTable> filteredTableData;

    @FXML
    public void initialize(){
        bindTable();
        fill();
    }

    private void bindTable() {
        cData.setCellValueFactory(new PropertyValueFactory<>("momento"));
        cJuiz.setCellValueFactory(new PropertyValueFactory<>("juiz"));
        cApostador1.setCellValueFactory(new PropertyValueFactory<>("jogador1"));
        cApostador2.setCellValueFactory(new PropertyValueFactory<>("jogador2"));
        cJogada1.setCellValueFactory(new PropertyValueFactory<>("jogada1"));
        cJogada2.setCellValueFactory(new PropertyValueFactory<>("jogada2"));
        cGanhador.setCellValueFactory(new PropertyValueFactory<>("ganhador"));
        cPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
    }

    private void fill() {

        tableData = new ArrayList<>();
        ApostaDAO apostaDAO = new ApostaDAO();

        tableData = (apostaDAO.findAll());
        if (tableData.size()>0){
            loaded = true;
        }
        tableView.setItems(FXCollections.observableArrayList(tableData));
    }


    public void showWindowCadastroApostador(ActionEvent actionEvent) {

        WindowCadastroApostador view = new WindowCadastroApostador();
        view.start();
    }



    public void showWindowJogar(ActionEvent actionEvent) {

        WindowJogar view = new WindowJogar();
        view.start();
    }


    public void filterByName(KeyEvent keyEvent) {
        if(loaded){
            if(txtFilterName.getText().equals("") )
                filteredTableData = tableData;
            else{
                filteredTableData = new ArrayList<>();
                for (RowApostaTable t : tableData ){
                    if(t.getJogador1().contains(txtFilterName.getText().toUpperCase()) ||
                            t.getJogador2().contains(txtFilterName.getText().toUpperCase()) ||
                            t.getGanhador().contains(txtFilterName.getText().toUpperCase())){
                        filteredTableData.add(t);
                    }
                }
            }
            refill(filteredTableData);
        }
    }


    private void refill(List<RowApostaTable> data) {
        tableView.getItems().clear();
        for (RowApostaTable st : data) {
            tableView.getItems().add(st);
        }
    }


    public void showWindowCadastroEditor(ActionEvent actionEvent) {
        WindowCadastroEditor view = new WindowCadastroEditor();
        view.start();
    }

    public void refreshData(ActionEvent actionEvent) {
        fill();
    }
}

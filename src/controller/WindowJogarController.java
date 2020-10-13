package controller;


import DAO.ApostaDAO;
import DAO.ApostadorDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class WindowJogarController {


    @FXML private TextField txtNomePremio1;
    @FXML private TextField txtNomePremio2;
    @FXML private TextField txtValor1;
    @FXML private TextField txtValor2;
    @FXML private Label lbResultado;
    @FXML private TextField txtJuiz;


    @FXML private ComboBox<ApostadorDTO> cbJogador2;
    @FXML private ComboBox<ApostadorDTO> cbJogador1;
    @FXML private ComboBox<PedraPapelTesoura> cbJogada1;
    @FXML private ComboBox<PedraPapelTesoura> cbJogada2;
    @FXML private Button btnJogarContraClube;
    @FXML private Button btnJogarContraMembro;
    @FXML private DatePicker dtPicker;
    @FXML private Button btnJogar;
    @FXML private RadioButton rbItem1Sim;
    @FXML private RadioButton rbItem1Nao;
    @FXML private RadioButton rbItem2Sim;
    @FXML private RadioButton rbItem2Nao;
    @FXML private Label lbNomePremio2;
    @FXML private Label lbValorPremio2;
    @FXML private Label lbJogada2;
    @FXML private Label lbItem2;
    @FXML private ToggleGroup itemfamila2;
    @FXML private ToggleGroup itemfamila1;

    private List<ApostadorDTO> apostadores;



    @FXML
    public void initialize(){

        setLayoutInicilize();
        ApostadorDAO apostadores = new ApostadorDAO();

        this.apostadores = apostadores.findApostadores();
        System.out.println(apostadores.findApostadores());
        cbJogador1.setItems(FXCollections.observableArrayList(this.apostadores));
        cbJogador2.setItems(FXCollections.observableArrayList(this.apostadores));

    }

    public void jogar(ActionEvent actionEvent) {

        prepareToPlay();

    }



    public void prepareToPlay(){
        // dados do jogador 1
        LocalDate data = dtPicker.getValue();
        Aposta novaAposta = new Aposta();

        novaAposta.setJogador1(setPlayer1());
        novaAposta.setJogador2(setPlayer2());
        novaAposta.setMomento(data);
        novaAposta.setJuiz(txtJuiz.getText());
        novaAposta.setJogada1(validaJogada(cbJogada1.getSelectionModel().getSelectedIndex()));
        novaAposta.setJogada2(validaJogada(cbJogada2.getSelectionModel().getSelectedIndex()));

        if(novaAposta.defineGanhador(novaAposta.getJogador1(), novaAposta.getJogador2(),novaAposta.getJogada1(),novaAposta.getJogada2()) == null){

            lbResultado.setText("EMPATE");
        }else{
            Premio premio = new Premio();
            novaAposta.setGanhador(novaAposta.defineGanhador(novaAposta.getJogador1(), novaAposta.getJogador2(),novaAposta.getJogada1(),novaAposta.getJogada2()));

            if (novaAposta.getGanhador().getCpf().equals(novaAposta.getJogador1().getCpf())){
                // se jogador1 um ganhar, ganha o que o jogador2 apostou
                premio.setNome(txtNomePremio2.getText());
                premio.setValorDeclarado(Double.parseDouble(txtValor2.getText()));

                RadioButton r2 = (RadioButton) itemfamila2.getSelectedToggle();
                if(r2.getText().equals("SIM")){
                    premio.setItemDeFamilia(true);
                }else{
                    premio.setItemDeFamilia(false);
                }

                novaAposta.setPremio(premio);
                lbResultado.setText("GANAHDOR:"+novaAposta.getGanhador().getNome());

            }else
            {
                premio.setNome(txtNomePremio1.getText());
                premio.setValorDeclarado(Double.parseDouble(txtValor1.getText()));
                premio.setItemDeFamilia(false);
                RadioButton r1 = (RadioButton) itemfamila1.getSelectedToggle();
                System.out.println(r1.getId());
                if(r1.getText().equals("SIM")){
                    premio.setItemDeFamilia(true);
                }else{
                    premio.setItemDeFamilia(false);
                }

                novaAposta.setPremio(premio);
                lbResultado.setText("GANHADOR: "+novaAposta.getGanhador().getNome());

            }
            ApostaDAO a = new ApostaDAO();
            if(a.saveAposta(novaAposta)){

            };

        }

    }


    public ApostadorDTO playWithClub(){
        int x = apostadores.size();
        int aleatorio = new Random().nextInt(x);
        return apostadores.get(aleatorio);
    }


    public void showApostadores(ActionEvent actionEvent) {

    }

    public void setJogarContraClube(ActionEvent actionEvent) {
        setLayoutContraClube();
    }

    public void setJogarContraMembro(ActionEvent actionEvent) {
        setLayoutVisible();
    }

    public Apostador setPlayer1 (){
        Apostador ap1 = new Apostador();
        ap1.setCpf(cbJogador1.getSelectionModel().getSelectedItem().getCpf());
        ap1.setNome(cbJogador1.getSelectionModel().getSelectedItem().getNome());
        ap1.setNumeroDivorcios(cbJogador1.getSelectionModel().getSelectedItem().getNumeroDivorcios());
        ap1.setIdade(cbJogador1.getSelectionModel().getSelectedItem().getIdade());
        ap1.setTelefone(cbJogador1.getSelectionModel().getSelectedItem().getTelefone());
        return ap1;
    }
    public Apostador setPlayer2 (){
        Apostador ap2 = new Apostador();
        ap2.setCpf(cbJogador2.getSelectionModel().getSelectedItem().getCpf());
        ap2.setNome(cbJogador2.getSelectionModel().getSelectedItem().getNome());
        ap2.setNumeroDivorcios(cbJogador2.getSelectionModel().getSelectedItem().getNumeroDivorcios());
        ap2.setIdade(cbJogador2.getSelectionModel().getSelectedItem().getIdade());
        ap2.setTelefone(cbJogador2.getSelectionModel().getSelectedItem().getTelefone());
        return ap2;
    }

    public Apostador setPlayer2 (ApostadorDTO a){
        Apostador ap2 = new Apostador();
        ap2.setCpf(cbJogador2.getSelectionModel().getSelectedItem().getCpf());
        ap2.setNome(cbJogador2.getSelectionModel().getSelectedItem().getNome());
        ap2.setNumeroDivorcios(cbJogador2.getSelectionModel().getSelectedItem().getNumeroDivorcios());
        ap2.setIdade(cbJogador2.getSelectionModel().getSelectedItem().getIdade());
        ap2.setTelefone(cbJogador2.getSelectionModel().getSelectedItem().getTelefone());
        return ap2;
    }

    public PedraPapelTesoura validaJogada(int i){
        if(i == 0){
            return PedraPapelTesoura.PEDRA;
        }
        else if(i ==1 ){
            return  PedraPapelTesoura.PAPEL;
        }
        else{
            return PedraPapelTesoura.TESOURA;
        }
    }


    public void setLayoutVisible(){
        btnJogarContraClube.setVisible(false);
        btnJogarContraMembro.setVisible(false);
        txtNomePremio1.setVisible(true);
        txtNomePremio2.setVisible(true);
        txtValor1.setVisible(true);
        txtValor2.setVisible(true);
        cbJogada1.setVisible(true);
        cbJogada2.setVisible(true);
        rbItem1Sim.setVisible(true);
        rbItem1Nao.setVisible(true);
        rbItem2Sim.setVisible(true);
        rbItem2Nao.setVisible(true);
        lbNomePremio2.setVisible(true);
        lbValorPremio2.setVisible(true);
        txtValor2.setVisible(true);
        cbJogada2.setVisible(true);
        rbItem2Sim.setVisible(true);
        rbItem2Nao.setVisible(true);
        lbJogada2.setVisible(true);
        lbItem2.setVisible(true);
        cbJogador2.setVisible(true);
        txtNomePremio2.setVisible(true);
        btnJogar.setVisible(true);

    }
    public void setLayoutContraClube (){

        setLayoutVisible();
        btnJogarContraClube.setVisible(false);
        btnJogarContraMembro.setVisible(false);
        txtNomePremio2.setEditable(false);
        txtValor2.setEditable(false);
        cbJogada2.setEditable(false);
        cbJogador2.setEditable(false);



    }
    public void setLayoutInicilize(){
        lbNomePremio2.setVisible(false);
        lbValorPremio2.setVisible(false);
        txtValor2.setVisible(false);
        cbJogada2.setVisible(false);
        rbItem2Sim.setVisible(false);
        rbItem2Nao.setVisible(false);
        lbJogada2.setVisible(false);
        lbItem2.setVisible(false);
        cbJogador2.setVisible(false);
        txtNomePremio2.setVisible(false);
        btnJogar.setVisible(false);

        cbJogada1.setItems(FXCollections.observableArrayList(PedraPapelTesoura.values()));
        cbJogada2.setItems(FXCollections.observableArrayList(PedraPapelTesoura.values()));

    }

}

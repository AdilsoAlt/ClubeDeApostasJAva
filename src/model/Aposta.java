package model;

import java.sql.Date;
import java.time.LocalDate;

public class Aposta {
    private LocalDate momento;
    private String juiz;
    private Apostador jogador1;
    private Apostador jogador2;
    private PedraPapelTesoura jogada1;
    private PedraPapelTesoura jogada2;
    private Apostador ganhador;
    private Premio premio;

    public Aposta( ) {

    }


    public Aposta(LocalDate momento, String juiz, Apostador jogador1, Apostador jogador2, PedraPapelTesoura jogada1, PedraPapelTesoura jogada2, Apostador ganhador, Premio premio) {
        this.momento = momento;
        this.juiz = juiz;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogada1 = jogada1;
        this.jogada2 = jogada2;
        this.ganhador = ganhador;
        this.premio = premio;
    }


    public LocalDate getMomento() {
        return momento;
    }

    public void setMomento(LocalDate momento) {
        this.momento = momento;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public Apostador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Apostador jogador1) {
        this.jogador1 = jogador1;
    }

    public Apostador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Apostador jogador2) {
        this.jogador2 = jogador2;
    }

    public PedraPapelTesoura getJogada1() {
        return jogada1;
    }

    public void setJogada1(PedraPapelTesoura jogada1) {
        this.jogada1 = jogada1;
    }

    public PedraPapelTesoura getJogada2() {
        return jogada2;
    }

    public void setJogada2(PedraPapelTesoura jogada2) {
        this.jogada2 = jogada2;
    }

    public Apostador getGanhador() {
        return ganhador;
    }

    public void setGanhador(Apostador ganhador) {
        this.ganhador = ganhador;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    public Apostador defineGanhador(Apostador jogador1, Apostador jogador2, PedraPapelTesoura jogada1, PedraPapelTesoura jogada2) {
        int j1;
        int j2;

        if(jogada1.toString() == PedraPapelTesoura.PEDRA.toString()){
            j1 = 0;
        }else if (jogada1.toString() == PedraPapelTesoura.PAPEL.toString()){
            j1 = 1;
        }else{
            j1=2;
        }
        if(jogada2.toString() == PedraPapelTesoura.PEDRA.toString()){
            j2 = 0;
        }else if (jogada2.toString() == PedraPapelTesoura.PAPEL.toString()){
            j2 = 1;
        }else{
            j2=2;
        }
            //pedra ==0
            //papel == 1
            //tesoura ==2
        if (j1 == j2){
            return null;
        }else if ((j1 ==0 && j2 == 2) || (j1 == 1 && j2 == 0) || (j1 == 2 && j2 == 1) ){
            return jogador1;
        } else{
            return jogador2;
        }

    }


}
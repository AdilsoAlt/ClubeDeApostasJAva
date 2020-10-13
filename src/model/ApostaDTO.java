package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApostaDTO {
    private LocalDate momento;
    private String juiz;
    private String jogador1;
    private String jogador2;
    private String jogada1;
    private String jogada2;
    private String ganhador;
    private String premio;

    public LocalDate getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(momento, formatter);
        this.momento = date;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public String getJogador1() {
        return jogador1;
    }

    public void setJogador1(String jogador1) {
        this.jogador1 = jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public void setJogador2(String jogador2) {
        this.jogador2 = jogador2;
    }

    public String getJogada1() {
        return jogada1;
    }

    public void setJogada1(String jogada1) {
        this.jogada1 = jogada1;
    }

    public String getJogada2() {
        return jogada2;
    }

    public void setJogada2(String jogada2) {
        this.jogada2 = jogada2;
    }

    public String getGanhador() {
        return ganhador;
    }

    public void setGanhador(String ganhador) {
        this.ganhador = ganhador;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }
}

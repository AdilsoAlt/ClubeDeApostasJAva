package model;

public enum PedraPapelTesoura {
    PEDRA ("Pedra"),
    PAPEL ("Papel"),
    TESOURA ("Tesoura");

    private String jogada;

    PedraPapelTesoura(String jogada){
        this.jogada = jogada;
    }

    @Override
    public String toString() {
        return jogada;
    }
}

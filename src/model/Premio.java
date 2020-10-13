package model;

public class Premio {
    private String nome;
    private double valorDeclarado;
    private boolean itemDeFamilia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public boolean isItemDeFamilia() {
        return itemDeFamilia;
    }

    public void setItemDeFamilia(boolean itemDeFamilia) {
        this.itemDeFamilia = itemDeFamilia;
    }
}

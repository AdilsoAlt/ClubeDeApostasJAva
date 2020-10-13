package model;

import java.util.ArrayList;
import java.util.List;

public class Apostador {
    private String nome;
    private String cpf;
    private String telefone;
    private int idade;
    private int numeroDivorcios;

    private List<Aposta> apostas = new ArrayList<>();

    public Apostador(String nome, String cpf, String telefone, int idade, int numeroDivorcios, List<Aposta> partidas) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idade = idade;
        this.numeroDivorcios = numeroDivorcios;
    }

    public Apostador(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getNumeroDivorcios() {
        return numeroDivorcios;
    }

    public void setNumeroDivorcios(int numeroDivorcios) {
        this.numeroDivorcios = numeroDivorcios;
    }
    public void addApostas(Aposta a){
       apostas.add(a);
    }


}

package model;

import java.util.ArrayList;
import java.util.List;

public class ApostadorDTO {

    private String nome;
    private String cpf;

    private String telefone;
    private int idade;
    private int numeroDivorcios;

    public List<ApostaDTO> getApostas() {
        return apostas;
    }

    private List<ApostaDTO> apostas = new ArrayList<>();

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


    public void add(List<ApostaDTO> apostas) {
        this.apostas.addAll(apostas);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}

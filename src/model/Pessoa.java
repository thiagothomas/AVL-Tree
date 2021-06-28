package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {

    private final long cpf;
    private final long rg;
    private final String nome;
    private final Date dataNascimento;
    private final String cidadeNascimento;

    public Pessoa(long cpf, long rg, String nome, Date dataNascimento, String cidadeNascimento) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cidadeNascimento = cidadeNascimento;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return  "* Nome: " + nome +
                "\n* CPF: " + cpf +
                "\n* RG: " + rg +
                "\n* Data de Nascimento:" +  dateFormat.format(dataNascimento) +
                "\n* Cidade de Nascimento: " + cidadeNascimento;
    }
}

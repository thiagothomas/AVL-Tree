package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dado<E> {

    private final E dadoPessoa; // cpf(long) ou data nascimento(date) ou nome(string)
    private final List<Integer> indicesComMesmoDado; // no cpf tera apenas um indice, mas com nome e data podem ter multiplos indices com mesmo dado

    public Dado(E dadoPessoa, int index) {
        this.dadoPessoa = dadoPessoa;
        indicesComMesmoDado = new ArrayList<>();
        indicesComMesmoDado.add(index);
    }

    public E getDadoPessoa() {
        return dadoPessoa;
    }

    public List<Integer> getIndicesComMesmoDado() {
        return indicesComMesmoDado;
    }

    // para cada tipo de dado há um retorno diferente!
    public String toString() {
        String retorno = indicesComMesmoDado.toString();
        if(dadoPessoa != null) {
            if (dadoPessoa instanceof String) {
                retorno += (String) dadoPessoa;
            } else if (dadoPessoa instanceof Date) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                retorno += dateFormat.format((Date) dadoPessoa);
            } else {
                retorno += ((Long) dadoPessoa).toString();
            }
        } else {
            return "";
        }
        return retorno;
    }

    // verifica se o valor da classe é menor que o @atual
    public boolean beforeValue(Dado<E> atual) {
        if(dadoPessoa instanceof String) {
            return ((String) this.dadoPessoa).compareTo((String) atual.getDadoPessoa()) < 0;
        } else if(dadoPessoa instanceof Date) {
            return ((Date) dadoPessoa).before((Date) atual.getDadoPessoa());
        } else {
            return ((long) dadoPessoa) < ((long) atual.dadoPessoa);
        }
    }

    // verifica se o valor da classe é maior que o @atual
    public boolean afterValue(Dado<E> atual) {
        if(dadoPessoa instanceof String) {
            return ((String) this.dadoPessoa).compareTo((String) atual.getDadoPessoa()) > 0;
        } else if(dadoPessoa instanceof Date) {
            return ((Date) dadoPessoa).after((Date) atual.getDadoPessoa());
        } else {
            return ((long) dadoPessoa) > ((long) atual.dadoPessoa);
        }
    }

}

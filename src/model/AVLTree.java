package model;

import java.util.*;

public class AVLTree<E> {

    private Node<E> raiz;
    private int numElementos;

    public AVLTree() {
        this.numElementos = 0;
    }

    private boolean buscar(Node<E> atual, Dado<E> valor) {
        if(Objects.isNull(atual)) {
            return false;
        }

        if(valor.beforeValue(atual.getDado())) {
            return buscar(atual.getEsquerda(), valor);
        }

        if(valor.afterValue(atual.getDado())) {
            return buscar(atual.getDireita(), valor);
        }

        // caso chegue aqui é por que o valor existe, mas apenas retorna true para cpf, caso seja data ou nome pode adicionar
        return !(valor.getDadoPessoa() instanceof String) && !(valor.getDadoPessoa() instanceof Date);
    }

    public void inserir(Dado<E> valor) {
        if(!buscar(raiz, valor)) {
            raiz = inserir(raiz, valor);
            numElementos++;
        }
    }

    private Node<E> inserir(Node<E> atual, Dado<E> valor) {
        if(Objects.isNull(atual)) {
            return new Node<>(valor);
        }

        if(atual.getDado().getDadoPessoa().equals(valor.getDadoPessoa())) {
            atual.getDado().getIndicesComMesmoDado().add(valor.getIndicesComMesmoDado().get(0));
            return balancear(atual);
        }

        if(valor.beforeValue(atual.getDado())) {
            atual.setEsquerda(inserir(atual.getEsquerda(), valor));
        } else {
            atual.setDireita(inserir(atual.getDireita(), valor));
        }

        return balancear(atual);
    }

    private Node<E> balancear(Node<E> atual) {
        atualizar(atual);

        if(fatorBalanceamento(atual) > 1) {
            if (!rotacaoSimplesDireita(atual)) {
                atual.setEsquerda(rotacaoEsquerda(atual.getEsquerda()));
            }
            atual = rotacaoDireita(atual);
        } else if(fatorBalanceamento(atual) < -1) {
            if(!rotacaoSimplesEsquerda(atual)) {
                atual.setDireita(rotacaoDireita(atual.getDireita()));
            }
            atual = rotacaoEsquerda(atual);
        }

        return atual;
    }

    private Node<E> rotacaoDireita(Node<E> atual) {
        Node<E> pai = atual.getEsquerda();

        atual.setEsquerda(pai.getDireita());
        pai.setDireita(atual);

        atualizar(atual);
        atualizar(pai);

        return pai;
    }

    private Node<E> rotacaoEsquerda(Node<E> atual) {
        Node<E> pai = atual.getDireita();

        atual.setDireita(pai.getEsquerda());
        pai.setEsquerda(atual);

        atualizar(atual);
        atualizar(pai);

        return pai;
    }

    private void atualizar(Node<E> atual) {
        int alturaEsquerda = altura(atual.getEsquerda());
        int alturaDireita = altura(atual.getDireita());

        atual.setAltura(1 + Math.max(alturaEsquerda, alturaDireita));
    }

    private int fatorBalanceamento(Node<E> atual) {
        if(Objects.isNull(atual)) {
            return 0;
        } else {
            return altura(atual.getEsquerda()) - altura(atual.getDireita());
        }
    }

    private int altura(Node<E> atual) {
        if(Objects.isNull(atual)) {
            return -1;
        } else {
            return atual.getAltura();
        }
    }

    private boolean rotacaoSimplesDireita(Node<E> atual) {
        return altura(atual.getEsquerda().getEsquerda()) > altura(atual.getEsquerda().getDireita());
    }

    private boolean rotacaoSimplesEsquerda(Node<E> atual) {
        return altura(atual.getDireita().getDireita()) > altura(atual.getDireita().getEsquerda());
    }

    public Node<E> getRaiz() {
        return raiz;
    }

    public List<Integer> getIndexCPF(Node<Long> atual, long valor) {
        if(Objects.isNull(atual)) {
            return Collections.emptyList();
        }

        if(valor < (atual.getDado().getDadoPessoa())) {
            return getIndexCPF(atual.getEsquerda(), valor);
        }

        if(valor > (atual.getDado().getDadoPessoa())) {
            return getIndexCPF(atual.getDireita(), valor);
        }

        return atual.getDado().getIndicesComMesmoDado();
    }

    public List<Integer> getIndexesFromDateInterval(Node<Date> atual, Date initialDate, Date finalDate, List<Integer> indices) {
        if(Objects.isNull(atual)) {
            return Collections.emptyList();
        }

        Date data = atual.getDado().getDadoPessoa();

        if(data.after(initialDate)) {
            getIndexesFromDateInterval(atual.getEsquerda(), initialDate, finalDate, indices);
        }
        if(data.before(finalDate)) {
            getIndexesFromDateInterval(atual.getDireita(), initialDate, finalDate, indices);
        }

        if(data.after(initialDate) && data.before(finalDate)) {
            indices.addAll(atual.getDado().getIndicesComMesmoDado());
        }

        return indices;
    }

    public List<Integer> getIndexesFromNamesStartingWith(Node<String> atual, String nameFragment, List<Integer> indices) {
        if(Objects.isNull(atual)) {
            return Collections.emptyList();
        }

        if(nameFragment.toUpperCase().compareTo(String.valueOf(atual.getDado().getDadoPessoa()).toUpperCase().substring(0, nameFragment.length())) <= 0) {
            getIndexesFromNamesStartingWith(atual.getEsquerda(), nameFragment, indices);
        }

        if(nameFragment.toUpperCase().compareTo(String.valueOf(atual.getDado().getDadoPessoa()).toUpperCase().substring(0, nameFragment.length())) >= 0) {
            getIndexesFromNamesStartingWith(atual.getDireita(), nameFragment, indices);
        }

        if(String.valueOf(atual.getDado().getDadoPessoa()).toUpperCase().startsWith(nameFragment.toUpperCase())) {
            indices.addAll(atual.getDado().getIndicesComMesmoDado());
        }

        return indices;
    }

}

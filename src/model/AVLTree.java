package model;

import java.util.Objects;

public class AVLTree {

    private Node raiz;
    private int numElementos;

    public AVLTree() {
        this.numElementos = 0;
    }

    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

    private boolean buscar(Node atual, int valor) {
        if(Objects.isNull(atual)) {
            return false;
        }

        if(valor < atual.getValor()) {
            return buscar(atual.getEsquerda(), valor);
        }
        if(valor > atual.getValor()) {
            return buscar(atual.getDireita(), valor);
        }

        return true;
    }

    public boolean inserir(int valor) {
        if(!buscar(raiz, valor)) {
            raiz = inserir(raiz, valor);
            numElementos++;
            return true;
        } else {
            return false;
        }
    }

    private Node inserir(Node atual, int valor) {
        if(Objects.isNull(atual)) {
            return new Node(valor);
        }

        if(valor < atual.getValor()) {
            atual.setEsquerda(inserir(atual.getEsquerda(), valor));
        } else {
            atual.setDireita(inserir(atual.getDireita(), valor));
        }

        return balancear(atual);
    }

    public boolean remover(int valor) {
        if(buscar(raiz, valor)) {
            raiz = remover(raiz, valor);
            numElementos--;
            return true;
        } else {
            return false;
        }
    }

    private Node remover(Node atual, int valor) {
        if (Objects.isNull(atual)) {
            return null;
        }

        if(valor < atual.getValor()) {
            atual.setEsquerda(remover(atual.getEsquerda(), valor));
        } else if(valor > atual.getValor()) {
            atual.setDireita(remover(atual.getDireita(), valor));
        } else {
            if(Objects.isNull(atual.getEsquerda())) {
                atual = atual.getDireita();
            } else if(Objects.isNull(atual.getDireita())) {
                atual = atual.getEsquerda();
            } else {
                if (atual.getDireita().getAltura() > atual.getEsquerda().getAltura()) {
                    Node menorValorDireita = menorValorNaSubarvore(atual.getDireita());
                    atual.setValor(menorValorDireita.getValor());
                    atual.setDireita(remover(atual.getDireita(), menorValorDireita.getValor()));
                } else {
                    Node maiorValorEsquerda = maiorValorNaSubarvore(atual.getEsquerda());
                    atual.setValor(maiorValorEsquerda.getValor());
                    atual.setEsquerda(remover(atual.getEsquerda(), maiorValorEsquerda.getValor()));
                }
            }
        }

        if(!Objects.isNull(atual)) {
            atual = balancear(atual);
        }

        return atual;
    }

    private Node balancear(Node atual) {
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

    private Node rotacaoDireita(Node atual) {
        Node pai = atual.getEsquerda();

        atual.setEsquerda(pai.getDireita());
        pai.setDireita(atual);

        atualizar(atual);
        atualizar(pai);

        return pai;
    }

    private Node rotacaoEsquerda(Node atual) {
        Node pai = atual.getDireita();

        atual.setDireita(pai.getEsquerda());
        pai.setEsquerda(atual);

        atualizar(atual);
        atualizar(pai);

        return pai;
    }

    private void atualizar(Node atual) {
        int alturaEsquerda = altura(atual.getEsquerda());
        int alturaDireita = altura(atual.getDireita());

        atual.setAltura(1 + Math.max(alturaEsquerda, alturaDireita));
    }

    private Node menorValorNaSubarvore(Node atual) {
        while(!Objects.isNull(atual.getEsquerda())) {
            atual = atual.getEsquerda();
        }

        return atual;
    }

    private Node maiorValorNaSubarvore(Node atual) {
        while(!Objects.isNull(atual.getDireita())) {
            atual = atual.getDireita();
        }

        return atual;
    }

    private int fatorBalanceamento(Node atual) {
        if(Objects.isNull(atual)) {
            return 0;
        } else {
            return altura(atual.getEsquerda()) - altura(atual.getDireita());
        }
    }

    private int altura(Node atual) {
        if(Objects.isNull(atual)) {
            return -1;
        } else {
            return atual.getAltura();
        }
    }

    private boolean rotacaoSimplesDireita(Node atual) {
        return altura(atual.getEsquerda().getEsquerda()) > altura(atual.getEsquerda().getDireita());
    }

    private boolean rotacaoSimplesEsquerda(Node atual) {
        return altura(atual.getDireita().getDireita()) > altura(atual.getDireita().getEsquerda());
    }

    public Node getRaiz() {
        return raiz;
    }

    public int getNumElementos() {
        return numElementos;
    }
}

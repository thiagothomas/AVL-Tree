package model;

public class Node<E> {

    private Node<E> esquerda;
    private Node<E> direita;
    private Dado<E> dado;
    private int altura;

    public Node(Dado<E> dado) {
        this.dado = dado;
    }

    public Node<E> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public Node<E> getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public Dado<E> getDado() {
        return dado;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}

package view;

import model.AVLTree;
import model.Node;

import java.util.Objects;

public class TreeViewer {

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        avlTree.inserir(1);
        avlTree.inserir(2);
        avlTree.inserir(3);
        avlTree.inserir(4);
        avlTree.inserir(5);
        avlTree.inserir(6);
        avlTree.inserir(7);
        avlTree.inserir(8);
        avlTree.inserir(9);
        avlTree.inserir(10);
        avlTree.inserir(11);
        avlTree.inserir(12);
        avlTree.inserir(13);
        avlTree.inserir(14);
        avlTree.inserir(15);

        imprimeArvore(avlTree);

    }

    public static void imprimeArvore(AVLTree avlTree) {
        imprimeArovre(avlTree.getRaiz(), 0, 5, "├ ");
    }

    private static void imprimeArovre(Node raiz, int espacoAtual, int incrementador, String pos) {
        if(Objects.isNull(raiz)) {
            return;
        }

        espacoAtual += incrementador;

        imprimeArovre(raiz.getDireita(), espacoAtual, incrementador, "┌ ");

        for(int i=incrementador; i<espacoAtual; i++) {
            System.out.print(" ");
        }
        System.out.println(pos+raiz.getValor());
        imprimeArovre(raiz.getEsquerda(), espacoAtual, incrementador, "└ ");
    }

    public static void imprimirPosOrdem(Node raiz) {
        if (Objects.isNull(raiz)) {
            return;
        }

        imprimirPosOrdem(raiz.getEsquerda());
        imprimirPosOrdem(raiz.getDireita());
        System.out.print(raiz.getValor() + " ");
    }

    public static void imprimirEmOrdem(Node raiz) {
        if (Objects.isNull(raiz)) {
            return;
        }

        imprimirEmOrdem(raiz.getEsquerda());
        System.out.print(raiz.getValor() + " ");
        imprimirEmOrdem(raiz.getDireita());
    }

    public static void imprimirPreOrdem(Node raiz) {
        if (Objects.isNull(raiz)) {
            return;
        }

        System.out.print(raiz.getValor() + " ");
        imprimirPreOrdem(raiz.getEsquerda());
        imprimirPreOrdem(raiz.getDireita());
    }

}

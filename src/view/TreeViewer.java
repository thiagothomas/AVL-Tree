package view;

import model.AVLTree;
import model.Node;

import java.util.Objects;

public class TreeViewer {

    private TreeViewer(){

    }

    public static void imprimeOpcoes(){
        System.out.println("------------- ESCOLHA A OPERACAO DESEJADA -------------");
        System.out.println(" * [1] - Inserir elemento");
        System.out.println(" * [2] - Buscar elemento");
        System.out.println(" * [3] - Remover elemento");
        System.out.println(" * [4] - Imprimir toda árvore");
        System.out.println(" * [5] - Imprimir em ordem");
        System.out.println(" * [6] - Imprimir em Pré-ordem");
        System.out.println(" * [7] - Imprimir em Pós-ordem");
        System.out.println(" * [8] - Sair");
        System.out.println("-------------------------------------------------------");
    }

    public static void imprimeArvore(AVLTree avlTree) {
        imprimeArovre(avlTree.getRaiz(), 0, 10, "├ ");
    }

    // Codigo baseado no video https://www.youtube.com/watch?v=eY3SZLGCK2E
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

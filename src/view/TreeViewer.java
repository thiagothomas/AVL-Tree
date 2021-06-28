package view;

import model.AVLTree;
import model.Node;
import model.Pessoa;

import java.util.List;
import java.util.Objects;

public class TreeViewer<E> {

    public TreeViewer(){

    }

    public static void imprimeOpcoes(){
        System.out.println("+------------------------- ESCOLHA A CONSULTA DESEJADA --------------------------+");
        System.out.println("| * [1] - Pessoas por CPF;                                                       |");
        System.out.println("| * [2] - Pessoas por nomes que começam com determinado fragmento;               |");
        System.out.println("| * [3] - Pessoas cujas datas de nascimento estão entre determinado intervalo;   |");
        System.out.println("| * [4] - Sair.                                                                  |");
        System.out.println("+--------------------------------------------------------------------------------+");
    }

    public void imprimeArvore(AVLTree<E> avlTree) {
        imprimeArovre(avlTree.getRaiz(), 0, 10, "├ ");
    }

    // Codigo baseado no video https://www.youtube.com/watch?v=eY3SZLGCK2E
    private void imprimeArovre(Node<E> raiz, int espacoAtual, int incrementador, String pos) {
        if(Objects.isNull(raiz)) {
            return;
        }

        espacoAtual += incrementador;

        imprimeArovre(raiz.getDireita(), espacoAtual, incrementador, "┌ ");

        for(int i=incrementador; i<espacoAtual; i++) {
            System.out.print(" ");
        }
        System.out.println(pos+raiz.getDado().toString());
        imprimeArovre(raiz.getEsquerda(), espacoAtual, incrementador, "└ ");
    }

    public static void imprimeDados(List<Integer> indices, Pessoa[] pessoas) {
        System.out.println("+----------------- PESSOAS ENCONTRADAS NA CONSULTA SELECIONADA ------------------+");
        if(indices.isEmpty()) {
            System.out.println("* NÃO FORAM ENCONTRADAS PESSOAS PARA A BUSCA SELECIONADA!\n");
            return;
        } else {
            System.out.print("* FORAM ENCONTRADAS "+ indices.size() + " PESSOAS PARA A BUSCA SELECIONADA!\n");
        }
        indices.forEach(i -> System.out.println("-\n"+pessoas[i].toString()));
        System.out.println("+--------------------------------------------------------------------------------+\n");
    }
}

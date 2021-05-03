package controller;

import model.AVLTree;
import view.TreeViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TreeController {

    private static final String DEFAULT_MESSAGE = "* A ÁRVORE ESTÁ VAZIA!";

    public static void main(String[] args) {
        InputStream is = System.in;
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);

        AVLTree avlTree = new AVLTree();

        int opcao;

        label:
        while(true){
            try {
                TreeViewer.imprimeOpcoes();
                System.out.print("Digite aqui: ");
                opcao = Integer.parseInt(br.readLine());
                System.out.println();

                switch (opcao) {
                    case 1:
                        System.out.print("DIGITE O NUMERO INTEIRO QUE DESEJA INSERIR: ");
                        avlTree.inserir(Integer.parseInt(br.readLine()));
                        break;
                    case 2:
                        System.out.print("DIGITE O NUMERO INTEIRO QUE DESEJA BUSCAR: ");
                        avlTree.buscar(Integer.parseInt(br.readLine()));
                        break;
                    case 3:
                        System.out.print("DIGITE O NUMERO INTEIRO QUE DESEJA REMOVER: ");
                        avlTree.remover(Integer.parseInt(br.readLine()));
                        break;
                    case 4:
                        if (avlTree.getNumElementos() > 0) TreeViewer.imprimeArvore(avlTree);
                        else System.out.println(DEFAULT_MESSAGE);
                        break;
                    case 5:
                        if(avlTree.getNumElementos() > 0) {
                            System.out.print("ARVORE EM ORDEM: ");
                            TreeViewer.imprimirEmOrdem(avlTree.getRaiz());
                        } else System.out.println(DEFAULT_MESSAGE);
                        break;
                    case 6:
                        if(avlTree.getNumElementos() > 0) {
                            System.out.print("ARVORE PRE ORDEM: ");
                            TreeViewer.imprimirPreOrdem(avlTree.getRaiz());
                        } else System.out.println(DEFAULT_MESSAGE);
                        break;
                    case 7:
                        if(avlTree.getNumElementos() > 0) {
                            System.out.print("AROVRE POS ORDEM: ");
                            TreeViewer.imprimirPosOrdem(avlTree.getRaiz());
                        } else System.out.println(DEFAULT_MESSAGE);
                        break;
                    case 8:
                        System.out.println("Muito Obrigado! Encerrando o programa.");
                        break label;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        break;
                }
                System.out.println();
                TreeViewer.imprimeArvore(avlTree);
            } catch (IOException e) {
                System.out.println("Erro na Leitura!");
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Tente novamente!");
            }
        }

    }

}

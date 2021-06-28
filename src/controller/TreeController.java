/*
    Trabalho do Grau B - Arthur Mattos, Matheus Correa, Thiago Thomas
    Universidade do Vale do Rio do Sinos
 */
package controller;

import model.AVLTree;
import model.Dado;
import model.Pessoa;
import view.TreeViewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TreeController {

    public static void main(String[] args) throws IOException, ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        AVLTree<Long> avlCpfTree = new AVLTree<>();
        AVLTree<Date> avlDateTree = new AVLTree<>();
        AVLTree<String> avlNameTree = new AVLTree<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("DIGITE O ARQUIVO EM FORMATO CSV QUE DESEJA PROCESSAR: ");
        String fileName = scanner.nextLine();
        System.out.println();

        List<String> registers = leArquivo(fileName);

        Pessoa[] pessoas = criaArrayPessoas(registers);

        for(int i=0; i<pessoas.length; i++) {
            avlCpfTree.inserir(new Dado<>(pessoas[i].getCpf(), i));
            avlDateTree.inserir(new Dado<>(pessoas[i].getDataNascimento(), i));
            avlNameTree.inserir(new Dado<>(pessoas[i].getNome(), i));
        }

        try(InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(is)) {
            int opcao;
            List<Integer> indices;
            flag:
            while(true) {
                try {
                    indices = new ArrayList<>();
                    TreeViewer.imprimeOpcoes();
                    System.out.print("* Digite o numero da opção aqui: ");
                    opcao = Integer.parseInt(in.readLine());
                    System.out.println();
                    switch (opcao) {
                        case 1:
                            System.out.print("* Digite o CPF que deseja pesquisar: ");
                            long cpf = Long.parseLong(in.readLine());
                            System.out.println();
                            indices = avlCpfTree.getIndexCPF(avlCpfTree.getRaiz(), cpf);
                            break;
                        case 2:
                            System.out.print("* Digite o inicio do nome que deseja pesquisar: ");
                            String nome = in.readLine();
                            System.out.println();
                            indices = avlNameTree.getIndexesFromNamesStartingWith(avlNameTree.getRaiz(), nome, indices);
                            break;
                        case 3:
                            System.out.print("* Digite a primeira data do intervalo (não inclusivo): ");
                            Date dataInicial = formato.parse(in.readLine());
                            System.out.println();
                            System.out.print("* Digite a segunda data do intervalo (não inclusivo): ");
                            Date dataFinal = formato.parse(in.readLine());
                            System.out.println();
                            indices = avlDateTree.getIndexesFromDateInterval(avlDateTree.getRaiz(), dataInicial, dataFinal, indices);
                            break;
                        case 4:
                            System.out.println("######################## FINALIZANDO PROGRAMA ########################");
                            break flag;
                        default:
                            System.out.println("* OPCAO INVALIDA. TENTE NOVAMENTE!");
                            break;
                    }
                    TreeViewer.imprimeDados(indices, pessoas);
                } catch (NumberFormatException e) {
                    System.out.println("* OPÇÃO INVALIDA. TENTE NOVAMENTE!");
                } catch (ParseException e) {
                    System.out.println("* DATA INVALIDA. TENTE NOVAMENTE!");
                }
            }
        }

    }

    public static List<String> leArquivo(String fileName) {
        List<String> registers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty()) registers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registers;
    }

    public static Pessoa[] criaArrayPessoas(List<String> registers) throws ParseException {
        //como rg e cpf sao dados unicos por usuarios eles nao podem se repetir, entao neste método e feita tal validação
        List<String> cpfs = new ArrayList<>();
        List<String> rgs = new ArrayList<>();
        List<Pessoa> pessoas = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        long cpf;
        long rg;
        String nome;
        Date dataNascimento;
        String cidadeNascimento;
        String[] linha;

        for(String r : registers) {
            linha = r.split(";");
            cpf = Long.parseLong(linha[0]);
            rg = Long.parseLong(linha[1]);

            if(cpfs.contains(String.valueOf(cpf)) || rgs.contains(String.valueOf(rg))) { // validação para ver se existe cpf ou rg
                continue;
            } else { // adiciona na lista de validacao
                cpfs.add(String.valueOf(cpf));
                rgs.add(String.valueOf(rg));
            }

            nome = linha[2];
            dataNascimento = formato.parse(linha[3]);
            cidadeNascimento = linha[4];
            pessoas.add(new Pessoa(cpf, rg, nome, dataNascimento, cidadeNascimento));
        }

        return pessoas.toArray(new Pessoa[0]); // retorna o array de pessoas
    }
}

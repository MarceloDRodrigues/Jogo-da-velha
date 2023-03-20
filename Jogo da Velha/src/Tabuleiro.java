import java.util.*;

public class Tabuleiro {

    public static void inicioTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public static void jogar(char[][] tabuleiro, Jogador jogadorAtual) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                jogadorAtual.getNome() + ", digite a linha a qual voce deseja preencher com "
                        + jogadorAtual.getSimbolo());

        int linha = scanner.nextInt() - 1;
        System.out.println(
                jogadorAtual.getNome() + ", digite a coluna a qual voce deseja preencher com "
                        + jogadorAtual.getSimbolo());

        int coluna = scanner.nextInt() - 1;
        System.out.println("Foram obtidos: " + (linha + 1) + "," + (coluna + 1));
        try {
            if (tabuleiro[linha][coluna] == ' ') {
                tabuleiro[linha][coluna] = jogadorAtual.getSimbolo();
            } else {
                System.out.println("Posicão já ocupada, tente novamente.");
                jogar(tabuleiro, jogadorAtual);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Número de linhas ou colunas inválido, respeite o limite de: " + tabuleiro.length);
            System.out.println("Tente novamente.");
            jogar(tabuleiro, jogadorAtual);
        }
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("Tabuleiro:");

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean verificaVencedor(char[][] tabuleiro, Jogador jogador) {
        boolean vencer = false;

        // verifica linhas e colunas
        for (int i = 0; i < tabuleiro.length; i++) {
            if ((tabuleiro[i][0] == jogador.getSimbolo() && tabuleiro[i][1] == jogador.getSimbolo()
                    && tabuleiro[i][2] == jogador.getSimbolo())) {

                vencer = true;
            }
            if ((tabuleiro[0][i] == jogador.getSimbolo() && tabuleiro[1][i] == jogador.getSimbolo()
                    && tabuleiro[2][i] == jogador.getSimbolo())) {

                vencer = true;
            }
        }

        // verifica diagonais
        if (!vencer) {
            if (tabuleiro[0][0] == jogador.getSimbolo() && tabuleiro[1][1] == jogador.getSimbolo()
                    && tabuleiro[2][2] == jogador.getSimbolo()) {
                vencer = true;

            } else if (tabuleiro[0][2] == jogador.getSimbolo() && tabuleiro[1][1] == jogador.getSimbolo()
                    && tabuleiro[2][0] == jogador.getSimbolo()) {
                vencer = true;

            }
        }

        return vencer;

    }

    public static void jogoCompleto(char[][] tabuleiro, Jogador jogador1, Jogador jogador2) {
        Jogador atual = null;

        int jogadas = 0;
        inicioTabuleiro(tabuleiro);
        exibirTabuleiro(tabuleiro);
        while (true) {

            atual = jogador1;
            jogar(tabuleiro, atual);
            if (verificaVencedor(tabuleiro, atual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println(
                        "O jogador " + atual.getNome() + " venceu. ");
                break;
            }
            exibirTabuleiro(tabuleiro);
            jogadas += 1;
            if (jogadas == 9) {
                System.out.println("Número limite de jogadas atingido.");
                System.out.println("Empate.");
                break;
            }
            atual = jogador2;
            jogar(tabuleiro, atual);
            if (verificaVencedor(tabuleiro, atual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println(
                        "O jogador " + atual.getNome() + " venceu .");

                break;
            }
            jogadas += 1;

            exibirTabuleiro(tabuleiro);

        }
    }

    public static void main(String[] args) {
        char[][] tabuleiro = new char[3][3];
        Jogador Marcelo = new Jogador("Marcelo", 'X');
        Jogador Ismaylla = new Jogador("Ismaylla", 'O');
        jogoCompleto(tabuleiro, Marcelo, Ismaylla);
    }
}

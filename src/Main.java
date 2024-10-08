import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        int opcao;

        while (true) {
            try {
                System.out.println("######################");
                System.out.println("####### AGENDA #######");
                System.out.println("######################");
                System.out.println(">>>> Menu Contato <<<<");
                System.out.println("1 - Adicionar Contato");
                System.out.println("2 - Detalhar Contato");
                System.out.println("3 - Editar Contato");
                System.out.println("4 - Remover Contato");
                System.out.println("5 - Listar Contatos");
                System.out.println("6 - Acessar LinkedIn");
                System.out.println("7 - Acessar GitHub");
                System.out.println("8 - Sair");
                System.out.print("Escolha uma opção: ");

                try{
                    opcao = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("\nOpção em branco! Escolha uma opção válida.\n");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        agenda.adicionarContato();
                        break;
                    case 2:
                        agenda.detalharContato();
                        break;
                    case 3:
                        agenda.editarContato();
                        break;
                    case 4:
                        agenda.removerContato();
                        break;
                    case 5:
                        agenda.listarContatos();
                        break;
                    case 6:
                        agenda.acessarLinkedIn();
                        break;
                    case 7:
                        agenda.acessarGitHub();
                        break;
                    case 8:
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("\nOpção inválida. Tente novamente.");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

    }
}
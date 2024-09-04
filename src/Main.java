import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();

        while (true){
            try{
                System.out.println("##################");
                System.out.println("##### AGENDA #####");
                System.out.println("##################");
                System.out.println(">>>> Menu Contato <<<<");
                System.out.println("1 - Adicionar Contato");
                System.out.println("2 - Detalhar Contato");
                System.out.println("3 - Editar Contato");
                System.out.println("4 - Remover Contato");
                System.out.println("5 - Listar Contatos");
                System.out.println("6 - Acessar LinkedIn");
                System.out.println("7 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao){
                    case 1: //adicionar contato
                        agenda.adicionarContato(sc);
                        break;
                    case 2: //detalhar contato
                        if (agenda.buscarContato(sc) == null){
                            System.out.println("Contato não encontrado!");
                        }else{
                            agenda.buscarContato(sc).mostarDetalhes();
                        }
                        break;
                    case 3: //editar contato
                        break;
                    case 4: //remover contato
                        break;
                    case 5: //listar contatos
                        agenda.listarContatos();
                        break;
                    case 6: //acessar linkedin
                        agenda.acessarLinkedIn(sc);
                        break;
                    case 7: //sair
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            }catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
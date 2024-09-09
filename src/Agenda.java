import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    ArrayList<Contato> contatos = new ArrayList<>();
    //mudei aqui pra private
    private int idContato = 0;
    private Scanner scanner = new Scanner(System.in);

    public void adicionarContato() {
        idContato++;
        System.out.println("Informe o nome do contato:");
        String nome = scanner.nextLine();
        System.out.println("Informe o telefone do contato (apenas números):");
        String telefone = leNumeroContato();
        if (telefone.isEmpty()) {
            idContato--;
            return;
        }
        System.out.println("Informe o e-mail do contato:");
        String email = scanner.nextLine();
        System.out.println("Informe o LinkdIn do contato:");
        String url = scanner.nextLine();

        for (Contato c : contatos) {
            if (c.getTelefoneContato().trim().equals(telefone)) {
                System.out.println("Telefone já cadastrado!");
                idContato--;
                return;
            }
        }

        if (url.isBlank()) {
            Contato contato = new Contato(idContato, nome, telefone, email);
            contatos.add(contato);
        } else {
            ContatoComLinkedIn contatoComLinkedIn = new ContatoComLinkedIn(idContato, nome, telefone, email, new LinkedIn(url));
            contatos.add(contatoComLinkedIn);
        }
    }

    public Contato buscarContato() {
        String telefone = leNumeroContato();
        for (Contato c : contatos) {
            if (c.getTelefoneContato().trim().equals(telefone.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return contatos.isEmpty();
    }

    public void detalharContato() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        System.out.println("Informe o número do contato que deseja buscar:");
        Contato contato = buscarContato();
        if (contato == null) {
            System.out.println("Contato não encontrado!");
        } else {
            contato.mostrarDetalhes();
        }
    }

    public void editarContato() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        System.out.println("Informe o número do contato que deseja editar:");
        Contato contato = buscarContato();
        if (contato == null) {
            System.out.println("Contato não encontrado!");
        } else {
            System.out.println("Informe o nome do contato:");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                contato.setNomeContato(nome);
            }
            System.out.println("Informe o telefone do contato (apenas números):");
            String telefone = leNumeroContato();
            if (!telefone.isEmpty()) {
                //tem que trasformar isso aqui em uma função eu acho
                for (Contato c : contatos) {
                    if (c.getTelefoneContato().trim().equals(telefone) && c.getIdContato() != contato.getIdContato()) {
                        System.out.println("Telefone já cadastrado!");
                        return;
                    }
                }
                contato.setTelefoneContato(telefone);
            }
            System.out.println("Informe o e-mail do contato:");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                contato.setEmailContato(email);
            }
            if (contato instanceof ContatoComLinkedIn contatoComLinkedIn) {
                System.out.println("Informe o LinkdIn do contato:");
                String url = scanner.nextLine();
                if (!url.isEmpty()) {
                    contatoComLinkedIn.setLinkedIn(new LinkedIn(url));
                }
            }
        }
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        //TODO config layout de exibição
        System.out.println("\n>>>> Contatos <<<<");
        System.out.println("Id | Nome | Telefone | E-mail | LinkedIn");
        for (Contato c : contatos) {
            System.out.println(c);
        }
    }

    public void removerContato() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        System.out.println("Informe o número do contato que deseja remover:");
        Contato c = buscarContato();
        if (c != null) {
            contatos.remove(c);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado!");
        }
    }

    public void acessarLinkedIn() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        System.out.println("Informe o número do contato que deseja acessar LinkedIn:");
        Contato c = buscarContato();
        if (c == null) {
            System.out.println("Contato não encontrado!");
            return;
        }

        if (c instanceof ContatoComLinkedIn contatoComLinkedIn) {
            try {
                System.out.println("Abrindo LinkedIn...");
                Thread.sleep(2000);
                String url = "https://www.linkedin.com/in/" + contatoComLinkedIn.getLinkedIn().getSlugProfile();
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Contato não possui linkedin!");
        }
    }

    private String leNumeroContato() {
        //verificação de número de caracteres e dígitos e remoção de espaço em branco
        String telefone = "";
        while (true) {
            telefone = scanner.nextLine();
            telefone = telefone.replaceAll("\\s", "");
            if (telefone.isEmpty())
                return telefone;
            else if (telefone.length() == 11 && telefone.matches("\\d{11}")) {
                return telefone;
            } else {
                System.out.println("Telefone inválido! Digite um telefone válido:");
            }
        }
    }
}

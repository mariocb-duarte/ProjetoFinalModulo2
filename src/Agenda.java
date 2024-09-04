import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    ArrayList<Contato> contatos = new ArrayList<>();
    int idContato = 0;

    public void adicionarContato(Scanner scanner) {
        idContato++;
        String nome = scanner.nextLine();
        String telefone = scanner.nextLine();
        String email = scanner.nextLine();
        String url = scanner.nextLine();
        ContatoComLinkedIn contato = new ContatoComLinkedIn(idContato, nome, telefone, email, new LinkedIn(url));
        for (Contato c : contatos) {
            if (c.getTelefoneContato().trim().equals(contato.getTelefoneContato().trim())) {
                System.out.println("Telefone já cadastrado!");
                idContato--;
                return;
            }
        }
        contatos.add(contato);
    }

    public Contato buscarContato(Scanner scanner) {
        String telefone = scanner.nextLine();
        for (Contato c : contatos) {
            if (c.getTelefoneContato().trim().equals(telefone.trim())) {
                return c;
            }
        }
        return null;
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.\n");
            return;
        }
        System.out.println(">>>> Contatos <<<<");
        System.out.println("Id | Nome | Telefone | E-mail | LinkedIn");
        for (Contato c : contatos) {
            System.out.println(c);
        }
    }

    public void removerContato(Scanner scanner) {
        Contato c = buscarContato(scanner);
        if (c != null) {
            contatos.remove(c);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado!");
        }
    }

    public void acessarLinkedIn(Scanner scanner) {
        System.out.println("Digite o telefone: ");
        Contato c = buscarContato(scanner);
        if (c == null) {
            System.out.println("Contato não encontrado!");
            return;
        }
        if (c instanceof ContatoComLinkedIn contatoComLinkedIn) {
            try {
                System.out.println("Abrindo LinkedIN!");
                Thread.sleep(2000);
                Desktop.getDesktop().browse(new URI(contatoComLinkedIn.getLinkedIn().toString()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Contato não possui linkedin!");
        }
    }
}

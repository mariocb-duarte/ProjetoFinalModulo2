import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    ArrayList<Contato> contatos = new ArrayList<>();
    private int idContato = 0;
    private Scanner scanner = new Scanner(System.in);

    public void adicionarContato() {
        idContato++;
        String nome = lerEntrada("Informe o nome do contato: ");
        String telefone = lerNumeroContato();
        if (telefone.isBlank()) {
            idContato--;
            return;
        }
        String email = lerEntrada("Informe o email do contato: ");
        String urlLinkedIn = lerEntrada("Informe o url do LinkedIn do contato: ");

        if (isTelefoneDuplicado(telefone)) {
            System.out.println("Telefone já cadastrado!");
            idContato--;
            return;
        }

        Contato contato = urlLinkedIn.isBlank()
                ? new Contato(idContato, nome, telefone, email)
                : new ContatoComLinkedIn(idContato, nome, telefone, email, new LinkedIn(urlLinkedIn));

        contatos.add(contato);
    }

    public void detalharContato() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        Contato contato = buscarContatoPorTelefone();
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

        Contato contato = buscarContatoPorTelefone();
        if (contato == null) {
            System.out.println("Contato não encontrado!");
            return;
        }

        String nome = lerEntrada("Informe o nome do contato: ");
        if (!nome.isBlank()) {
            contato.setNomeContato(nome);
        }

        String telefone = lerNumeroContato();
        if (!telefone.isBlank() && !isTelefoneDuplicado(telefone, contato.getIdContato())) {
            contato.setTelefoneContato(telefone);
        }

        String email = lerEntrada("Informe o email do contato: ");
        if (!email.isBlank()) {
            contato.setEmailContato(email);
        }

        if (contato instanceof ContatoComLinkedIn contatoComLinkedIn) {
            String urlLinkedIn = lerEntrada("Informe o url do LinkedIn do contato: ");
            if (!urlLinkedIn.isBlank()) {
                contatoComLinkedIn.setLinkedIn(new LinkedIn(urlLinkedIn));
            }
        }
    }

    public void listarContatos() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        System.out.println("\n>>>> Contatos <<<<");
        System.out.println("Id | Nome | Telefone | E-mail | LinkedIn");
        contatos.forEach(System.out::println);
    }

    public void removerContato() {
        if (isEmpty()) {
            System.out.println("\nAgenda ainda não possui contato adicionado.");
            return;
        }
        Contato contato = buscarContatoPorTelefone();
        if (contato != null) {
            contatos.remove(contato);
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

        Contato contato = buscarContatoPorTelefone();
        if (contato == null) {
            System.out.println("Contato não encontrado!");
            return;
        }

        if (contato instanceof ContatoComLinkedIn contatoComLinkedIn) {
            abrirLinkedIn(contatoComLinkedIn);
        } else {
            System.out.println("Contato não possui linkedin!");
        }
    }

    private String lerNumeroContato() {
        while (true) {
            String telefone = scanner.nextLine().replaceAll("\\s", "");
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

    private String lerEntrada(String mensagem){
        System.out.println(mensagem);
        return scanner.nextLine();
    }

    private boolean isTelefoneDuplicado(String telefone) {
        return contatos.stream().anyMatch(c -> c.getTelefoneContato().equals(telefone));
    }

    private boolean isTelefoneDuplicado(String telefone, int idAtual){
        return contatos.stream().anyMatch(c -> c.getTelefoneContato().equals(telefone) && c.getIdContato() != idAtual);
    }

    public Contato buscarContatoPorTelefone() {
        String telefone = lerNumeroContato();
        return contatos.stream()
                .filter(c -> c.getTelefoneContato().equals(telefone))
                .findFirst()
                .orElse(null);
    }

    private void abrirLinkedIn(ContatoComLinkedIn contatoComLinkedIn) {
        try{
            System.out.println("Abrindo LinkedIn...");
            Thread.sleep(1500);
            String urlLinkedIn = "https://www.linkedin.com/in/" + contatoComLinkedIn.getLinkedIn().getSlugProfile();
            Desktop.getDesktop().browse(new URI(urlLinkedIn));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao abris LinkedIn. ", e);
        }
    }

    public boolean isEmpty() {
        return contatos.isEmpty();
    }
}

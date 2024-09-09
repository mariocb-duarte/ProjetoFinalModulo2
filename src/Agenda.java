import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    ArrayList<Contato> contatos = new ArrayList<>();
    private int idContato = 0;
    private Scanner scanner = new Scanner(System.in);

    public void adicionarContato() {
        String nome = lerNome();
        if (nome.isBlank()) return;

        String telefone = lerNumeroContato();
        if (telefone.isBlank()) {
            return;
        }
        if (isTelefoneDuplicado(telefone)) {
            System.out.println("Telefone já cadastrado!");
            return;
        }

        String email = lerEmail();
        String urlLinkedIn = lerLinkedIn();

        Contato contato = urlLinkedIn.isBlank()
                ? new Contato(nome, telefone, email)
                : new ContatoComLinkedIn(nome, telefone, email, new LinkedIn(urlLinkedIn));

        contatos.add(contato);
        for (int i = 0; i < contatos.size();i++){
            if (contatos.get(i).equals(contato)){
                contato.setIdContato(i+1);
                break;
            }
        }
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
            for (int i = 0; i < contatos.size(); i++) {
                contatos.get(i).setIdContato(i+1);
            }
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
            System.out.println("Informe o telefone do contato ou ENTER para cancelar: ");
            String telefone = scanner.nextLine().replaceAll("\\s", "");
            telefone = telefone.replaceAll("\\s", "");
            if (telefone.isBlank())
                return telefone;
            else if (telefone.length() == 11 && telefone.matches("\\d{11}")) {
                return telefone;
            } else {
                System.out.println("Telefone inválido!");
            }
        }
    }

    private String lerEntrada(String mensagem){
        System.out.println(mensagem);
        return scanner.nextLine();
    }

    private String lerNome(){
        System.out.println("Informe o nome do Contato (20 characters) ou ENTER para cancelar: ");
        String name = scanner.nextLine();
        if(name.length()>20)
            return name.substring(0, 20);
        return name;
    }

    private String lerEmail(){
        //E-mail optativo
        System.out.println("Informe o e-mail do Contato ou ENTER para continuar sem  e-mail: ");
        String email = scanner.nextLine();
        if (email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || email.isBlank()) {
            return email;
        }
        System.out.println("Email inválido. Por favor, digite um email válido.");
        return lerEmail();
    }

    private String lerLinkedIn(){
        //LinkedIn optativo
        System.out.println("Informe o url do LinkedIn do Contato ou ENTER para continuar sem: ");
        String urlLinkedIn = scanner.nextLine();
        if (urlLinkedIn.matches("^(https?:\\/\\/)?(www\\.)?linkedin\\.com\\/.*$") || urlLinkedIn.isBlank()) {
            return urlLinkedIn;
        }
        System.out.println("Linkedin inválido. Por favor, digite um url de LinkedIn válido.");
        return lerLinkedIn();
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

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    ArrayList<Contato> contatos = new ArrayList<>();
    //mudei aqui pra private
    private int idContato = 0;

    public void adicionarContato(Scanner scanner) {
        idContato++;
        System.out.println("Informe o nome do contato:");
        String nome = scanner.nextLine();
        System.out.println("Informe o telefone do contato (apenas números):");
        String telefone = leNumeroContato(scanner);
        if(telefone.isEmpty()){
            idContato--;
            return;
        }
        System.out.println("Informe o e-mail do contato:");
        String email = scanner.nextLine();
        System.out.println("Informe o LinkdIn do contat:");
        String url = scanner.nextLine();
        //acho que esse new LinkedIn ficou meio estranho, não sei se isso é usual
        //aqui eu não sei se o ideal seria chamar o outro construtur se não fosse informado LinkedIn
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
        System.out.println("Informe o número do contato que deseja buscar:");
        String telefone = leNumeroContato(scanner);
        for (Contato c : contatos) {
            if (c.getTelefoneContato().trim().equals(telefone.trim())) {
                return c;
            }
        }
        return null;
    }

    public void detalharContato(Scanner scanner){
        Contato contato = buscarContato(scanner);
        if (contato == null){
            System.out.println("Contato não encontrado!");
        }else{
            contato.mostrarDetalhes();
        }
    }

    public void editarContato(Scanner scanner) {
        Contato contato = buscarContato(scanner);
        if (contato == null){
            System.out.println("Contato não encontrado!");
        }else{
            System.out.println("Informe o nome do contato:");
            String nome = scanner.nextLine();
            if(!nome.isEmpty()){
                contato.setNomeContato(nome);
            }
            System.out.println("Informe o telefone do contato (apenas números):");
            String telefone = leNumeroContato(scanner);
            if(!telefone.isEmpty()){
                //tem que trasformar isso aqui em uma função eu acho
                for (Contato c : contatos) {
                    if (c.getTelefoneContato().trim().equals(contato.getTelefoneContato().trim()) && c.getIdContato() != contato.getIdContato()) {
                        System.out.println("Telefone já cadastrado!");
                        return;
                    }
                }
                contato.setTelefoneContato(telefone);
            }
            System.out.println("Informe o e-mail do contato:");
            String email = scanner.nextLine();
            if(!email.isEmpty()){
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
        //aqui tem que avalir isso porque parece só estar sendo criado contato com linkedin
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

    private String leNumeroContato(Scanner scanner) {
        //verificação de número de caracteres e dígitos e remoção de espaço em branco
        String telefone = "";
        while(true){
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

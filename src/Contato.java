public class Contato {
    private int idContato;
    private String nomeContato;
    private String telefoneContato;
    private String emailContato;

    public Contato(String nomeContato, String telefoneContato, String emailContato) {
        this.nomeContato = nomeContato;
        this.telefoneContato = telefoneContato;
        this.emailContato = emailContato;
    }

    public void setIdContato(int idContato){
        this.idContato = idContato;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public void mostrarDetalhes() {
        System.out.println("\nId: " + idContato);
        System.out.println("Nome: " + nomeContato);
        System.out.println("Telefone: " + telefoneContato);
        System.out.println("Email: " + emailContato);
    }

    @Override
    public String toString(){
        return idContato + " | " + nomeContato + " | " + telefoneContato + " | " + emailContato + " | ";
    }
}

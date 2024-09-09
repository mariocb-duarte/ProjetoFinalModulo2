public class ContatoComGitHub extends Contato {
    private GitHub gitHub;

    public ContatoComGitHub(String nomeContato, String telefoneContato, String emailContato, GitHub gitHub) {
        super(nomeContato, telefoneContato, emailContato);
        this.gitHub = gitHub;
    }

    public GitHub getGitHub() {
        return gitHub;
    }

    public void setGitHub(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("GitHub: " + (gitHub != null ? gitHub : "Não disponível"));
    }

    @Override
    public String toString() {
        return super.toString() + gitHub.toString();
    }



}

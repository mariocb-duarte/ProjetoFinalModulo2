public class ContatoComLinkedIn extends Contato {
    private LinkedIn linkedIn;

    public ContatoComLinkedIn(int idContato, String nomeContato, String telefoneContato, String emailContato, LinkedIn linkedIn) {
        super(idContato, nomeContato, telefoneContato, emailContato);
        this.linkedIn = linkedIn;
    }

    public LinkedIn getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(LinkedIn linkedIn) {
        this.linkedIn = linkedIn;
    }

    @Override
    public void mostarDetalhes() {
        super.mostarDetalhes();
        System.out.println("Linkedin: " + linkedIn);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + linkedIn.toString();
    }
}

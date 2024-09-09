public class ContatoComLinkedIn extends Contato {
    private LinkedIn linkedIn;

    public ContatoComLinkedIn(String nomeContato, String telefoneContato, String emailContato, LinkedIn linkedIn) {
        super(nomeContato, telefoneContato, emailContato);
        this.linkedIn = linkedIn;
    }

    public LinkedIn getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(LinkedIn linkedIn) {
        this.linkedIn = linkedIn;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Linkedin: " + linkedIn);
    }

    @Override
    public String toString() {
        return super.toString() + linkedIn.toString();
    }
}

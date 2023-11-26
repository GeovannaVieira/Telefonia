public class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    protected Chamada[] chamadas;
    protected int numChamadas;

    // Construtor para inicializar os atributos e instanciar o vetor de chamadas
    public Assinante(long cpf, String nome, int numero, int maxChamadas) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.chamadas = new Chamada[maxChamadas];
        this.numChamadas = 0;
    }

    // Método para obter o CPF do assinante
    public long getCpf() {
        return cpf;
    }

    // Método para obter uma representação textual dos atributos do assinante
    @Override
    public String toString() {
        return "CPF: " + cpf + "\nNome: " + nome + "\nNúmero: " + numero;
    }
}

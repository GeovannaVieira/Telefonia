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

    // Método para obter o nome do assinante
    public String getNome() {
        return nome;
    }

    // Método para obter o número do assinante
    public int getNumero() {
        return numero;
    }

    // Método para adicionar uma chamada ao vetor de chamadas
    public void adicionarChamada(Chamada chamada) {
        if (numChamadas < chamadas.length) {
            chamadas[numChamadas] = chamada;
            numChamadas++;
        } else {
            System.out.println("Erro: Limite de chamadas atingido.");
        }
    }

    // Método para obter uma representação textual dos atributos do assinante
    @Override
    public String toString() {
        return "CPF: " + cpf + "\nNome: " + nome + "\nNúmero: " + numero;
    }

    // Método para obter o valor total das chamadas
    public float getValorTotalChamadas() {
        float total = 0;
        for (Chamada chamada : chamadas) {
            if (chamada != null) {
                total += chamada.getValor();
            }
        }
        return total;
    }
}


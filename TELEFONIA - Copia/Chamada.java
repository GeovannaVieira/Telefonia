import java.util.GregorianCalendar;

public class Chamada {
    private GregorianCalendar data; // Data da chamada
    private int duracao; // Duração da chamada em minutos
    private String tipo; // Tipo de chamada (por exemplo, "Pós-pago")
    private float valor; // Valor da chamada

    // Construtor para inicializar os atributos
    public Chamada(String tipo, long cpf, GregorianCalendar data, int duracao, float valor) {
        // Verifica se a data e a duração são válidas antes de atribuir
        if (data != null && duracao > 0) {
            this.tipo = tipo;
            this.data = data;
            this.duracao = duracao;
            this.valor = valor;
        } else {
            // Se não forem, você pode lançar uma exceção ou lidar com o erro de outra forma apropriada
            throw new IllegalArgumentException("Erro: A data ou duração da chamada é inválida.");
        }
    }

    // Método para obter a data da chamada
    public GregorianCalendar getData() {
        return data;
    }

    // Método para obter a duração da chamada
    public int getDuracao() {
        return duracao;
    }

    // Método para obter o valor da chamada
    public float getValor() {
        return valor;
    }

    // Método para obter uma representação textual dos atributos da classe
    @Override
    public String toString() {
        return "Data: " + data.getTime() + ", Duração: " + duracao + " minutos, Valor: R$" + valor;
    }
}

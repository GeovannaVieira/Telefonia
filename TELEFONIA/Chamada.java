import java.text.SimpleDateFormat;
import java.util.Date;

public class Chamada {
    private Date data; // Data da chamada
    private int duracao; // Duração da chamada em minutos

    // Construtor para inicializar os atributos
    public Chamada(Date data, int duracao) {
        // Verifica se a data e a duração são válidas antes de atribuir
        if (data != null && duracao > 0) {
            this.data = data;
            this.duracao = duracao;
        } else {
            // Se não forem, emite uma mensagem de erro
            System.out.println("Erro: A data ou duração da chamada é inválida.");
        }
    }

    // Método para obter a data da chamada
    public Date getData() {
        return data;
    }

    // Método para obter a duração da chamada
    public int getDuracao() {
        return duracao;
    }

    // Método para obter uma representação textual dos atributos da classe
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(data);
        return "Data: " + formattedDate + ", Duração: " + duracao + " minutos";
    }
}

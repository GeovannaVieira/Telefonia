import java.util.GregorianCalendar;

public class PosPago extends Assinante {
    private float assinatura; // Valor de assinatura 

    // Construtor para inicializar os atributos
    public PosPago(long cpf, String nome, int numero, float assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    // Método para fazer uma chamada e registrar no vetor de chamadas
    public void fazerChamada(GregorianCalendar data, int duracao) {
        float custoChamada = 1.04f * duracao;
        if (numChamadas < chamadas.length) {
            Chamada chamada = new Chamada("Pós-pago", getCpf(), data, duracao, custoChamada);
            chamadas[numChamadas] = chamada;
            numChamadas++;
            System.out.println("Chamada realizada com sucesso! Custo: R$" + custoChamada);
        } else {
            System.out.println("Chamada não pode ser realizada. Limite de chamadas atingido.");
        }
    }

    // Método para imprimir a fatura do pós-pago para um determinado mês
    public void imprimirFatura(int mes) {
        float totalChamadas = 0;

        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Número: " + getNumero());
        System.out.println("Fatura do mês: " + mes);

        for (Chamada chamada : chamadas) {
            if (chamada != null && chamada.getData().get(GregorianCalendar.MONTH) + 1 == mes) {
                totalChamadas += chamada.getValor();
                System.out.println("Chamada - Data: " + chamada.getData().getTime() +
                        ", Duração: " + chamada.getDuracao() + " minutos, Valor: R$" + chamada.getValor());
            }
        }

        float totalFatura = assinatura + totalChamadas;
        System.out.println("Total da fatura: R$" + totalFatura);
    }
}

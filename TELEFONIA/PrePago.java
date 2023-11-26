import java.util.GregorianCalendar;

public class PrePago extends Assinante {
    private Recarga[] recargas;
    private int numRecargas;
    private float creditos;

    // Construtor para inicializar os atributos e instanciar o vetor de recargas
    public PrePago(String cpf, String nome, int numero) {
        super(cpf, nome, numero);
        this.recargas = new Recarga[10];
        this.numRecargas = 0;
        this.creditos = 0.0f;
    }

    // Método para fazer uma chamada e registrar no vetor de chamadas
    public void fazerChamada(GregorianCalendar data, int duracao) throws CreditoInsuficienteException, LimiteChamadasAtingidoException {
        float custoChamada = 1.45f * duracao;
        if (numChamadas < chamadas.length && creditos >= custoChamada) {
            Chamada chamada = new Chamada("Pré-pago", getCpf(), data, duracao, custoChamada);
            adicionarChamada(chamada);
            creditos -= custoChamada;
            System.out.println("Chamada realizada com sucesso! Custo: R$" + custoChamada);
        } else {
            if (numChamadas >= chamadas.length) {
                throw new LimiteChamadasAtingidoException("Limite de chamadas atingido.");
            } else {
                throw new CreditoInsuficienteException("Crédito insuficiente para realizar a chamada. Créditos disponíveis: R$" + creditos);
            }
        }
    }

   // Método para registrar uma recarga e adicionar créditos
public void recarregar(GregorianCalendar data, float valor) {
    // Verifica se há espaço no vetor de recargas
    if (numRecargas < recargas.length) {
        // Adiciona a recarga ao vetor de recargas
        recargas[numRecargas++] = new Recarga(data, valor);
        creditos += valor;

        System.out.println("Recarga realizada com sucesso! Valor: R$" + valor);
    } else {
        System.out.println("Limite de recargas atingido. Não é possível recarregar mais créditos.");
    }
}



    // Método para imprimir a fatura do pré-pago para um determinado mês
    public void imprimirFatura(int mes) {
        float totalChamadas = 0;
        float totalRecargas = 0;

        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Número: " + getNumero());
        System.out.println("Fatura do mês: " + mes);

        for (Chamada chamada : chamadas) {
            if (chamada != null && chamada.getData() != null &&
                    chamada.getData().get(GregorianCalendar.MONTH) == mes) {
                totalChamadas += chamada.getValor();
                System.out.println("Chamada - Data: " + chamada.getData().getTime() +
                        ", Duração: " + chamada.getDuracao() + " minutos, Valor: R$" + chamada.getValor());
            }
        }

        for (Recarga recarga : recargas) {
            if (recarga != null && recarga.getData() != null &&
                    recarga.getData().get(GregorianCalendar.MONTH) == mes) {
                totalRecargas += recarga.getValor();
                System.out.println("Recarga - Data: " + recarga.getData().getTime() +
                        ", Valor: R$" + recarga.getValor());
            }
        }

        System.out.println("Total de chamadas: R$" + totalChamadas);
        System.out.println("Total de recargas: R$" + totalRecargas);
        System.out.println("Créditos disponíveis: R$" + creditos);
    }
}
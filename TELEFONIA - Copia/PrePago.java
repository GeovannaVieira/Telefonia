import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class PrePago extends Assinante {
    private Recarga[] recargas;
    private int numRecargas;
    private float creditos;

    // Construtor para inicializar os atributos e instanciar o vetor de recargas
    public PrePago(long cpf, String nome, int numero) {
        super(cpf, nome, numero, 100); // Substitua 100 pelo tamanho desejado para o vetor de chamadas
        this.recargas = new Recarga[10];
        this.numRecargas = 0;
        this.creditos = 0.0f;
    }

    // Métodos ausentes da classe base Assinante
    // Não é necessário redefinir getCpf(), getNome() e getNumero, pois já são fornecidos pela classe base.

    // Método para fazer uma chamada e registrar no vetor de chamadas
    public void fazerChamada(GregorianCalendar data, int duracao)
            throws CreditoInsuficienteException, LimiteChamadasAtingidoException {
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
                throw new CreditoInsuficienteException(
                        "Crédito insuficiente para realizar a chamada. Créditos disponíveis: R$" + creditos);
            }
        }
    }

    // Método para registrar uma recarga e adicionar créditos
    public void recarregar(GregorianCalendar data, float valor) throws CreditoInsuficienteException {
        if (numRecargas < recargas.length) {
            recargas[numRecargas++] = new Recarga(data.getTime(), valor);
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
            if (chamada != null && chamada.getData() != null) {
                LocalDate chamadaDate = chamada.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (chamadaDate.getMonthValue() == mes) {
                    totalChamadas += chamada.getValor();
                    System.out.println("Chamada - Data: " + chamada.getData().getTime() +
                            ", Duração: " + chamada.getDuracao() + " minutos, Valor: R$" + chamada.getValor());
                }
            }
        }

        for (Recarga recarga : recargas) {
            if (recarga != null && recarga.getData() != null) {
                LocalDate recargaDate = recarga.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (recargaDate.getMonthValue() == mes) {
                    totalRecargas += recarga.getValor();
                    System.out.println("Recarga - Data: " + recarga.getData().getTime() +
                            ", Valor: R$" + recarga.getValor());
                }
            }
        }

        System.out.println("Total de chamadas: R$" + totalChamadas);
        System.out.println("Total de recargas: R$" + totalRecargas);
        System.out.println("Créditos disponíveis: R$" + creditos);
    }
}

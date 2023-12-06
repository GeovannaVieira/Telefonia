import java.util.Scanner;
import java.util.GregorianCalendar;

public class Telefonia {
    private PrePago[] prePagos;
    private int numPrePagos;
    private PosPago[] posPagos;
    private int numPosPagos;

    // Construtor
    public Telefonia(int maxPrePagos, int maxPosPagos) {
        this.prePagos = new PrePago[maxPrePagos];
        this.numPrePagos = 0;
        this.posPagos = new PosPago[maxPosPagos];
        this.numPosPagos = 0;
    }

    public void cadastrarAssinante() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tipo do assinante (1 para pré-pago, 2 para pós-pago):");
        int tipoAssinante;
        while (true) {
            try {
                tipoAssinante = Integer.parseInt(scanner.nextLine());
                if (tipoAssinante == 1 || tipoAssinante == 2) {
                    break;
                } else {
                    System.out.println("Opção inválida. Digite 1 para pré-pago ou 2 para pós-pago:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite 1 para pré-pago ou 2 para pós-pago:");
            }
        }

        System.out.println("Digite o CPF do assinante:");
        long cpf;
        while (true) {
            try {
                cpf = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("CPF inválido. Digite novamente:");
            }
        }

        System.out.println("Digite o nome do assinante:");
        String nome = scanner.nextLine();

        System.out.println("Digite o número de telefone do assinante:");
        String numero = scanner.nextLine();

        if (tipoAssinante == 1) {
            if (numPrePagos < prePagos.length) {
                prePagos[numPrePagos] = new PrePago(cpf, nome, Integer.parseInt(numero));
                numPrePagos++;
                System.out.println("Assinante pré-pago cadastrado com sucesso!");
            } else {
                System.out.println("Limite de assinantes pré-pagos atingido. Não é possível cadastrar mais assinantes.");
            }
        } else if (tipoAssinante == 2) {
            if (numPosPagos < posPagos.length) {
                System.out.println("Digite o valor da assinatura do assinante pós-pago:");
                float assinatura;
                while (true) {
                    try {
                        assinatura = Float.parseFloat(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido. Digite novamente:");
                    }
                }
                posPagos[numPosPagos] = new PosPago(cpf, nome, Integer.parseInt(numero), assinatura, 100);
                numPosPagos++;
                System.out.println("Assinante pós-pago cadastrado com sucesso!");
            } else {
                System.out.println("Limite de assinantes pós-pagos atingido. Não é possível cadastrar mais assinantes.");
            }
        } else {
            System.out.println("Tipo de assinante inválido. Por favor, tente novamente.");
        }
    }
    public void listarAssinantes() {
        if (numPrePagos == 0 && numPosPagos == 0) {
            System.out.println("Nenhum assinante cadastrado.");
        } else {
            System.out.println("Assinantes Pré-Pagos:");
            for (int i = 0; i < numPrePagos; i++) {
                System.out.println("CPF: " + prePagos[i].getCpf() + ", Nome: " + prePagos[i].getNome() + ", Número: " + prePagos[i].getNumero());
            }

            System.out.println("Assinantes Pós-Pagos:");
            for (int i = 0; i < numPosPagos; i++) {
                System.out.println("CPF: " + posPagos[i].getCpf() + ", Nome: " + posPagos[i].getNome() + ", Número: " + posPagos[i].getNumero());
            }
        }
    }

    public void fazerChamada() throws CreditoInsuficienteException, LimiteChamadasAtingidoException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione o tipo de assinante. Selecione 1 para pré-pago ou 2 para pós-pago):");
        int tipoAssinante;
        while (true) {
            try {
                tipoAssinante = Integer.parseInt(scanner.nextLine());
                if (tipoAssinante == 1 || tipoAssinante == 2) {
                    break;
                } else {
                    System.out.println("Opção inválida. Digite 1 para pré-pago ou 2 para pós-pago:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite 1 para pré-pago ou 2 para pós-pago:");
            }
        }

        System.out.println("Digite o CPF do assinante:");
        long cpf;
        while (true) {
            try {
                cpf = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("CPF inválido. Digite novamente:");
            }
        }

        boolean chamadaRealizada = false;
        if (tipoAssinante == 1) {
            PrePago assinantePrePago = localizarPrePago(cpf);
            if (assinantePrePago != null) {
                System.out.println("Digite a duração da chamada em minutos:");
                int duracao;
                while (true) {
                    try {
                        duracao = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Duração inválida. Digite novamente:");
                    }
                }
                assinantePrePago.fazerChamada(new GregorianCalendar(), duracao);
                chamadaRealizada = true;
            }
        } else if (tipoAssinante == 2) {
            PosPago assinantePosPago = localizarPosPago(cpf);
            if (assinantePosPago != null) {
                System.out.println("Digite a duração da chamada em minutos:");
                int duracao;
                while (true) {
                    try {
                        duracao = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Duração inválida. Digite novamente:");
                    }
                }
                assinantePosPago.fazerChamada(new GregorianCalendar(), duracao);
                chamadaRealizada = true;
            }
        }

        if (!chamadaRealizada) {
            System.out.println("Assinante não encontrado ou chamada não pôde ser realizada.");
        }
    }

    public void fazerRecarga() throws CreditoInsuficienteException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do assinante pré-pago para recarga:");
        long cpf;
        while (true) {
            try {
                cpf = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("CPF inválido. Digite novamente:");
            }
        }

        PrePago assinantePrePago = localizarPrePago(cpf);
        if (assinantePrePago != null) {
            System.out.println("Digite o valor da recarga:");
            float valor;
            while (true) {
                try {
                    valor = Float.parseFloat(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Digite novamente:");
                }
            }
            assinantePrePago.recarregar(new GregorianCalendar(), valor);
            System.out.println("Recarga realizada com sucesso!");
        } else {
            System.out.println("Assinante pré-pago não encontrado ou recarga não pôde ser realizada.");
        }
    }

    private PrePago localizarPrePago(long cpf) {
        for (int i = 0; i < numPrePagos; i++) {
            if (prePagos[i].getCpf() == cpf) {
                return prePagos[i];
            }
        }
        return null;
    }

    private PosPago localizarPosPago(long cpf) {
        for (int i = 0; i < numPosPagos; i++) {
            if (posPagos[i].getCpf() == cpf) {
                return posPagos[i];
            }
        }
        return null;
    }

    public void imprimirFaturas(int mes) {
        if (numPrePagos == 0 && numPosPagos == 0) {
            System.out.println("Nenhum assinante cadastrado.");
        } else {
            System.out.println("Faturas dos assinantes Pré-Pagos:");
            for (int i = 0; i < numPrePagos; i++) {
                prePagos[i].imprimirFatura(mes);
            }

            System.out.println("Faturas dos assinantes Pós-Pagos:");
            for (int i = 0; i < numPosPagos; i++) {
                posPagos[i].imprimirFatura(mes);
            }
        }
    }

    public static void main(String[] args) throws CreditoInsuficienteException, LimiteChamadasAtingidoException {
        try (Scanner scanner = new Scanner(System.in)) {
            Telefonia telefonia = new Telefonia(100, 100);
    
            int opcao;
            do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar assinante");
            System.out.println("2 - Listar assinantes");
            System.out.println("3 - Fazer chamada");
            System.out.println("4 - Fazer recarga");
            System.out.println("5 - Imprimir faturas");
            System.out.println("0 - Sair");

            System.out.println("Digite a opção desejada:");
            while (true) {
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Digite novamente:");
                }
            }

            switch (opcao) {
                case 1:
                    telefonia.cadastrarAssinante();
                    break;
                case 2:
                    telefonia.listarAssinantes();
                    break;
                case 3:
                    telefonia.fazerChamada();
                    break;
                case 4:
                    telefonia.fazerRecarga();
                    break;
                case 5:
                    System.out.println("Digite o mês para imprimir as faturas:");
                    int mes;
                    while (true) {
                        try {
                            mes = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Mês inválido. Digite novamente:");
                        }
                    }
                    telefonia.imprimirFaturas(mes);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

}

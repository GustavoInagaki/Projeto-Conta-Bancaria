public class SistemaBancario {
    private double saldo;
    private double limiteChequeEspecial;
    private double chequeEspecialUsado;
    
    
    public SistemaBancario(double depositoInicial) {
        this.saldo = depositoInicial;
        
        if(depositoInicial <= 500) {
            this.limiteChequeEspecial = 50;

        }else {
            this.limiteChequeEspecial = depositoInicial * 0.5;
        }
        
        this.chequeEspecialUsado = 0;
    }
    
    public void consultarSaldo() {
        System.out.printf("Saldo atual: R$%.2f\n", saldo );
    }
    
    public void consultarChequeEspecial() {
        System.out.printf("Limite do cheque especial: R$%.2f\n", limiteChequeEspecial);
        System.out.printf("Cheque especial disponivel: R$%.2f\n", limiteChequeEspecial - chequeEspecialUsado);
    }
    
    public void depositar(double valor) {
        if(valor > 0) {
            if(chequeEspecialUsado > 0) {
                double taxa = chequeEspecialUsado * 0.2;
                double totalDivida = chequeEspecialUsado + taxa;
                
                if(valor >= totalDivida) {
                    valor -= totalDivida;
                    chequeEspecialUsado = 0;
                    System.out.printf("Dívida de R$ %.2f (incluindo taxa de %.2f) foi paga.\n", totalDivida, taxa);
                }else {
                    double valorAplicado = valor / 1.2;
                    chequeEspecialUsado -= valorAplicado;
                    System.out.printf("Parcial da dívida foi paga: R$ %.2f (taxa proporcional aplicada).\n", valor);
                    return;
                }
            }
            
            saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso.\n", valor);
        }else {
               System.out.println("Erro: valor inválido para depósito.");
           }
    }
    
    public void sacar(double valor) {
           if (valor <= 0) {
               System.out.println("Erro: valor inválido para saque.");
               return;
           }
            else if (valor <= saldo) {
               saldo -= valor;
               System.out.printf("Saque de R$ %.2f realizado com sucesso.\n", valor);
           } else {
               double restante = valor - saldo;
               if (restante <= (limiteChequeEspecial - chequeEspecialUsado)) {
                   chequeEspecialUsado += restante;
                   saldo = 0;
                   System.out.printf("Saque de R$ %.2f realizado utilizando cheque especial.\n", valor);
               } else {
                   System.out.println("Erro: saldo insuficiente e limite de cheque especial excedido.");
               }
           }
       }

       public void pagarBoleto(double valor) {
           System.out.printf("Tentando pagar boleto de R$ %.2f...\n", valor);
           sacar(valor);
       }

       public void verificarUsoChequeEspecial() {
           if (chequeEspecialUsado > 0) {
               System.out.printf("Atenção: você está usando R$ %.2f do cheque especial.\n", chequeEspecialUsado);
           } else {
               System.out.println("Você não está usando o cheque especial.");
           }
       }
    
}

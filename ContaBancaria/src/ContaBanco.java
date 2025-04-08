public class ContaBanco {

	public static void main(String[] args) {
		
		SistemaBancario conta = new SistemaBancario(400);
		
		conta.consultarSaldo();
		conta.consultarChequeEspecial();
		
		conta.sacar(430);
		
		conta.consultarSaldo();
		conta.consultarChequeEspecial();
		
		conta.depositar(50);
		
		conta.consultarSaldo();
		conta.consultarChequeEspecial();
	}
}


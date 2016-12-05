package locadora.controller;

import locadora.model.Funcionario;



public class Main {
	public static void main(String[] args) {
		Funcionario f1 = new Funcionario(Funcionario.PADRAO,
				2100,"João","123");
		System.out.println(f1.calcularSalarioComImposto());
		
		Funcionario f2 = new Funcionario(Funcionario.PADRAO,
				1700,"José","321");
		System.out.println(f2.calcularSalarioComImposto());
	}
}

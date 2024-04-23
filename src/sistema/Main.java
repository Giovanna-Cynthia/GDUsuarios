package sistema;
//
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int opcao = 0;
		System.out.println("Bem-Vindo ao Sistema");
		System.out.println("-----------------------");
		System.out.println("Escolha 1 - Para cadastrar Usuario");
		System.out.println("Escolha 2 - Para cadastrar Produto");
		System.out.println("-----------------------");
		opcao = sc.nextInt();
		
		switch (opcao) {
		case 1: {
			Sistema.main(args);
		}
		case 2: {
		SistemaProduto.main(args);
		}
		default:
			System.out.println("Opcao Invalida");
		}
	
	}

}
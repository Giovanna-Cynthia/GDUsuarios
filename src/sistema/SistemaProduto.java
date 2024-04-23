package sistema;

import java.util.Scanner;

import service.HandleMenuProduct;


public class SistemaProduto {

	public static void main(String[] args) {
		// Scanner para capturar dados

		Scanner sc = new Scanner(System.in);
		HandleMenuProduct hmp = new HandleMenuProduct();
		int opcao = 0;
	
		do {
			// \n quebra uma linha, (joga o conteudo a seguir para linha debaixo)
			System.out.println(
					"1 - Criar \n2 - Editar \n3 - Deletar \n4 - Listar \n5 - Listar Especifico \n6 - Total dos Preços \n7 - Somar Produtos Cadastrados \n9 - Sair\n");

			opcao = sc.nextInt();

			switch (opcao) {
			case 1: {
				hmp.criar();
				break;
			}
			case 2: {
				hmp.editar();
				break;
			}
			case 3: {
				hmp.deletar();
				break;
			}
			case 4: {
				hmp.listar();
				break;
			}
			case 5: {
				hmp.listarEspecifico();
				break;
			}
			case 6: {
				hmp.somarPrecos();
			}
			case 7: {
				hmp.totalProdutos();
			}
			default:
				System.out.println("Opcao Invalida");
				break;
			}
		} while (opcao != 9);
	}

}

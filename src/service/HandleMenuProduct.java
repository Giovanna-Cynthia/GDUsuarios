package service;

import java.util.List;
import java.util.Scanner;
import models.Produto;
import utils.GerenciadorProdutos;

public class HandleMenuProduct {

	Scanner sc = new Scanner(System.in);
	
	GerenciadorProdutos gp = new GerenciadorProdutos();
	
	public HandleMenuProduct() {
		//ira criar os produtos em um arquivo txt
		gp.verificaECria("produtos.txt");
	}
	
	public void criar() {
		System.out.println("Digite o nome do Produto: ");
		String nome = sc.next();
		System.out.println("Digite o preço do Produto: ");
		double preco = sc.nextDouble();
		System.out.println("Digite a quantidade do Produto: ");
		int quantidade = sc.nextInt();
		
		long id = getNextId();
		
		Produto p = new Produto(id, nome, preco, quantidade);
		
		gp.adicionarProduto(p);
	}
	
	 public void editar() {
		 System.out.println("Digite o ID do Produto: ");
		 long id = sc.nextLong();
		 System.out.println("Digite o nome do Produto: ");
		 String nome = sc.next();
		 System.out.println("Digite o preço do Produto: ");
		 double preco = sc.nextDouble();
		 System.out.println("Digite a quantidade do Produto: ");
		 int quantidade = sc.nextInt();
		 
		 gp.editarProduto(id, nome, preco, quantidade);
	 }
	 
	 public void deletar() {
		 System.out.println("Digite o ID do produto a ser deletado:");
		long id = sc.nextLong();
		gp.deletarProduto(id);
	 }
	
	 public void listar() {
		gp.listarProdutos();
	 }
	
	 public void listarEspecifico() {
		 System.out.println("Digite o ID do produto que deseja listar:");
		long id = sc.nextLong();
		gp.listarEspecifico(id);
	 }
	

	private long getNextId() {
		List<Produto> produtos = gp.lerProdutos();
		
		long maxId = 0;
		
		for(Produto produto : produtos) {
			long id = produto.getId();
			
			if (id > maxId) {
			
				maxId = id;
			}
		}
		return maxId + 1;
	}
}

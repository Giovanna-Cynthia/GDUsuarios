package service;

import java.util.List;
import java.util.Scanner;

import models.Usuario;
import utils.GerenciadorUsuarios;

public class HandleMenu {

	Scanner sc = new Scanner(System.in);
	
	//Gerenciador
	GerenciadorUsuarios gs = new GerenciadorUsuarios();
	
	//Construtor vazio
	public HandleMenu() {
		//toda vez que a classe menu, for instaciada, o nosso arquivo sera verificado
		gs.verificaECria("usuarios.txt");
	}
	
	public void criar() {
		System.out.println("Digite o nome:");
		String nome = sc.next();
		System.out.println("Digite a senha:");
		String senha = sc.next();
		
		int id = getNextId();
		
		Usuario u = new Usuario(id, nome, senha);
		//gd => esta chamando o gerenciador de usuarios e seu dominios de la 
		gs.adicionarUsuario(u);
	}
	
	public void editar() {
		System.out.println("Digite o ID do usuario: ");
		int id = sc.nextInt();
		System.out.println("Digite o novo nome: ");
		String nome = sc.next();
		System.out.println("Digite a nova senha: ");
		String senha = sc.next();
		
		gs.editarUsuario(id, nome, senha);
	}
	
	public void deletar() {
		System.out.println("Digite o ID do usuario a ser deletado:");
		int id = sc.nextInt();
		gs.deletarUsuario(id);
	}
	
	public void listar() {
		gs.listarUsuarios();
	}
	
	public void listarEspecifico() {
		System.out.println("Digite o ID do usuario que deseja listar:");
		int id = sc.nextInt();
		gs.listarEspecifico(id);
	}
	
	private int getNextId() {
		List<Usuario> usuarios = gs.lerUsuarios();
		
		// o maxId se inicia a partir do 0 (que foi determinado pela variavel)
		//o for vai percorrer os usuarios cadastrados, e somar mais 1, para que não aja repetição de id
		int maxId = 0;
		//for => Foreach percorre objeto, um por um
		//unico usuario: lista com todos
		for(Usuario usuario : usuarios) {
			int id = usuario.getId();
			
			if (id > maxId) {
				//logica para descobrir ultimo id
				maxId = id;
			}
		}
		return maxId + 1;
	}
	
	public void login() {
		System.out.println("Digite seu usuario: ");
		String login = sc.next();
		System.out.println("Digite sua senha: ");
		String senha = sc.next();
		
		gs.login(login, senha);
	}

	
	 public void alterarSenha( ) {
		System.out.println("Digite o ID do usuario que deseja alterar a senha:");
		long id = sc.nextInt();
		System.out.println("Digite sua senha antiga: ");
		String senhaAntiga = sc.next();
		System.out.println("Digite sua nova senha: ");
		String novaSenha = sc.next();
		
		gs.alterarSenha(id, senhaAntiga, novaSenha);
	    }

}

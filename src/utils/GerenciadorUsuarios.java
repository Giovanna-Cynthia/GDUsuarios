package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import models.Usuario;

public class GerenciadorUsuarios {

	//Private => Variavel privada
	//Static => Pode ser acessada por outras variaveis
	//Final => Não pode ser alterada
	private static final String NOME_ARQUIVO = "usuarios.txt";
	
	//Verificar a Existencia do nosso banco e criar caso nao exista
	public void verificaECria(String nomeArquivo) {
		//file => arquivo
		File arquivo = new File(nomeArquivo);
		//verificar se o arquivo existe
		if(arquivo.exists()) {
			System.out.println("Banco Funcionando! ");
		} else {
			//tente criar, caso de erro, exibe o erro
			try {
				//Criar o novo arquivo
				arquivo.createNewFile();
				System.out.println("Arquivo criado com sucesso!");
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao criar o arquivo" + e.getMessage());
			}
		}
	}
	
	
	public void adicionarUsuario(Usuario usuario) {
		//Writer => Escrever
		//BufferWriter, FileWriter
		//BufferedWriter, proporciona uma eficiente escrita
		//FileWriter, escreve dentro do arquivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(usuario.toString()); //1;giovanna;12345
			bw.newLine(); //nova linha no arquivo txt
			System.out.println("Usuario adicionado com sucesso!");
		}catch(IOException e) {
			System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
		}
	}
	
	//List<Usuario> usuarios = new ArrayList<Usuario>(); => ira percorrer os usuarios que serao cadastrados no sistema
	public List<Usuario> lerUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		//Buffed, File, Reader
		//O FileReader é usado para ler caracteres de um arquivo, e o BufferedReader é utilizado para realizar operações de leitura com melhor desempenho usando uma memória intermédia. 
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha; //linha => 1;nome;senha
			//percorrer todas as linhas enquanto seja diferente de vazio
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";"); //dividir em tres partes
				//quando o proximo usuario for adicionada, serão cadastrados por partes
				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
			}
			//IOException é o "máximo", ele foca na entrada e saída de dados
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
		}
		return usuarios;
	}
	
	public void deletarUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();
		
		//removeIf => remove o usuario, e o id que o usuario era cadastrado (e nenhum outro usuario sera cadastrado no mesmo id)
		if(usuarios.removeIf(usuario -> usuario.getId() == id)) {
			//reescrevendo arquivo com novos usuarios e alteracoes
			reescreverArquivo(usuarios);
			System.out.println("Usuario deletado com sucesso");
		} else {
			System.out.println("Usuario nao encontrado");
		}
	}
	
	public void reescreverArquivo(List<Usuario> usuarios) {
		//
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))){
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		} catch(IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
		}
	}
	
	public void listarUsuarios() {
		List<Usuario> usuarios = lerUsuarios();
		//nenhum usuario para listar
		//isEmpty => esta vazio
		//caso não esteja ira fazer o percurso para listar todos os usuarios
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuario cadastrado");
		} else {
			System.out.println("Lista de usuarios");
			for (Usuario usuario : usuarios) {
				System.out.println("ID: " + usuario.getId());
				//System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: " + usuario.getSenha());
			}
		}
	}
	
	public void editarUsuario(int id, String novoNome, String novaSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean encontrado = false;
		
		//vai de usuario a usuario, ate achar o id selecionado
		for (Usuario usuario : usuarios) {
			//id não pode ser alterado por ja ser pré definido
			if (usuario.getId() == id) {
				//dera a opcoa do usuario alterar para o novo nome
				usuario.setNome(novoNome);
				usuario.setSenha(novaSenha);
				//encontrado = true => variavel para encontrar o usuario que ja esta cadastrado
				encontrado = true;
				break;
			}
		}
		//Se (o usuario estiver no sistema) sera encontrado, ira aparecer seus dados e entao recebera a mensagem
		if (encontrado) {
			reescreverArquivo(usuarios);
			System.out.println("Usuario editado com sucesso!");
			//mas se não for recebera a mensagem, e voltara para as perguntas
		} else {
			System.out.println("Usuario nao encontrado");
		}
	}
	
	public void listarEspecifico(int id) {
		List<Usuario> usuarios = lerUsuarios();
		
		//ira percorrer de usuario a usuario, para encontrar o id cadastrado com os dados
		for (Usuario usuario : usuarios) {
			//Se o id do usuario ainda estiver no sistema
			if (usuario.getId() == id) {
				//ira aparecer os dados especificos dele...
				System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: " + usuario.getSenha());
			}
		}
	}
	
	public void login(String login, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		//
		boolean encontrado = false;
		
		//ira percorrer a lista de usuarios
		for (Usuario usuario : usuarios) {
			//Se (o nome(login) e senha(senha) for igual aos dados cadastrados)
			if (usuario.getNome().equals(login) && usuario.getSenha().equals(senha)) {
				//então o usuario é igual a verdadeiro
				encontrado = true;
				break;
			}
		}
		if (encontrado) {
			//Se (for encontrado) receberá tal mensagem
			System.out.println("Bem-Vindo ao sistema!");
			//para sair do sistema pós login correto.
			System.exit(0);
		} else {
			//se não for encontrado, recebera a seguinte mensagem e voltara para o menu 
			System.out.println("Usuario Invalido");
		}
	}
}

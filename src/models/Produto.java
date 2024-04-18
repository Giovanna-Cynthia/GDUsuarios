package models;

public class Produto {

	//id => 2147483647 - int
	//id => 9.223.372.036.854.775.807 - long
	
	private long id;
	private String nome;
	private double preco;
	private int quantidade;
	
	public long getId() {
		return id;
	}

	public Produto(long id, String nome, double preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	//sobrescrever metodo
		@Override
		public String toString() {
			return id + ";" + preco + ";" + quantidade;
		}
}

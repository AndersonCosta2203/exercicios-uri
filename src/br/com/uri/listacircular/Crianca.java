package br.com.uri.listacircular;

public class Crianca {

	private Integer codigo;
	private String nome;

	public Crianca(Integer codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public Crianca() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Crianca [codigo=" + codigo + ", nome=" + nome + "]";
	}

}

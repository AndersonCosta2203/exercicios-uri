package br.com.uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcampamentoDeFerias {
	
	static Integer quantidadeDeCriancas = 0;
	static List<Integer> listaDosVerificados = new ArrayList<>();
	
	public static void main(String[] args) {

		try (Scanner e = new Scanner(System.in)) {
			do {
				ListaCircular circular = new ListaCircular();
				System.out.println("Indique a quantidade de crianças: ");
				quantidadeDeCriancas = e.nextInt();
				String lixo = e.nextLine();
				
				if (quantidadeDeCriancas == 0) { break; }
				
				listaDosVerificados.clear();
				for (int i = 0; i < quantidadeDeCriancas; i++) {
					System.out.println("Insira o Nome e Valor da ficha");
					String nomeValor = e.nextLine();

					String[] arrayNomeValor = nomeValor.split(" ");
					
					String nome = arrayNomeValor[0];
					if (nome != null && nome.matches("[a-zA-Z\\s]+") && nome.length() <= 30) {
						No node = new No();
						node.setId(i);
						node.setCrianca(new Crianca(Integer.valueOf(arrayNomeValor[1]), arrayNomeValor[0]));
						circular.add(node);
					} else { quantidadeDeCriancas--; i--; }
				}
				
				Crianca crianca = circular.getInicio().getCrianca();
				while (listaDosVerificados.size() < circular.getContador()) {
					crianca = buscarCrianca(crianca.getCodigo(), circular);
					listaDosVerificados.add(crianca.getCodigo());
				}
				System.out.println("Vencedor(a): "+crianca.getNome());
			} while (quantidadeDeCriancas > 1);
			
		}
	}

	private static Crianca buscarCrianca(Integer codigoCrianca, ListaCircular circular) {
		String posicao = (codigoCrianca % 2 == 0) ? "D" : "E";
		No tmp = circular.getInicio();
		while (true) {
			if (tmp.getCrianca().getCodigo() == codigoCrianca) {
				do {
					tmp = buscarProximoNo(posicao, tmp, circular);
					if (!listaDosVerificados.contains(tmp.getCrianca().getCodigo())) {
						return tmp.getCrianca();
					}
				} while (true);
			}
			tmp = buscarProximoNo(posicao, tmp, circular);
        }
	}

	private static No buscarProximoNo(String posicao, No tmp, ListaCircular circular) {
		if ("D".equals(posicao)) { 
			tmp = circular.proximoNo(tmp);
		} else { 
			tmp = circular.anteriorNo(tmp); 
		}
		return tmp;
	}
}

class ListaCircular {
	private No inicio;
	private int contador;

	public ListaCircular() {
		super();
		this.inicio = null;
		this.contador = 0;
	}

	public int quantDeNo() {
		return contador;
	}

	public No proximoNo(No atual) {
		return atual.getProximo();
	}

	public No anteriorNo(No atual) {
		return atual.getAnterior();
	}

	public No getInicio() {
		return inicio;
	}

	public void setInicio(No inicio) {
		this.inicio = inicio;
	}

	public void add(No elemento) {
		if (this.inicio == null) {
			elemento.setProximo(elemento);
			elemento.setAnterior(elemento);
			this.inicio = elemento;
		} else {
			No tmp = inicio;
			while (tmp.getProximo() != inicio) {
				tmp = tmp.getProximo();
			}
			inicio.setAnterior(elemento);
			tmp.setProximo(elemento);
			elemento.setAnterior(tmp);
			elemento.setProximo(inicio);
		}
		contador++;
	}

	public boolean isEmpty() {
		return this.inicio == null;
	}

	public void setNo(No no) {
		this.inicio = no;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
}

class Crianca {

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
}

class No {
	private No proximo;
	private No anterior;
	private Integer id;
	private Crianca crianca;

	public No(Crianca crianca) {
		super();
		this.crianca = crianca;
	}

	public No() {
		super();
	}

	public No getProximo() {
		return proximo;
	}

	public void setProximo(No proximo) {
		this.proximo = proximo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Crianca getCrianca() {
		return crianca;
	}

	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}

	public No getAnterior() {
		return anterior;
	}

	public void setAnterior(No anterior) {
		this.anterior = anterior;
	}
}

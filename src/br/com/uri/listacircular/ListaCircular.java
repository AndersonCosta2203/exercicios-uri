package br.com.uri.listacircular;

public class ListaCircular {
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

	public No getNo() {
		return inicio;
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

	public void exibirListaNaOrdem() {
		No tmp;
		tmp = inicio;
		do {
			System.out.println(
					"Elemento da lista = " + tmp.getCrianca().getCodigo() + " - " + tmp.getCrianca().getNome());
			tmp = tmp.getProximo();
		} while (tmp != inicio);
	}

	public void exibirListaNaOrdemInversa() {
		No tmp;
		tmp = inicio;
		do {
			System.out.println("Elemento da lista = " + tmp.getCrianca().getCodigo() + " - "
					+ tmp.getCrianca().getNome() + "\n" + tmp);
			tmp = tmp.getAnterior();
		} while (tmp != inicio);
	}

	@Override
	public String toString() {
		return "ListaCircular [no=" + inicio + ", contador=" + contador + "]";
	}

}

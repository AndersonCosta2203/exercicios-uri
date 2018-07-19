package br.com.uri;

public class ListaCircular {
	private No no;
	private int contador;

	public ListaCircular() {
		super();
		this.no = null;
		this.contador = 0;
	}

	public int quantDeNo() {
		return contador;
	}

	public void proximoNo() {
		no = no.getProximo();
	}

	public void anteriorNo() {
		no = no.getAnterior();
	}

	public void add(No node) {
		if (this.no == null) {
			node.setProximo(node);
			node.setAnterior(node);
			this.no = node;
		} else {
			node.setProximo(this.no.getProximo());
			node.setAnterior(this.no.getAnterior());
			this.no.setProximo(node);
			this.no.setAnterior(node);
		}
		contador++;
	}

	public No getNo() {
		return no;
	}

	public void setNo(No no) {
		this.no = no;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	@Override
	public String toString() {
		return "ListaCircular [no=" + no + ", contador=" + contador + "]";
	}
	
	
}

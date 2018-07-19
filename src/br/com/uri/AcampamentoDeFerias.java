package br.com.uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AcampamentoDeFerias {
	
	static ListaCircular circular = new ListaCircular();
	static Integer quantidadeDeCriacas = 0;
	static List<Integer> listaDosVerificados = new ArrayList<>();
	
	public static void main(String[] args) {

		try (Scanner e = new Scanner(System.in)) {
			do {
				System.out.println("Indique a quantidade de crianças: ");
				quantidadeDeCriacas = 5; // e.nextInt();
				//String lixo = e.nextLine();
				
				List<String> listaTemporaria = Arrays.asList("Maria 7", "Pedro 9", "Joao_Vitor 5", "Isabel 12", "Laura 8"); 
						// Arrays.asList("Fernanda 7", "Fernando 9", "Gustavo 11");
				
				for (int i = 0; i < quantidadeDeCriacas; i++) {
					System.out.println("Insira o Nome e Valor da ficha");
					String nomeValor = listaTemporaria.get(i); // e.nextLine();

					String[] arrayNomeValor = nomeValor.split(" ");
					
					String nome = arrayNomeValor[0];
					if (nome.matches("[a-zA-Z\\s]+")) {
						No node = new No();
						node.setId(i);
						node.setCrianca(new Crianca(Integer.valueOf(arrayNomeValor[1]), arrayNomeValor[0]));
						circular.add(node);
					}
				}
				
				Crianca crianca = buscarCriancaPorId(0);
				while (listaDosVerificados.size() < quantidadeDeCriacas) {
					crianca = buscarCrianca(crianca.getCodigo());
					listaDosVerificados.add(crianca.getCodigo());
				}
				System.out.println("Vencedor(a): "+crianca.getNome());
				quantidadeDeCriacas = 0;
			} while (quantidadeDeCriacas > 1);
			
		}
	}

	private static Crianca buscarCrianca(Integer codigoCrianca) {
		String posicao = (codigoCrianca % 2 == 0) ? "D" : "E";
		while (true) {
			if (circular.getNo().getCrianca().getCodigo() == codigoCrianca) {
				if("D".equals(posicao)) {
					circular.proximoNo();
					if (!listaDosVerificados.contains(circular.getNo().getCrianca().getCodigo())) {
						return circular.getNo().getCrianca();
					}
				} else {
					circular.anteriorNo();
					if (!listaDosVerificados.contains(circular.getNo().getCrianca().getCodigo())) {
						return circular.getNo().getCrianca();
					}
				}
			}
			circular.proximoNo();
        }
	}

	private static Crianca buscarCriancaPorId(Integer id) {
		while (true) {
			if (circular.getNo().getId() == id) {
				return circular.getNo().getCrianca();
			}
            circular.proximoNo();
        }
	}

}
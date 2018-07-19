package br.com.uri;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Estiagem {

	private static List<Consumo> listaDeConsumo = new ArrayList<>();

	public static void main(String[] args) {

		/*
		 * A primeira linha de cada caso de teste contém um inteiro N (1 ≤ N ≤ 1*106),
		 * indicando a quantidade de imóveis
		 * 
		 */
		try (Scanner e = new Scanner(System.in)) {
			Integer quantidadeImoveis = 0;
			int contador = 1;
			do {
				System.out.println("Indique a quantidade de imóveis: ");
				quantidadeImoveis = e.nextInt();
				String lixo = e.nextLine();

				if (quantidadeImoveis > 0) {
					listaDeConsumo.clear();
					Integer quantidadeTotalDePessoa = 0;
					BigDecimal consumoDeAguaTotal = new BigDecimal("0");
					for (int i = 0; (i < quantidadeImoveis) && (i <= 1_000_000); i++) {
						System.out.println("Indique a quantidade e o consumo total: ");
						String dadosConsumo = e.nextLine();

						String[] valor = dadosConsumo.split(" ");
						if (valor.length > 1) {
							BigDecimal quantidadePessoas = new BigDecimal(valor[0]).setScale(2);
							BigDecimal consumo = new BigDecimal(valor[1]).setScale(2);
							if (quantidadePessoas.compareTo(BigDecimal.ZERO) > 0
									&& consumo.compareTo(BigDecimal.ZERO) > 0) {

								BigDecimal mediaDeGasto = consumo.divide(quantidadePessoas, RoundingMode.DOWN)
										.setScale(2);

								adicionarNaFilaEVerificarAgrupamento(
										new Consumo(quantidadePessoas.intValue(), consumo.intValue(), mediaDeGasto));
								quantidadeTotalDePessoa += quantidadePessoas.intValue();
								consumoDeAguaTotal = consumoDeAguaTotal.add(consumo);
							}
						}
					}

					List<Consumo> listaOrdenada = listaDeConsumo.stream()
							.sorted(Comparator.comparing(Consumo::getMediaDeGasto)).collect(Collectors.toList());

					StringBuilder valorOrdenado = new StringBuilder();
					for (Consumo consumo : listaOrdenada) {
						valorOrdenado.append(valorOrdenado == null || valorOrdenado.length() == 0
								? consumo.getQuantidadeDePessoas() + "-" + consumo.getMediaDeGasto().longValue()
								: " ".concat(consumo.getQuantidadeDePessoas()+ "-" + consumo.getMediaDeGasto().longValue()));

					}

					Double media = consumoDeAguaTotal.doubleValue() / quantidadeTotalDePessoa.longValue();
					BigDecimal bg = new BigDecimal(media.toString());

					System.out.println("Cidade# n: " + contador);
					System.out.println(valorOrdenado);
					System.out.println("Consumo medio: " + bg.setScale(2, BigDecimal.ROUND_DOWN) + " m3.\n");

					contador++;
				}
			} while (quantidadeImoveis > 0);
		}
	}

	public static boolean compararERetornarSevalorEIgual(BigDecimal vUm, BigDecimal vDois) {
		return vUm.setScale(0, RoundingMode.HALF_DOWN).compareTo(vDois.setScale(0, RoundingMode.HALF_DOWN)) == 0;
	}

	public static void adicionarNaFilaEVerificarAgrupamento(Consumo c) {
		boolean encontrou = listaDeConsumo.stream().filter((Consumo consumo) -> {
			return compararERetornarSevalorEIgual(consumo.getMediaDeGasto(), c.getMediaDeGasto());
		}).map((Consumo consumo) -> {
			consumo.setQuantidadeDePessoas(consumo.getQuantidadeDePessoas() + c.getQuantidadeDePessoas());
			return consumo;
		}).count() > 0;
		if (!encontrou) {
			listaDeConsumo.add(c);
		}
	}


}

class Consumo {
	private Integer quantidadeDePessoas;
	private Integer gastoTotalImovel;
	private BigDecimal mediaDeGasto;
	
	public Consumo() {
	}
	
	public Consumo(Integer quantidadeDePessoas, Integer gastoTotalImovel, BigDecimal mediaDeGasto) {
		super();
		this.quantidadeDePessoas = quantidadeDePessoas;
		this.gastoTotalImovel = gastoTotalImovel;
		this.mediaDeGasto = mediaDeGasto;
	}
	
	public Integer getGastoTotalImovel() {
		return gastoTotalImovel;
	}
	
	public BigDecimal getMediaDeGasto() {
		return mediaDeGasto;
	}
	
	public Integer getQuantidadeDePessoas() {
		return quantidadeDePessoas;
	}
	
	public void setQuantidadeDePessoas(Integer quantidadeDePessoas) {
		this.quantidadeDePessoas = quantidadeDePessoas;
	}
}

package br.com.uri;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Estiagem {
	
	private static List<Consumo> listaDeConsumo = new ArrayList<>();
	private static List<Consumo> listaDeConsumoParaMedia = new ArrayList<>();

	public static void main(String[] args) {
		
		/*
		 * A primeira linha de cada caso de teste contém um inteiro N (1 ≤ N ≤ 1*106), 
		 * indicando a quantidade de imóveis
		 * 
		 * */
		try (Scanner e = new Scanner(System.in)) {
			Integer quantidadeImoveis = 0;
			int contador = 1;
			do {
				System.out.println("Indique a quantidade de imóveis: ");
				quantidadeImoveis = e.nextInt();
				String lixo = e.nextLine();
				
				if (quantidadeImoveis > 0) {
					listaDeConsumo.clear();
					listaDeConsumoParaMedia.clear();
					List<String> listaDeDadosConsumo = new ArrayList<>();
//					for (int i = 0; (i < quantidadeImoveis) && (i <= 1_000_000); i++) {
//						
//						/*
//						 * Deve-se listar, por ordem ascendente de consumo, 
//						 * a quantidade de pessoas seguido de um hífen e o 
//						 * consumo destas pessoas, arredondando o valor para baixo.
//						 * 
//						 * */
//						System.out.println("Indique a quantidade e o consumo total: ");
//						String dadosConsumo = e.nextLine();
//						listaDeDadosConsumo.add(dadosConsumo);
//					}
					
					listaDeDadosConsumo = Arrays.asList("1 25", "2 20", "3 31", "2 40", "6 70");
					
					for (String dados : listaDeDadosConsumo) {
						String[] valor = dados.split(" ");
						if (valor.length > 1) {
							BigDecimal quantidadePessoas = new BigDecimal(valor[0]).setScale(2);
							BigDecimal consumo = new BigDecimal(valor[1]).setScale(2);
							if (quantidadePessoas.compareTo(BigDecimal.ZERO) > 0 && 
									consumo.compareTo(BigDecimal.ZERO) > 0) {
								BigDecimal mediaDeGasto = consumo.divide(quantidadePessoas, RoundingMode.DOWN).setScale(2);
								listaDeConsumoParaMedia.add(new Consumo(quantidadePessoas.intValue(), consumo.intValue(), mediaDeGasto));
								adicionarNaFilaEVerificarAgrupamento(new Consumo(quantidadePessoas.intValue(), consumo.intValue(), mediaDeGasto));
							}						
						}					
					}
									
					List<Consumo> listaOrdenada = listaDeConsumo.stream()
							.sorted(
									Comparator.comparing(Consumo::getMediaDeGasto)).collect(Collectors.toList());
					
					StringBuilder valorOrdenado = new StringBuilder();	
					for (Consumo consumo : listaOrdenada) {
						valorOrdenado.append(valorOrdenado == null || valorOrdenado.length() == 0 ? 
								consumo.quantidadeDePessoas +"-"+ consumo.mediaDeGasto.longValue() : 
									" ".concat(consumo.quantidadeDePessoas +"-"+ consumo.mediaDeGasto.longValue()));  
						
					}
					
					BigDecimal quantidadeTotalDePessoa = BigDecimal.valueOf(listaDeConsumoParaMedia
							.stream()
							.map((Consumo c) -> {
								return c.quantidadeDePessoas;
							})
							.mapToDouble(Integer::doubleValue)
							.sum());

					BigDecimal consumoDeAguaTotal = BigDecimal.valueOf(listaDeConsumoParaMedia
						.stream()
						.map((Consumo c) -> {
							return c.gastoTotalImovel;
						})
						.mapToDouble(Integer::doubleValue)
						.sum());
					
					System.out.println(quantidadeTotalDePessoa +" "+consumoDeAguaTotal);
					BigDecimal media = consumoDeAguaTotal.divide(quantidadeTotalDePessoa, RoundingMode.UP) 
							.setScale(2);
					
					System.out.println("Cidade# n: "+contador);
					System.out.println(valorOrdenado);
					System.out.println("Consumo medio: "+media+" m3.\n");
					
					contador++;
				}
			} while (quantidadeImoveis > 0 );
		}
	}
	
	public static boolean compararERetornarSevalorEIgual(BigDecimal vUm, BigDecimal vDois) {
		return vUm.setScale(0, RoundingMode.HALF_DOWN)
				.compareTo(vDois.setScale(0, RoundingMode.HALF_DOWN)) == 0;
	}
	
	public static void adicionarNaFilaEVerificarAgrupamento(Consumo c) {
		boolean encontrou = listaDeConsumo.stream()
			.filter((Consumo consumo) -> {
				return compararERetornarSevalorEIgual(consumo.mediaDeGasto, c.getMediaDeGasto());	
			})
			.map((Consumo consumo) -> {
				consumo.setQuantidadeDePessoas(
						consumo.getQuantidadeDePessoas() + 
						c.getQuantidadeDePessoas());
				return consumo;
			})
			.count() > 0;
		if (!encontrou) { listaDeConsumo.add(c); }	
	}
	
	static class Consumo {
		private Integer quantidadeDePessoas;
		private Integer gastoTotalImovel;
		private BigDecimal mediaDeGasto;
		
		public Consumo() {}
		
		public Consumo(Integer quantidadeDePessoas, Integer gastoTotalImovel, BigDecimal mediaDeGasto) {
			super();
			this.quantidadeDePessoas = quantidadeDePessoas;
			this.gastoTotalImovel = gastoTotalImovel;
			this.mediaDeGasto = mediaDeGasto;
		}

		public Integer getGastoTotalImovel() {
			return gastoTotalImovel;
		}
		
		public void setGastoTotalImovel(Integer gastoTotalImovel) {
			this.gastoTotalImovel = gastoTotalImovel;
		}
		
		public BigDecimal getMediaDeGasto() {
			return mediaDeGasto;
		}
		
		public void setMediaDeGasto(BigDecimal mediaDeGasto) {
			this.mediaDeGasto = mediaDeGasto;
		}

		public Integer getQuantidadeDePessoas() {
			return quantidadeDePessoas;
		}

		public void setQuantidadeDePessoas(Integer quantidadeDePessoas) {
			this.quantidadeDePessoas = quantidadeDePessoas;
		}

		public Double getGastoTotalImovelDouble() {
			return gastoTotalImovel.doubleValue();
		}

		public Integer getMediaDeGastoInt() {
			return mediaDeGasto.intValue();
		}
	}
}

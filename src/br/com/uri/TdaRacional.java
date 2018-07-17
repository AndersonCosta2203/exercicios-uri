package br.com.uri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TdaRacional {

	public static void main(String[] args) {

		try (Scanner e = new Scanner(System.in)) {
			Integer tamanhoLista = 0;

			System.out.println("Insira o tamanho da lista: ");
			tamanhoLista = e.nextInt();
			e.nextLine();

			forUm : for (int i = 0; i < tamanhoLista && i <= 10000 ; i++) {
				System.out.println("Informe a String: ");
				String val = e.nextLine();

				final String[] fraseChar = val.split(" ");

				List<BigDecimal> listaValores = new ArrayList<>();

				char[] arrayDigitos = Arrays.toString(fraseChar).replaceAll("\\D*", "").toCharArray();
				forDois: for (int n = 0; n < arrayDigitos.length; n++) {
					BigDecimal valor = new BigDecimal(String.valueOf(arrayDigitos[n])).setScale(0);
					if (valor.longValue() > 10000) {
						continue forUm;
					}
					listaValores.add(valor);
				}

				Map<String, BigDecimal> mapFormula = new HashMap<>();
				mapFormula.put("N1", listaValores.get(0));
				mapFormula.put("N2", listaValores.get(2));
				mapFormula.put("D1", listaValores.get(1));
				mapFormula.put("D2", listaValores.get(3));

				if (val.contains("+")) {
					realizarOperacaoAdicao(mapFormula);
				} else if (val.contains("-")) {
					realizarOperacaoSubtracao(mapFormula);
				} else if (val.contains("*")) {
					realizarOperacaoMultiplicacao(mapFormula);
				} else if (val.contains("/")) {
					realizarOperacaoDivisao(mapFormula);
				}
			}

		}
	}

	private static void realizarOperacaoDivisao(Map<String, BigDecimal> mapFormula) {
		BigDecimal primeiraParte = (mapFormula.get("N1").multiply(mapFormula.get("D2")));
		BigDecimal segundaParte = (mapFormula.get("N2").multiply(mapFormula.get("D1")));

		processarDivisao(primeiraParte.longValue(), segundaParte.longValue());
	}

	private static void realizarOperacaoMultiplicacao(Map<String, BigDecimal> mapFormula) {
		BigDecimal primeiraParte = (mapFormula.get("N1").multiply(mapFormula.get("N2")));
		BigDecimal segundaParte = (mapFormula.get("D1").multiply(mapFormula.get("D2")));

		processarDivisao(primeiraParte.longValue(), segundaParte.longValue());
	}

	private static void realizarOperacaoSubtracao(Map<String, BigDecimal> mapFormula) {
		BigDecimal primeiraParte = (mapFormula.get("N1").multiply(mapFormula.get("D2")));
		BigDecimal segundaParte = (mapFormula.get("N2").multiply(mapFormula.get("D1")));
		BigDecimal resultadoUm = primeiraParte.subtract(segundaParte);

		BigDecimal terceiraParte = (mapFormula.get("D1").multiply(mapFormula.get("D2")));

		processarDivisao(resultadoUm.longValue(), terceiraParte.longValue());
	}

	private static void realizarOperacaoAdicao(Map<String, BigDecimal> mapFormula) {
		BigDecimal primeiraParte = (mapFormula.get("N1").multiply(mapFormula.get("D2")));
		BigDecimal segundaParte = (mapFormula.get("N2").multiply(mapFormula.get("D1")));
		BigDecimal resultadoUm = primeiraParte.add(segundaParte);

		BigDecimal terceiraParte = (mapFormula.get("D1").multiply(mapFormula.get("D2")));

		processarDivisao(resultadoUm.longValue(), terceiraParte.longValue());
	}

	private static void processarDivisao(long numerador, long denominador) {

		boolean aindaDivide = true;
		long newNumerador = numerador;
		long newDenominador = denominador;
		do {
			if (newNumerador % 2 == 0 && newDenominador % 2 == 0) {
				newNumerador = newNumerador / 2;
				newDenominador = newDenominador / 2;
			} else if (newNumerador % 3 == 0 && newDenominador % 3 == 0) {
				newNumerador = newNumerador / 3;
				newDenominador = newDenominador / 3;
			} else {
				aindaDivide = false;
			}
		} while (aindaDivide);
		System.out.println(numerador + "/" + denominador + " = " + newNumerador + "/" + newDenominador);
	}

}
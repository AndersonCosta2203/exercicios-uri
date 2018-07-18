package br.com.uri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TdaRacional {

	public static void main(String[] args) {

		try (Scanner e = new Scanner(System.in)) {
			Integer tamanhoLista = 0;
			final String regexSoNumero = "\\d*";

			System.out.println("Insira o tamanho da lista: ");
			tamanhoLista = e.nextInt();
			e.nextLine();

			for (int i = 0; i < tamanhoLista && i <= 10000; i++) {
				System.out.println("Informe a String: ");
				String val = e.nextLine();

				final String[] fraseChar = val.split(" ");

				List<BigDecimal> listaValores = new ArrayList<>();

				for (String str : fraseChar) {
					if (str.matches(regexSoNumero)) {
						BigDecimal valor = new BigDecimal(String.valueOf(str)).setScale(0);
						listaValores.add(valor);
					}
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

		long newNumerador = numerador;
		long newDenominador = denominador;
		for (int i = 1; i < 1000; i++) {
			if (newNumerador % i == 0 && newDenominador % i == 0) {
				newNumerador = newNumerador / i;
				newDenominador = newDenominador / i;
				i = 1;
			}
		}
		System.out.println(numerador + "/" + denominador + " = " + newNumerador + "/" + newDenominador);
	}

}
package br.com.uri;

import java.util.Scanner;

public class Criptografia {

	public static void main(String[] args) {
		final String minusculoMaiusculo = "[a-zA-Z]*";

		try (Scanner e = new Scanner(System.in)) {
			System.out.println("Informe a quantidade: ");
			int repeticao = e.nextInt();
			String lixo = e.nextLine();
			
			for (int i = 0; i < repeticao; i++) {
				System.out.println("Informe a String: ");
				String val = e.nextLine();
				
				// Crypt Um
				StringBuilder concat = criptUm(minusculoMaiusculo, val);
				
				// Crypt Dois
				/*
				 *O resultado do segundo processamento inverte os caracteres e produz “3# rw{hW”. 
				 **/
				StringBuilder inverso = concat.reverse();
				
				// Crypt Tres
				StringBuilder finalString = criptTres(inverso);
				
				System.out.println(finalString);
			}
		}
		
	}

	private static StringBuilder criptTres(StringBuilder inverso) {
		/*
		 * Por último, com o deslocamento dos caracteres da metade em diante, o resultado final deve ser “3# rvzgV”. 
		 */
		StringBuilder finalString = new StringBuilder();
		int metade = (inverso.length() / 2);
		for (int i = 0; i < inverso.length(); i++) {
			String val = inverso.substring(i, i+1);
			if (i+1 > metade) {
				int asciiOfA = (int) val.charAt(0);
				finalString.append((char) (asciiOfA -1));
			} else {
				finalString.append(val);
			}
		}
		return finalString;
	}

	private static StringBuilder criptUm(final String minusculoMaiusculo, String valor) {
		StringBuilder concat = new StringBuilder();
		/*
		 * Na primeira passada, somente caracteres que sejam letras minúsculas e
		 * maiúsculas devem ser deslocadas 3 posições para a direita,
		 * 
		 */
		for (int i = 0; i < valor.length(); i++) {
			String val = valor.substring(i, i+1);
			if (val.matches(minusculoMaiusculo)) {
				int asciiOfA = (int) val.charAt(0);
				concat.append((char) (asciiOfA + 3));
			} else {
				concat.append(val);
			}
		}
		return concat;
	}

}

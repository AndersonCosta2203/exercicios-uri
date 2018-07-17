package br.com.uri.tdaracional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TdaRacional {
    public static void main (String[] args) {
        // (N1*D2 + N2*D1) / (D1*D2)
    	List<String> lista = Arrays.asList("4", 
    			"1 / 2 + 3 / 4", 
    			"1 / 2 - 3 / 4", 
    			"2 / 3 * 6 / 6", 
    			"1 / 2 / 3 / 4");
    	Integer tamanhoLista = Integer.parseInt(lista.get(0));
    	for(int i = 0; i <= tamanhoLista -1; i++) {
            final String[] fraseChar = lista.get(i).split(" ");
            System.out.println(fraseChar.toString().contains("+"));
            
            String operador = null;
            List<BigDecimal> listaDouble = new ArrayList<>();
            for (String s : fraseChar) {
            	if ("+".equals(s)) {
    				operador = "+";					
				} else if ("/".equals(s)) {
    				operador = "/";					
				} else if ("*".equals(s)) {
    				operador = "*";					
				} else if ("-".equals(s)) {
    				operador = "-";					
				} else if(!s.contains("/")) {
					listaDouble.add(new BigDecimal(s).setScale(0));
				}
			}
            
            String formula = null;
            switch (operador) {
			case "+":				
				formula = "(N1*D2 + N2*D1) / (D1*D2)";
				break;
			case "/":
				formula = "(N1/D1) / (N2/D2)";
				break;
			case "-":
				formula = " (N1*D2 - N2*D1) / (D1*D2)";
				break;
			case "*":
				formula = "(N1*N2) / (D1*D2)";
				break;

			default:
				break;
			}
            Map<String, BigDecimal> mapFormula = new HashMap<>();
            mapFormula.put("N1", listaDouble.get(0));
            mapFormula.put("N2", listaDouble.get(1));
            mapFormula.put("D1", listaDouble.get(2));
            mapFormula.put("D2", listaDouble.get(3));
            
			String[] novaFormula = formula.split(" / ");
			if ("+".equals(operador)) {
				BigDecimal primeiraParte = (mapFormula.get("N1").multiply(mapFormula.get("D2")));
				BigDecimal segundaParte = (mapFormula.get("N1").multiply(mapFormula.get("D2")));
				BigDecimal terceiraParte = (mapFormula.get("D1").multiply(mapFormula.get("D2")));
				BigDecimal resultadoUm = primeiraParte.add(segundaParte);
				BigDecimal resultadoDois = resultadoUm.divide(terceiraParte);
				System.out.println("-> "+ resultadoDois.toString());
			}
        }
    }
}
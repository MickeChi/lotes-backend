package com.lotes.lotesbackend.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;

public class NumberUtils {
	
	private static final String PATTERN_MUMERIC = "^\\d+(\\.\\d+)?$";


    public static String numeroARomanos(int natural)
    {
        String[][] list =
                {
                        new String[] {"","M","MM","MMM"},
                        new String[] {"","C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                        new String[] {"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                        new String[] {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}
                };
        //thousand
        int unitThousand = natural / 1000;
        int unit = natural % 1000;
        //hundred
        int unitHundred = unit / 100;
        unit = unit % 100;
        //dozen
        int unitDozens = unit / 10;
        unit = unit % 10;
        return list[0][unitThousand] + list[1][unitHundred] + list[2][unitDozens] + list[3][unit];
    }

    public static String numeroATexto(String numero){
        ULocale to = new ULocale("es_MX");
        Double number = Double.parseDouble(numero);
        RuleBasedNumberFormat rbnf = new RuleBasedNumberFormat(to, RuleBasedNumberFormat.SPELLOUT);
        return rbnf.format(number);
    }
    
    public static String numeroDecimalATexto(String numeroStr){
    	 boolean tieneDecimales = numeroStr.indexOf(".") > 0;
    	 String[] arrayNumeros = numeroStr.split("\\.");
    	 List<BigDecimal> numeros = Arrays.stream(arrayNumeros).map(n -> new BigDecimal(n)).collect(Collectors.toList());
    	 StringBuilder texto = new StringBuilder();
    	 long cont = 0;
    	 for (BigDecimal n : numeros) {
    		 texto.append(numeroATexto(n.toString()));
    		 //System.out.println(texto.toString());
    		 if(cont < 1 && tieneDecimales )
    			 texto.append("punto ");
    		 cont ++;
    	 }
    	 
    	 return texto.toString().toLowerCase();
    }
    
    public static String allNumbersToText(String texto) {
    	String[] arrayPalabras = texto.split(" ");
    	StringBuilder sb = new StringBuilder();
    	int contPalabra = 1;
    	int numPalabras = arrayPalabras.length;
    	for(String palabra : arrayPalabras) {
    		
    		if(palabra.matches(PATTERN_MUMERIC)) {
    			sb.append(numeroATexto(palabra));
    		}else {
    			sb.append(palabra);
    		}
    		
    		if(contPalabra < numPalabras) {
    			sb.append(" ");
    		}
    		
    	}
    	
    	return sb.toString();
    	
    }

}

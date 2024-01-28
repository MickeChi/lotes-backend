package com.lotes.lotesbackend.utils;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;

public class NumberUtils {


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

}

package br.com.lulira.request.converters;


// classe responsável por fazer a conversão e a validação dos números.
public class NumberConvert {



   public static Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedOperationException("Please set a numeric value");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);

    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty())
            return false;
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }
}

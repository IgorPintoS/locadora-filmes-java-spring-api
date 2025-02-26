package com.locadora.filmes.services.utility;

import com.locadora.filmes.exceptions.CpfCnpjInvalidException;

import java.util.regex.Pattern;

public class CpfCnpjFormatar {

    public static final Pattern CPF_FORMATADO = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
    public static final Pattern CNPJ_FORMATADO = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");

    public static String formatarCpfCnpj(String cpfCnpj) {

        if(cpfCnpj == null) {
            return null;
        }

        cpfCnpj = cpfCnpj.replaceAll("\\D", "");

        if(cpfCnpj.length() == 11) {
            return CPF_FORMATADO.matcher(cpfCnpj).replaceAll("$1.$2.$3-$4");
        } else if(cpfCnpj.length() == 14){
            return CNPJ_FORMATADO.matcher(cpfCnpj).replaceAll("$1.$2.$3/$4-$5");
        } else {
            throw new CpfCnpjInvalidException("CPF/CNPJ com quantidade de dígitos inválida.");
        }

    }

    public static boolean validarCpfCnpj(String cpfCnpj) {
        if(cpfCnpj == null){
            return false;
        }

        cpfCnpj.replaceAll("\\D", "");

        return cpfCnpj.length() == 11 || cpfCnpj.length() == 14;
    }
}

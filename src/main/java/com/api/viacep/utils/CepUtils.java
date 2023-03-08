package com.api.viacep.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class CepUtils {
    private static final Pattern CEP_PATTERN = Pattern.compile("[0-9]{8}");

    public static boolean isCep(final String cep){
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        String nrCep = cep.replace("-","");
        Matcher matcher = CEP_PATTERN.matcher(nrCep);
        return matcher.matches();
    }
}

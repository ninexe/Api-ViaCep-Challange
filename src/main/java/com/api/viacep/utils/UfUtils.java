package com.api.viacep.utils;

import com.api.viacep.enums.RegiaoEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UfUtils {
    public static Double getFrete(final String uf){
        RegiaoEnum regiaoEnum;
        switch (uf) {
            case "SP":
            case "RJ":
            case "MG":
            case "ES":
                regiaoEnum = RegiaoEnum.SUDESTE;
                break;
            case "MS":
            case "MT":
            case "GO":
            case "DF":
                regiaoEnum = RegiaoEnum.CENTRO_OESTE;
                break;
            case "BA":
            case "SE":
            case "AL":
            case "PE":
            case "PB":
            case "RN":
            case "CE":
            case "PI":
            case "MA":
                regiaoEnum = RegiaoEnum.NORDESTE;
                break;
            case "RS":
            case "SC":
            case "PR":
                regiaoEnum = RegiaoEnum.SUL;
                break;
            case "AM":
            case "RR":
            case "AP":
            case "PA":
            case "TO":
            case "RO":
            case "AC":
                regiaoEnum = RegiaoEnum.NORTE;
                break;
            default:
                throw new IllegalStateException(String.format("Uf %s não mapeada na classe RegiaoEnum", uf));
        }
        return getFreteByRegiao(regiaoEnum);
    }
    private static Double getFreteByRegiao(final RegiaoEnum regiaoEnum){
        Double vlFrete = null;
        switch (regiaoEnum) {
            case SUDESTE:
                vlFrete = 7.85;
                break;
            case CENTRO_OESTE:
                vlFrete = 12.50;
                break;
            case NORDESTE:
                vlFrete = 15.98;
                break;
            case SUL:
                vlFrete = 17.30;
                break;
            case NORTE:
                vlFrete = 20.83;
                break;
        }
        return vlFrete;
    }

}

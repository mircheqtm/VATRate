package cz.cvut.fel.grishmir.vatrate.util;

import cz.cvut.fel.grishmir.vatrate.model.Country;

import java.util.List;

public class StatisticHelper {

    public static boolean isAlreadyInList(Country countryToCheck, List<Country> countries){
        for (Country c : countries){
            if(c.getIsoDuplicate() != null &&
                    (c.getIsoDuplicate().equals(countryToCheck.getCode()) || c.getCode().equals(countryToCheck.getIsoDuplicate()))){
                return true;
            }
        }
        return false;
    }
}

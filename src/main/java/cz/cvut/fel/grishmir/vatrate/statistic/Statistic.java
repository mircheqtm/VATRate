package cz.cvut.fel.grishmir.vatrate.statistic;

import cz.cvut.fel.grishmir.vatrate.model.Country;

import java.util.List;

//This could be shorter, but foe me it is more understandable, when code like this is "duplicated"
public class Statistic {

    public Country[] findTwoLowestVATRate(List<Country> countries) {
        if (countries == null || countries.size() < 2) {
            throw new IllegalArgumentException("List of countries cannot be null or have less then two elements");
        }

        Country first, second;
        first = second = Country.builder()
                .standardRate(Float.MAX_VALUE)
                .build();

        for (Country country : countries) {
            Float curRate = country.getStandardRate();

            if (curRate != null && curRate < first.getStandardRate()) {
                second = first;
                first = country;
            } else if (curRate != null && curRate < second.getStandardRate())
                second = country;
        }

        Country[] lowestStandardVATRate = new Country[2];
        lowestStandardVATRate[0] = first;
        lowestStandardVATRate[1] = second;
        return lowestStandardVATRate;
    }

    public Country[] findTwoHighestVATRate(List<Country> countries) {
        if (countries == null || countries.size() < 2) {
            throw new IllegalArgumentException("List of countries cannot be null or have less then two elements");
        }

        Country first, second;
        first = second = Country.builder()
                .standardRate(0f)
                .build();

        for (Country country : countries) {
            Float curRate = country.getStandardRate();
            if (curRate != null && curRate > first.getStandardRate()) {
                second = first;
                first = country;
            } else if (curRate != null && curRate > second.getStandardRate())
                second = country;
        }

        Country[] lowestStandardVATRate = new Country[2];
        lowestStandardVATRate[0] = first;
        lowestStandardVATRate[1] = second;
        return lowestStandardVATRate;
    }
}

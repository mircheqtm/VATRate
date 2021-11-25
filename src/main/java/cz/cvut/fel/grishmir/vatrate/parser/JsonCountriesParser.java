package cz.cvut.fel.grishmir.vatrate.parser;

import cz.cvut.fel.grishmir.vatrate.model.Country;
import cz.cvut.fel.grishmir.vatrate.util.StatisticHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonCountriesParser implements CountriesParser{

    public List<Country> parse(String json) throws ParseException {
        List<Country> countries = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(json);
        JSONObject rates = (JSONObject) jsonResponse.get("rates");

        //Not sure about this :(
        Set<String> keySet = rates.keySet();
        for (String key : keySet) {
            JSONObject countryInfo = (JSONObject) rates.get(key);

            Country country = Country.builder()
                    .code(key)
                    .comment(parseStringValue(countryInfo, "_comment"))
                    .isoDuplicate(parseStringValue(countryInfo, "iso_duplicate"))
                    .countryName(parseStringValue(countryInfo, "country"))
                    .standardRate(parseFloatValue(countryInfo, "standard_rate"))
                    .reducedRate(parseFloatValue(countryInfo, "reduced_rate"))
                    .reducedRateAlt(parseFloatValue(countryInfo, "reduced_rate_alt"))
                    .superReducedRate(parseFloatValue(countryInfo, "super_reduced_rate"))
                    .parkingRate(parseFloatValue(countryInfo, "parking_rate"))
                    .build();

            //Removing redundancy
            if(country.getIsoDuplicate() != null && !StatisticHelper.isAlreadyInList(country, countries)){
                continue;
            }
            countries.add(country);
        }
        return countries;
    }

    private Float parseFloatValue(JSONObject countryInfo, String key) {
        float floatRate;
        if (countryInfo.get(key).equals(false)) {
            return null;
        }
        floatRate = Float.parseFloat(countryInfo.get(key).toString());
        return floatRate;
    }

    private String parseStringValue(JSONObject countryInfo, String key) {
        Object value = countryInfo.get(key);
        if (countryInfo.get(key) == null) {
            return null;
        }
        return value.toString();
    }


}

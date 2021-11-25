package cz.cvut.fel.grishmir.vatrate;

import cz.cvut.fel.grishmir.vatrate.datareader.HttpReader;
import cz.cvut.fel.grishmir.vatrate.model.Country;
import cz.cvut.fel.grishmir.vatrate.parser.CountriesParser;
import cz.cvut.fel.grishmir.vatrate.parser.JsonCountriesParser;
import cz.cvut.fel.grishmir.vatrate.statistic.Statistic;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        String path = "http://5.189.172.205:15500/vat-rates";
        CountriesParser jsonParser = new JsonCountriesParser();
        HttpReader reader = new HttpReader(jsonParser);
        List<Country> countries = reader.readData(path);

        Statistic statistic = new Statistic();
        System.out.println("Highest standard VAT rate :");
        System.out.println(Arrays.toString(statistic.findTwoHighestVATRate(countries)));
        System.out.println("Lowest standard VAT rate :");
        System.out.println(Arrays.toString(statistic.findTwoLowestVATRate(countries)));

    }
}

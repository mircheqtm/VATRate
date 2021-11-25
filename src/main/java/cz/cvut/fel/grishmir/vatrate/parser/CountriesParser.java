package cz.cvut.fel.grishmir.vatrate.parser;

import cz.cvut.fel.grishmir.vatrate.model.Country;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface CountriesParser {
    List<Country> parse(String input) throws ParseException;
}

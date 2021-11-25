package cz.cvut.fel.grishmir.vatrate.datareader;

import cz.cvut.fel.grishmir.vatrate.model.Country;
import cz.cvut.fel.grishmir.vatrate.parser.CountriesParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpReader {
    private final CountriesParser parser;

    public HttpReader(CountriesParser parser) {
        this.parser = parser;
    }

    public List<Country> readData(String path) throws IOException, InterruptedException, ParseException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        if(response.statusCode() != 200){
            throw new IllegalArgumentException("Failed loading data from" + path);
        }

        return parser.parse(body);
    }

}

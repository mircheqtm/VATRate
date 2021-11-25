package cz.cvut.fel.grishmir.vatrate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {

    private String comment;

    private String isoDuplicate;

    private String code;

    private String countryName;

    private Float standardRate;

    private Float reducedRate;

    private Float reducedRateAlt;

    private Float superReducedRate;

    private Float parkingRate;
}

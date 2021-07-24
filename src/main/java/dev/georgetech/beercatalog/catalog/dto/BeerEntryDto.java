package dev.georgetech.beercatalog.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEntryDto {

    @NotBlank
    String beerId;
    @NotBlank
    String beerName;

    @NotBlank
    String location;
    @PastOrPresent
    LocalDate date;
    @Min(0)
    @Max(5)
    Integer rating;
    @Size(max = 255)
    String comments;
}

package uk.co.softwebweavers.incometax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaxableRange {

  @JsonProperty("start")
  private Double start;
  @JsonProperty("end")
  private Double end;
  @JsonProperty("percentage")
  private Double percentage;
}

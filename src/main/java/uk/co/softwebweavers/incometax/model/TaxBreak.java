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
public class TaxBreak {

  @JsonProperty("personal_allowance")
  private Double personalAllowance;
  @JsonProperty("base_rate")
  private TaxableRange basicRate;
  @JsonProperty("higher_rate")
  private TaxableRange higherRate;
  @JsonProperty("additional_rate")
  private TaxableRange additionalRate;
  @JsonProperty("ni_allowance")
  private Double niAllowance;
  @JsonProperty("ni_primary")
  private TaxableRange niPrimary;
  @JsonProperty("ni_upper_limit")
  private TaxableRange niUpperLimit;
}

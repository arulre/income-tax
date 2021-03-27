package uk.co.softwebweavers.incometax.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_EMPTY)
public class IncomeTax {

  @JsonProperty("basic_tax")
  private Double basicTax;
  @JsonProperty("higher_tax")
  private Double higherTax;
  @JsonProperty("additional_tax")
  private Double additionalTax;
  @JsonProperty("total_taxable_income")
  private Double totalTaxableIncome;
  @JsonProperty("total_tax")
  private Double totalTax;
  @JsonProperty("income_after_tax")
  private Double IncomeAfterTax;

  @JsonProperty("ni_primary")
  private Double niPrimary;
  @JsonProperty("ni_upper_limit")
  private Double niUpperLimit;
  @JsonProperty("total_ni")
  private Double totalNi;
}

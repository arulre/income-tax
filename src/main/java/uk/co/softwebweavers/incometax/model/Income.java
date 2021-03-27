package uk.co.softwebweavers.incometax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Income {

  @JsonProperty("salary")
  private Double salary = 0.00;
  @JsonProperty("bonus")
  private Double bonus = 0.00;
  @JsonProperty("other_income")
  private Double otherIncome = 0.00;
  @JsonProperty("salary_sacrified")
  private SalarySacrified salarySacrified;
  @JsonProperty("income_tax")
  private IncomeTax incomeTax;
}


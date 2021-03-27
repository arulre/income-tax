package uk.co.softwebweavers.incometax.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SalarySacrified {

  @JsonProperty("child_voucher")
  private Double childVoucher;
  @JsonProperty("pension")
  private Double pension;
  @JsonProperty("share_scheme")
  private Double shareScheme;
  @JsonProperty("others")
  private Double others;
}

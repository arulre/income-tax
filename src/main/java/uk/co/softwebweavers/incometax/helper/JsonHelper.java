package uk.co.softwebweavers.incometax.helper;

import uk.co.softwebweavers.incometax.model.TaxBreak;
import uk.co.softwebweavers.incometax.model.TaxableRange;

public class JsonHelper {

  public static TaxBreak getTaxBreak(String filename) {

    return TaxBreak.builder()
        .personalAllowance(12500.00)
        .basicRate(TaxableRange.builder().start(12501.00).end(50000.00).percentage(20.00).build())
        .higherRate(TaxableRange.builder().start(50000.00).end(150000.00).percentage(40.00).build())
        .additionalRate(TaxableRange.builder().start(150001.00).end(10000000.00).percentage(45.00).build())
        .niAllowance(9504.00)
        .niPrimary(TaxableRange.builder().start(9504.00).end(50004.00).percentage(12.00).build())
        .niUpperLimit(TaxableRange.builder().start(50004.00).end(1000000000.00).percentage(2.00).build())
        .build();
  }
}

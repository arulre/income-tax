package uk.co.softwebweavers.incometax.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import uk.co.softwebweavers.incometax.helper.JsonHelper;
import uk.co.softwebweavers.incometax.model.Income;
import uk.co.softwebweavers.incometax.model.IncomeTax;
import uk.co.softwebweavers.incometax.model.TaxBreak;

@Service
@Slf4j
public class IncomeTaxCalculatorService {

  ObjectMapper objectMapper;
  TaxEngine taxEngine;

  public IncomeTaxCalculatorService(@Autowired ObjectMapper objectMapper,
      @Autowired TaxEngine taxEngine) {
    this.objectMapper = objectMapper;
    this.taxEngine = taxEngine;
  }
  public Income calculateIncomeTax(Income income, String taxYear) throws IOException {
//    TaxBreak taxBreak = getTaxBread(taxYear);
    TaxBreak taxBreak = JsonHelper.getTaxBreak("2020-21");
    taxEngine.calculate(income, taxBreak);
    return income;
  }

//  public TaxBreak getTaxBread(String taxYear) throws IOException {
//      File file = ResourceUtils.getFile(taxYear+".json");
//      return objectMapper.readValue(file, TaxBreak.class);
//  }
}

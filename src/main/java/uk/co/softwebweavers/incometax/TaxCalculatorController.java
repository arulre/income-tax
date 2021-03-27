package uk.co.softwebweavers.incometax;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.softwebweavers.incometax.model.Income;
import uk.co.softwebweavers.incometax.model.IncomeTax;
import uk.co.softwebweavers.incometax.service.IncomeTaxCalculatorService;

@RestController
@Slf4j
public class TaxCalculatorController {

  private IncomeTaxCalculatorService incomeTaxCalculatorService;
  private TaxCalculatorController(@Autowired IncomeTaxCalculatorService incomeTaxCalculatorService)  {
    this.incomeTaxCalculatorService = incomeTaxCalculatorService;
  }

  @RequestMapping(path = "/income/{taxYear}",
      method =  RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Income> getIncomeTaxDetails(@PathVariable String taxYear, @RequestBody Income income) {
    try {
      incomeTaxCalculatorService.calculateIncomeTax(income, taxYear);
      return ResponseEntity.status((HttpStatus.OK)).body(income);
    } catch (Exception e) {
      log.error("Something wrong", e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
  }
}

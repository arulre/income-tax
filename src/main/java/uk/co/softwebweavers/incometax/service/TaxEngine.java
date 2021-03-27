package uk.co.softwebweavers.incometax.service;

import org.springframework.stereotype.Component;
import uk.co.softwebweavers.incometax.model.Income;
import uk.co.softwebweavers.incometax.model.IncomeTax;
import uk.co.softwebweavers.incometax.model.SalarySacrified;
import uk.co.softwebweavers.incometax.model.TaxBreak;
import uk.co.softwebweavers.incometax.model.TaxableRange;

@Component
public class TaxEngine {

  public Income calculate(Income income, TaxBreak taxBreak) {

    calculateTaxableIncome(income);
    applySalarySacrification(income);
    calculateIncomeTax(income, taxBreak);
    calculateNationalInsurance(income, taxBreak);
    return income;
  }

  private Income calculateTaxableIncome(Income income) {
    IncomeTax incomeTax =  new IncomeTax();
    incomeTax.setTotalTaxableIncome(income.getSalary() + income.getBonus() + income.getOtherIncome());
    income.setIncomeTax(incomeTax);
    return income;
  }

  private Income applySalarySacrification(Income income) {
    Double totalTaxableIncome = income.getIncomeTax().getTotalTaxableIncome();
    SalarySacrified salarySacrified = income.getSalarySacrified();
    if(salarySacrified == null) return income;
    if(salarySacrified.getPension() > 0) {
      totalTaxableIncome = totalTaxableIncome - salarySacrified.getPension();
    }
    if(salarySacrified.getChildVoucher() > 0) {
      totalTaxableIncome = totalTaxableIncome - salarySacrified.getChildVoucher();
    }
    if(salarySacrified.getShareScheme() > 0) {
      totalTaxableIncome = totalTaxableIncome - salarySacrified.getShareScheme();
    }
    if(salarySacrified.getOthers() > 0) {
      totalTaxableIncome = totalTaxableIncome - salarySacrified.getOthers();
    }
    income.getIncomeTax().setTotalTaxableIncome(totalTaxableIncome);
    return income;
  }

  private Income calculateIncomeTax(Income income, TaxBreak taxBreak) {
    IncomeTax incomeTax = income.getIncomeTax();
    Double totalTax = 0.0;
    Double availableTaxableIncome = incomeTax.getTotalTaxableIncome();
    TaxableRange additionaRate = taxBreak.getAdditionalRate();
    if(availableTaxableIncome > additionaRate.getStart()) {
      incomeTax.setAdditionalTax((availableTaxableIncome - additionaRate.getStart()) * additionaRate.getPercentage() / 100);
      availableTaxableIncome = additionaRate.getStart() - 1;
      totalTax = totalTax + incomeTax.getAdditionalTax();
    }
    TaxableRange higherRate = taxBreak.getHigherRate();
    if(availableTaxableIncome > higherRate.getStart()) {
      incomeTax.setHigherTax((availableTaxableIncome - higherRate.getStart()) * higherRate.getPercentage() / 100);
      availableTaxableIncome = higherRate.getStart() - 1;
      totalTax = totalTax + incomeTax.getHigherTax();
    }
    TaxableRange basicRate = taxBreak.getBasicRate();
    if(availableTaxableIncome > basicRate.getStart()) {
      incomeTax.setBasicTax((availableTaxableIncome - basicRate.getStart()) * basicRate.getPercentage() / 100);
      availableTaxableIncome = basicRate.getStart() - 1;
      totalTax = totalTax + incomeTax.getBasicTax();
    }
    incomeTax.setTotalTax(totalTax);
    incomeTax.setIncomeAfterTax(incomeTax.getTotalTaxableIncome() - incomeTax.getTotalTax());
    income.setIncomeTax(incomeTax);
    return income;
  }

  private Income calculateNationalInsurance(Income income, TaxBreak taxBreak) {
    IncomeTax incomeTax = income.getIncomeTax();
    Double totalNI = 0.0;
    Double niPayableFor = incomeTax.getTotalTaxableIncome();
    TaxableRange niUpperLimit = taxBreak.getNiUpperLimit();
    if(niPayableFor > niUpperLimit.getStart()) {
      incomeTax.setNiUpperLimit((niPayableFor - niUpperLimit.getStart()) * niUpperLimit.getPercentage() / 100);
      niPayableFor = niUpperLimit.getStart() - 1;
      totalNI = totalNI + incomeTax.getNiUpperLimit();
    }
    TaxableRange niPrimary = taxBreak.getNiPrimary();
    if(niPayableFor > niPrimary.getStart()) {
      incomeTax.setNiPrimary((niPayableFor - niPrimary.getStart()) * niPrimary.getPercentage() / 100);
      niPayableFor = niPrimary.getStart() - 1;
      totalNI = totalNI + incomeTax.getNiPrimary();
    }
    incomeTax.setTotalNi(totalNI);
    incomeTax.setIncomeAfterTax(incomeTax.getIncomeAfterTax() - incomeTax.getTotalNi());
    income.setIncomeTax(incomeTax);
    return income;
  }
}
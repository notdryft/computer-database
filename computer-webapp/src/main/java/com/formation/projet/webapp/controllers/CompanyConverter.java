package com.formation.projet.webapp.controllers;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.services.CompanyService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 07/06/13
 * Time: 11:17
 */
public class CompanyConverter extends PropertyEditorSupport {

    private CompanyService companyService;

    public CompanyConverter(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public String getAsText() {
        Company company = (Company) getValue();
        if (company == null) {
            return null;
        }

        return String.valueOf(company.getId());
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.trim().isEmpty()) {
            try {
                long id = Long.parseLong(text);
                setValue(companyService.find(id));
            } catch (NumberFormatException e) {
                setValue(null);
            }
        }
    }
}

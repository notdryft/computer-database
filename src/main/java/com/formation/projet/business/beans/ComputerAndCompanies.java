package com.formation.projet.business.beans;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:35
 */
public class ComputerAndCompanies {

    private Computer computer;

    private List<Company> companies;

    public ComputerAndCompanies() {
        // Do nothing.
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}

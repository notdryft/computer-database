package com.formation.projet.business.forms;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.beans.Computer;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 28/05/13
 * Time: 14:36
 */
public class ComputerForm {

    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    // Non empty
    private FormElement name;

    // Optional
    // Format: yyyy-MM-dd
    private FormElement introduced;

    // Optional
    // Format: yyyy-MM-dd
    private FormElement discontinued;

    // Optional
    // Long
    private FormElement company;

    public ComputerForm() {
        this.name = initFormElement("name", "Required");
        this.introduced = initFormElement("introduced", "Date ('yyyy-MM-dd')");
        this.discontinued = initFormElement("discontinued", "Date ('yyyy-MM-dd')");
        this.company = initFormElement("company");
    }

    private FormElement initFormElement(String name) {
        return initFormElement(name, null);
    }

    private FormElement initFormElement(String name, String message) {
        FormElement formElement = new FormElement();
        formElement.setName(name);

        if (message != null) {
            formElement.addMessage(message);
        }

        return formElement;
    }

    public FormElement getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public FormElement getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced.setValue(introduced);
    }

    public FormElement getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued.setValue(discontinued);
    }

    public FormElement getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company.setValue(company);
    }

    public Computer toComputer() {
        if (!isValid()) {
            return null;
        }

        Computer computer = new Computer();
        computer.setName((String) name.getValueObject());
        computer.setIntroduced((Date) introduced.getValueObject());
        computer.setDiscontinued((Date) discontinued.getValueObject());

        if (company.getValueObject() != null) {
            Company company = new Company();
            company.setId((Long) this.company.getValueObject());

            computer.setCompany(company);
        }

        return computer;
    }

    private void validate() {
        // name
        String name = this.name.getValue();
        if (name == null || name.isEmpty()) {
            this.name.setValid(false);
        } else {
            this.name.setValid(true);
            this.name.setValueObject(name);
        }

        // introduced
        String introduced = this.introduced.getValue();
        if (introduced != null && !introduced.isEmpty()) {
            try {
                Date d = new Date(fmt.parse(introduced).getTime());

                this.introduced.setValid(true);
                this.introduced.setValueObject(d);
            } catch (ParseException e) {
                this.introduced.setValid(false);
            }
        }

        // discontinued
        String discontinued = this.discontinued.getValue();
        if (discontinued != null && !discontinued.isEmpty()) {
            try {
                Date d = new Date(fmt.parse(discontinued).getTime());

                this.discontinued.setValid(true);
                this.discontinued.setValueObject(d);
            } catch (ParseException e) {
                this.discontinued.setValid(false);
            }
        }

        // company
        String company = this.company.getValue();
        if (company != null && !company.isEmpty()) {
            try {
                Long l = Long.parseLong(company);

                this.company.setValid(true);
                this.company.setValueObject(l);
            } catch (NumberFormatException e) {
                this.company.setValid(false);
            }
        }
    }

    public boolean isValid() {
        validate();

        return name.isValid()
                && introduced.isValid()
                && discontinued.isValid()
                && company.isValid();
    }
}

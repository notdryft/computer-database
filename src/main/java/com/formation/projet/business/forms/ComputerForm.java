package com.formation.projet.business.forms;

import com.formation.projet.application.annotations.Form;
import com.formation.projet.application.forms.FormElement;
import com.formation.projet.business.beans.Company;
import com.formation.projet.business.beans.Computer;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 28/05/13
 * Time: 14:36
 */
@Form(Computer.class)
public class ComputerForm {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static {
        // Strict format, checks leap years
        dateFormat.setLenient(false);
    }

    // Ignored
    private Long id;

    // Non empty
    private final FormElement<String> name;

    // Optional
    // Format: yyyy-MM-dd
    private final FormElement<Date> introduced;

    // Optional
    // Format: yyyy-MM-dd
    // Greater than introduced
    private final FormElement<Date> discontinued;

    // Optional
    // Long
    private final FormElement<Long> company;

    public ComputerForm() {
        this.id = null;
        this.name = new FormElement<String>("name", "Required");
        this.introduced = new FormElement<Date>("introduced", "Date ('yyyy-MM-dd')");
        this.discontinued = new FormElement<Date>("discontinued", "Date ('yyyy-MM-dd')");
        this.company = new FormElement<Long>("company");
    }

    public ComputerForm(Computer computer) {
        this();

        this.id = computer.getId();
        this.name.setValue(computer.getName());

        if (computer.getIntroduced() != null) {
            this.introduced.setValue(dateFormat.format(computer.getIntroduced()));
        }

        if (computer.getDiscontinued() != null) {
            this.discontinued.setValue(dateFormat.format(computer.getDiscontinued()));
        }

        if (computer.getCompany() != null) {
            this.company.setValue(String.valueOf(computer.getCompany().getId()));
        }

        if (!isValid()) {
            throw new IllegalArgumentException("Impossible to construct a ComputerForm from this Computer: invalid parameters");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        if (id != null) {
            computer.setId(id);
        }

        computer.setName(name.getValueObject());
        computer.setIntroduced(introduced.getValueObject());
        computer.setDiscontinued(discontinued.getValueObject());

        if (company.getValueObject() != null) {
            Company company = new Company();
            company.setId(this.company.getValueObject());

            computer.setCompany(company);
        }

        return computer;
    }

    private void validate() {
        // name
        String name = this.name.getValue();
        if (name == null || name.isEmpty()) {
            this.name.invalidate();
        } else {
            this.name.validate(name);
        }

        // introduced
        String introduced = this.introduced.getValue();
        Date introducedDate = null;
        if (introduced != null && !introduced.isEmpty()) {
            try {
                introducedDate = new Date(dateFormat.parse(introduced).getTime());

                this.introduced.validate(introducedDate);
            } catch (ParseException e) {
                this.introduced.invalidate("invalid date format");
            }
        }

        // discontinued
        String discontinued = this.discontinued.getValue();
        if (discontinued != null && !discontinued.isEmpty()) {
            try {
                Date discontinuedDate = new Date(dateFormat.parse(discontinued).getTime());

                if (introducedDate == null || introducedDate.before(discontinuedDate)) {
                    this.discontinued.validate(discontinuedDate);
                } else {
                    this.discontinued.invalidate("should be after introduction date");
                }
            } catch (ParseException e) {
                this.discontinued.invalidate("invalid date format");
            }
        }

        // company
        String company = this.company.getValue();
        if (company != null && !company.isEmpty()) {
            try {
                Long l = Long.parseLong(company);

                this.company.validate(l);
            } catch (NumberFormatException e) {
                this.company.invalidate();
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

package com.formation.projet.business.beans;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 14:06
 */
public class Computer {

    private int id;

    private String name;

    private Date introducedDate;

    private Date discontinuedDate;

    private int companyId;

    public Computer() {
        // Do nothing.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIntroducedDate() {
        return introducedDate;
    }

    public void setIntroducedDate(Date introducedDate) {
        this.introducedDate = introducedDate;
    }

    public Date getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(Date discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}

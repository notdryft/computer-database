package com.formation.projet.business.services;

import com.formation.projet.business.beans.Company;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:46
 */
public interface CompanyService {

    List<Company> findAll();
}

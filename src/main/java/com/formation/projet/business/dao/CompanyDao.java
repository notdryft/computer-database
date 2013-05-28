package com.formation.projet.business.dao;

import com.formation.projet.business.beans.Company;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:00
 */
public interface CompanyDao {

    List<Company> findAll();
}

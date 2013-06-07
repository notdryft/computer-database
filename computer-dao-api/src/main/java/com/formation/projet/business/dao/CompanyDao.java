package com.formation.projet.business.dao;

import com.formation.projet.business.beans.Company;
import com.formation.projet.core.exceptions.DaoException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:00
 */
public interface CompanyDao {

    Company find(long id) throws DaoException;

    List<Company> findAll() throws DaoException;
}

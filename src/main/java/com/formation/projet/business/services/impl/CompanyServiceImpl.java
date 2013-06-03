package com.formation.projet.business.services.impl;

import com.formation.projet.application.exceptions.DaoException;
import com.formation.projet.application.exceptions.ServiceException;
import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.impl.CompanyDaoImpl;
import com.formation.projet.business.services.CompanyService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:46
 */
public enum CompanyServiceImpl implements CompanyService {
    instance;

    private final CompanyDao companyDao;

    private CompanyServiceImpl() {
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies;
        try {
            companies = companyDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while calling findAll()", e);
        }

        return companies;
    }
}

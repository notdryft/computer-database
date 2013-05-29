package com.formation.projet.business.services;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.CompanyDaoImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:46
 */
public enum CompanyServiceImpl implements CompanyService {
    instance;

    private CompanyDao companyDao;

    private CompanyServiceImpl() {
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }
}

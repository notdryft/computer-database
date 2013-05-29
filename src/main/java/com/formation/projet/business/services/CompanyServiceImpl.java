package com.formation.projet.business.services;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.CompanyDaoImpl;
import com.formation.projet.business.dao.ConnectionFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:46
 */
public enum CompanyServiceImpl implements CompanyService {
    instance;

    private ConnectionFactory factory;

    private CompanyDao companyDao;

    private CompanyServiceImpl() {
        factory = ConnectionFactory.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public List<Company> findAll() {
        factory.openConnection();

        List<Company> companies = companyDao.findAll();

        factory.closeConnection();

        return companies;
    }
}

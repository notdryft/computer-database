package com.formation.projet.business.services;

import com.formation.projet.application.exceptions.DaoException;
import com.formation.projet.application.exceptions.ServiceException;
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

    private final ConnectionFactory factory;

    private final CompanyDao companyDao;

    private CompanyServiceImpl() {
        factory = ConnectionFactory.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public List<Company> findAll() {
        factory.openConnection();

        List<Company> companies;
        try {
            companies = companyDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while calling findAll()", e);
        } finally {
            factory.closeConnection();
        }

        return companies;
    }
}

package com.formation.projet.business.services.impl;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.services.CompanyService;
import com.formation.projet.connection.ConnectionFactory;
import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.core.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:46
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private final ConnectionFactory factory;

    @Autowired
    private CompanyDao companyDao;

    public CompanyServiceImpl() {
        factory = ConnectionFactory.instance;
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

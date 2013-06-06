package com.formation.projet.business.services.impl;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.services.CompanyService;
import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.core.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 15:46
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    @Transactional
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

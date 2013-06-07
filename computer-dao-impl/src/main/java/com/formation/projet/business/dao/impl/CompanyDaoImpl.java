package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.core.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 28/05/13
 * Time: 12:21
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(readOnly = true)
    public Company find(long id) throws DaoException {
        Company company;

        try {
            company = hibernateTemplate.get(Company.class, id);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling find(long)");
        }

        return company;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Company> findAll() throws DaoException {
        List<Company> companies;

        try {
            companies = (List<Company>) hibernateTemplate.find("from Company order by name");
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling findAll()", e);
        }

        return companies;
    }
}

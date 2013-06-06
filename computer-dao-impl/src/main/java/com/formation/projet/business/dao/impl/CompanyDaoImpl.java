package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.core.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_QUERY =
            "SELECT l.id, l.name " +
                    "FROM company l " +
                    "ORDER BY l.name ASC";

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() throws DaoException {
        List<Company> companies;

        try {
            companies = jdbcTemplate.query(FIND_ALL_QUERY, new CompanyMapper());
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling findAll()", e);
        }

        return companies;
    }
}

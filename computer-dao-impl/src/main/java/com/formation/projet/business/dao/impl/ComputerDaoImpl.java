package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.core.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.formation.projet.business.dao.impl.ComputerQueryFactory.*;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:40
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Computer find(long id) throws DaoException {
        Computer computer;

        try {
            computer = jdbcTemplate.queryForObject(makeFindQuery(), new Object[]{id}, new ComputerMapper());
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling find(int)", e);
        }

        return computer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Computer> findAll(PageState pageState) throws DaoException {
        List<Computer> computers;

        try {
            computers = jdbcTemplate.query(makeFindAllStatement(pageState), new ComputerMapper());
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling findAll(PageState)", e);
        }

        return computers;
    }

    @Override
    @Transactional
    public Computer create(Computer computer) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(makeCreateStatement(computer), keyHolder);

            computer.setId(keyHolder.getKey().longValue());
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling create(Computer)", e);
        }

        return computer;
    }

    @Override
    @Transactional
    public Computer update(Computer computer) throws DaoException {
        try {
            jdbcTemplate.update(makeUpdateStatement(computer));
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling update(Computer)", e);
        }

        return find(computer.getId());
    }

    @Override
    @Transactional
    public void delete(Computer computer) throws DaoException {
        try {
            jdbcTemplate.update(makeDeleteStatement(computer));
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling delete(Computer)", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int count(String filter) throws DaoException {
        int count;
        try {
            count = jdbcTemplate.queryForObject(makeCountQuery(), new Object[]{'%' + filter + '%'}, Integer.class);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling count(String)", e);
        }

        return count;
    }
}

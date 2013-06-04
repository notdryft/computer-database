package com.formation.projet.business.dao;

import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.PageState;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:00
 */
public interface ComputerDao {

    Computer find(long id) throws DaoException;

    List<Computer> findAll(PageState pageState) throws DaoException;

    Computer create(Computer computer) throws DaoException;

    Computer update(Computer computer) throws DaoException;

    void delete(Computer computer) throws DaoException;

    int count(String filter) throws DaoException;
}

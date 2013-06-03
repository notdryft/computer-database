package com.formation.projet.business.services.impl;

import com.formation.projet.application.exceptions.DaoException;
import com.formation.projet.application.exceptions.ServiceException;
import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.impl.CompanyDaoImpl;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.dao.impl.ComputerDaoImpl;
import com.formation.projet.business.services.ComputerService;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 14:54
 */
public enum ComputerServiceImpl implements ComputerService {
    instance;

    private final ComputerDao computerDao;

    private final CompanyDao companyDao;

    private ComputerServiceImpl() {
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public ComputerAndCompanies findWithAllCompanies(long id) {
        ComputerAndCompanies computerAndCompanies = new ComputerAndCompanies();

        try {
            Computer computer = computerDao.find(id);
            if (computer == null) {
                return null;
            }

            computerAndCompanies.setComputer(computer);
            computerAndCompanies.setCompanies(companyDao.findAll());
        } catch (DaoException e) {
            throw new ServiceException("Error while calling findWithAllCompanies(long)", e);
        }

        return computerAndCompanies;
    }

    @Override
    public ComputersAndCount findAllAndCount(PageState pageState) {
        ComputersAndCount computersAndCount;
        try {
            computersAndCount = new ComputersAndCount();
            computersAndCount.setTotal(computerDao.count(pageState.getFilter()));
            computersAndCount.setComputers(computerDao.findAll(pageState));
        } catch (DaoException e) {
            throw new ServiceException("Error while calling findAllAndCount(PageState)", e);
        }

        return computersAndCount;
    }

    @Override
    public Computer create(Computer computer) {
        try {
            computer = computerDao.create(computer);
        } catch (DaoException e) {
            throw new ServiceException("Error while calling create(Computer)", e);
        }

        return computer;
    }

    @Override
    public Computer update(Computer computer) {
        try {
            computer = computerDao.update(computer);
        } catch (DaoException e) {
            throw new ServiceException("Error while calling update(Computer)", e);
        }

        return computer;
    }

    @Override
    public boolean seekAndDestroy(long id) {
        try {
            Computer computer = computerDao.find(id);
            if (computer == null) {
                return false;
            }

            computerDao.delete(computer);
        } catch (DaoException e) {
            throw new ServiceException("Error while calling seekAndDestroy(long)", e);
        }

        return true;
    }
}

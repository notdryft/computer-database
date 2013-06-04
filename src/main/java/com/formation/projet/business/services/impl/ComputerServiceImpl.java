package com.formation.projet.business.services.impl;

import com.formation.projet.core.connection.ConnectionFactory;
import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.core.exceptions.ServiceException;
import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.dao.impl.CompanyDaoImpl;
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

    private final ConnectionFactory factory;

    private final ComputerDao computerDao;

    private final CompanyDao companyDao;

    private ComputerServiceImpl() {
        factory = ConnectionFactory.instance;
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public ComputerAndCompanies findWithAllCompanies(long id) {
        factory.openConnection();

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
        } finally {
            factory.closeConnection();
        }

        return computerAndCompanies;
    }

    @Override
    public ComputersAndCount findAllAndCount(PageState pageState) {
        factory.openConnection();

        ComputersAndCount computersAndCount;
        try {
            computersAndCount = new ComputersAndCount();
            computersAndCount.setTotal(computerDao.count(pageState.getFilter()));
            computersAndCount.setComputers(computerDao.findAll(pageState));
        } catch (DaoException e) {
            throw new ServiceException("Error while calling findAllAndCount(PageState)", e);
        } finally {
            factory.closeConnection();
        }

        return computersAndCount;
    }

    @Override
    public Computer create(Computer computer) {
        factory.openConnection();

        try {
            computer = computerDao.create(computer);
        } catch (DaoException e) {
            throw new ServiceException("Error while calling create(Computer)", e);
        } finally {
            factory.closeConnection();
        }

        return computer;
    }

    @Override
    public Computer update(Computer computer) {
        factory.openConnection();

        try {
            computer = computerDao.update(computer);
        } catch (DaoException e) {
            throw new ServiceException("Error while calling update(Computer)", e);
        } finally {
            factory.closeConnection();
        }

        return computer;
    }

    @Override
    public boolean seekAndDestroy(long id) {
        factory.openConnection();

        try {
            Computer computer = computerDao.find(id);
            if (computer == null) {
                return false;
            }

            computerDao.delete(computer);
        } catch (DaoException e) {
            throw new ServiceException("Error while calling seekAndDestroy(long)", e);
        } finally {
            factory.closeConnection();
        }

        return true;
    }
}

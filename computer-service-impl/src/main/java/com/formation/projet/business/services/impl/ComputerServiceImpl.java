package com.formation.projet.business.services.impl;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.services.ComputerService;
import com.formation.projet.connection.ConnectionFactory;
import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.core.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 14:54
 */
@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ConnectionFactory factory;

    @Autowired
    private ComputerDao computerDao;

    @Autowired
    private CompanyDao companyDao;

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

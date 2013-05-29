package com.formation.projet.business.services;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.dao.*;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 14:54
 */
public enum ComputerServiceImpl implements ComputerService {
    instance;

    private ConnectionFactory factory;

    private ComputerDao computerDao;

    private CompanyDao companyDao;

    private ComputerServiceImpl() {
        factory = ConnectionFactory.instance;
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public ComputerAndCompanies findWithAllCompanies(long id) {
        factory.openConnection();

        ComputerAndCompanies computerAndCompanies = new ComputerAndCompanies();

        Computer computer = computerDao.find(id);
        if (computer == null) {
            return null;
        }

        computerAndCompanies.setComputer(computer);
        computerAndCompanies.setCompanies(companyDao.findAll());

        factory.closeConnection();

        return computerAndCompanies;
    }

    @Override
    public ComputersAndCount findAllAndCount(String filter, int sortColumn, int offset, int limit) {
        factory.openConnection();

        ComputersAndCount computersAndCount = new ComputersAndCount();
        computersAndCount.setTotal(computerDao.count(filter));
        computersAndCount.setComputers(computerDao.findAll(filter, sortColumn, offset, limit));

        factory.closeConnection();

        return computersAndCount;
    }

    @Override
    public Computer create(Computer computer) {
        factory.openConnection();

        computer = computerDao.create(computer);

        factory.closeConnection();

        return computer;
    }

    @Override
    public Computer update(Computer computer) {
        factory.openConnection();

        computer = computerDao.update(computer);

        factory.closeConnection();

        return computer;
    }

    @Override
    public boolean seekAndDestroy(long id) {
        factory.openConnection();

        Computer computer = computerDao.find(id);
        if (computer == null) {
            return false;
        }

        computerDao.delete(computer);

        factory.closeConnection();

        return true;
    }
}

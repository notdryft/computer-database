package com.formation.projet.business.services;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.CompanyDaoImpl;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.dao.ComputerDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 14:54
 */
public enum ComputerServiceImpl implements ComputerService {
    instance;

    private ComputerDao computerDao;

    private CompanyDao companyDao;

    private ComputerServiceImpl() {
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    public ComputerAndCompanies findWithAllCompanies(long id) {
        ComputerAndCompanies computerAndCompanies = new ComputerAndCompanies();

        Computer computer = computerDao.find(id);
        if (computer == null) {
            return null;
        }

        computerAndCompanies.setComputer(computer);
        computerAndCompanies.setCompanies(companyDao.findAll());

        return computerAndCompanies;
    }

    @Override
    public ComputersAndCount findAllAndCount(String filter, int sortColumn, int offset, int limit) {
        ComputersAndCount computersAndCount = new ComputersAndCount();
        computersAndCount.setTotal(computerDao.count(filter));
        computersAndCount.setComputers(computerDao.findAll(filter, sortColumn, offset, limit));

        return computersAndCount;
    }

    @Override
    public Computer create(Computer computer) {
        return computerDao.create(computer);
    }

    @Override
    public Computer update(Computer computer) {
        return computerDao.update(computer);
    }

    @Override
    public boolean seekAndDestroy(long id) {
        Computer computer = computerDao.find(id);
        if (computer == null) {
            return false;
        }

        computerDao.delete(computer);

        return true;
    }
}

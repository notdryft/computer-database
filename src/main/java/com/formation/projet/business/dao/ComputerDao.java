package com.formation.projet.business.dao;

import com.formation.projet.business.beans.Computer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:00
 */
public interface ComputerDao {

    Computer find(int id);

    List<Computer> findAll(String filter, int sortColumn, int offset, int limit);

    Computer findByName(String name);

    List<Computer> searchByName(String name);

    Computer create(Computer computer);

    Computer update(Computer computer);

    void delete(Computer computer);

    int count(String filter);
}

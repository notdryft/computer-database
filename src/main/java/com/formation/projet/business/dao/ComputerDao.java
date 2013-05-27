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

    List<Computer> findAll(int page, int limit, int orderedColumn);

    Computer findByName(String name);

    List<Computer> searchByName(String name);

    Computer create(Computer computer);

    Computer update(Computer computer);

    void delete(Computer computer);

    int count();
}

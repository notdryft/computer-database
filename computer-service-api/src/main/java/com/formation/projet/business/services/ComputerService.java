package com.formation.projet.business.services;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.beans.PageState;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 14:41
 */
public interface ComputerService {

    ComputerAndCompanies findWithAllCompanies(long id);

    ComputersAndCount findAllAndCount(PageState pageStage);

    Computer create(Computer computer);

    Computer update(Computer computer);

    boolean seekAndDestroy(long id);
}

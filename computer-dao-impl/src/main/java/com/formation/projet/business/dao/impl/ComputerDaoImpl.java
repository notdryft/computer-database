package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.core.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:40
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    private static final Map<Integer, String> COMPUTER_COLUMNS = new HashMap<Integer, String>();

    static {
        COMPUTER_COLUMNS.put(1, "l.id");
        COMPUTER_COLUMNS.put(2, "l.name");
        COMPUTER_COLUMNS.put(3, "l.introduced");
        COMPUTER_COLUMNS.put(4, "l.discontinued");
        COMPUTER_COLUMNS.put(5, "r.name");
    }

    @Override
    @Transactional(readOnly = true)
    public Computer find(long id) throws DaoException {
        Computer computer;

        try {
            computer = hibernateTemplate.get(Computer.class, id);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling find(long)", e);
        }

        return computer;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Computer> findAll(final PageState pageState) throws DaoException {
        List<Computer> computers;

        String column = COMPUTER_COLUMNS.get(Math.abs(pageState.getSortColumn()));

        StringBuilder sb = new StringBuilder();
        sb.append("from Computer l left outer join fetch l.company");
        sb.append(" where l.name like ?");
        sb.append(" order by ");
        sb.append(column);
        sb.append(pageState.getSortColumn() < 0 ? " desc" : " asc");

        final String query = sb.toString();

        try {
            computers = hibernateTemplate.executeFind(new HibernateCallback<Object>() {

                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Query q = session.createQuery(query);
                    q.setString(0, '%' + pageState.getFilter() + '%');
                    q.setFirstResult(pageState.getOffset());
                    q.setMaxResults(pageState.getMaxItemsPerPage());

                    return q.list();
                }
            });
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling findAll(PageState)", e);
        }

        return computers;
    }

    @Override
    @Transactional
    public Computer create(Computer computer) throws DaoException {
        Long id;

        try {
            id = (Long) hibernateTemplate.save(computer);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling create(Computer)", e);
        }

        return find(id);
    }

    @Override
    @Transactional
    public Computer update(Computer computer) throws DaoException {
        try {
            hibernateTemplate.update(computer);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling update(Computer)", e);
        }

        return find(computer.getId());
    }

    @Override
    @Transactional
    public void delete(Computer computer) throws DaoException {
        try {
            hibernateTemplate.delete(computer);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling delete(Computer)", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int count(String filter) throws DaoException {
        Long count;
        try {
            List result = hibernateTemplate.find(
                    "select count(*) from Computer where name like ?",
                    new Object[]{'%' + filter + '%'});
            count = (Long) result.get(0);
        } catch (DataAccessException e) {
            throw new DaoException("Error while calling count(String)", e);
        }

        System.out.println("count.intValue() = " + count.intValue());

        return count.intValue();
    }
}

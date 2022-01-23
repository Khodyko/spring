package org.example.spring.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.dao.ExceptionDao.DaoException;

import java.util.List;

import static org.apache.logging.log4j.Level.DEBUG;

public class ValidatorDao {
    private final static Logger logger = LogManager.getLogger();

    public ValidatorDao() {
        logger.log(DEBUG, this.getClass().getSimpleName() + " was created");
    }

    public  Boolean validateListForPage(int pageSize, int pageNum) throws DaoException {
        logger.log(DEBUG, Thread.currentThread()
                .getStackTrace()[1].getMethodName() + " method start");
        if (pageSize < 0 || pageNum < 0) {
            throw new DaoException("page must be positive number");
        } else {
            return true;
        }
    }
}

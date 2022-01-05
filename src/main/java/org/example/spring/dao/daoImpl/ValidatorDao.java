package org.example.spring.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring.dao.ExceptionDao.DaoException;

import java.util.List;

import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.INFO;

public class ValidatorDao {
    private final static Logger logger= LogManager.getLogger();
    public ValidatorDao() {
        logger.log(DEBUG, this.getClass().getSimpleName()+" was created");
    }

    public <T> Boolean validateListForPage(List<T> list, int pageSize, int pageNum) throws DaoException {
        logger.log(DEBUG, Thread.currentThread().getStackTrace()[1].getMethodName()+" method start");
        Integer sizeFullList = list.size();
        Integer pageMaxNum=(sizeFullList % pageSize>0?1:0)+
                            sizeFullList/pageSize;

        try {
            if (pageSize < 0 || pageNum < 0) {
                throw new IllegalArgumentException("page must be positive number");
            } else if (pageMaxNum < pageNum) {
                throw new IllegalArgumentException("We have only " + sizeFullList % pageSize +
                        " pages and page № " + pageNum + " is not exist");
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }
}

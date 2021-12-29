package org.example.spring.dao.daoImpl;

import org.example.spring.dao.ExceptionDao.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidatorDao {

    public <T> Boolean validateListForPage(List<T> list, int pageSize, int pageNum) throws DaoException {

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

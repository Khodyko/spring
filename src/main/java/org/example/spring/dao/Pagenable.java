package org.example.spring.dao;

import org.example.spring.dao.ExceptionDao.DaoException;

import java.util.List;

public interface Pagenable {
    public <T> List<T> getPagedList(List<T> list, int pageSize, int pageNum) throws DaoException;
}

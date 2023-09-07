package dao;

import entity.Student;

import java.sql.Date;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    String generateNextId();

    public boolean save(T entity);

    public boolean update(T entity);

    boolean delete(T student);

    List<T> getAll();
}

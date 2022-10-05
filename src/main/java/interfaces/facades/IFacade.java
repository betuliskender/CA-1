package interfaces.facades;

import java.util.List;

public interface IFacade <T>
{
    T getById(Integer id) throws Exception;
    List<T> getAll();
    T create(T t) throws Exception;

    T update(T t) throws Exception;

    T delete(Integer id) throws Exception;


}

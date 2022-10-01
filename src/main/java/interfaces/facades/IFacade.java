package interfaces.facades;

<<<<<<< HEAD
import dtos.CityInfoDto;
import entities.CityInfo;
=======
import dtos.PersonDto;
import entities.Person;
>>>>>>> personApiBranch

import java.util.List;

public interface IFacade <T>
{
    T getById(Integer id);
    List<T> getAll();
    T create(T t);

    T update(T t);
<<<<<<< HEAD
    T delete(Integer id);
=======

    T delete(T t);
>>>>>>> personApiBranch

}

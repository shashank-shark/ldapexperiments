package dapexp.dao;

import dapexp.domain.Person;

import java.util.List;

public interface PersonDao {
    List<Person> getAllPersons();
}

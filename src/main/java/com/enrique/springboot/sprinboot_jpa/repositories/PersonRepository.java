package com.enrique.springboot.sprinboot_jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enrique.springboot.sprinboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findByApellido1(String apellido1);

    @Query("select p from Person p where p.apellido1 = ?1 and p.apellido2 = ?2")
    List<Person> buscarPorApellidos(String apellido1, String apellido2);

    @Query("select p.nombre, p.grupo from Person p")
    List<Object[]> buscarPerdonasData();

}

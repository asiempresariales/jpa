package com.enrique.springboot.sprinboot_jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enrique.springboot.sprinboot_jpa.entities.Person;
import com.enrique.springboot.sprinboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SprinbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SprinbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		findOne();
		
	}

	public void findOne(){

		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(102);
		// if(optionalPerson.isPresent()){
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);
		repository.findById(107).ifPresent(person->System.out.println(person));
	}
	
	public void list(){
		
				//List<Person> persons = (List<Person>)repository.findAll();
		
				List<Person> persons = (List<Person>)repository.findByApellido1("Díaz");
				persons.stream().forEach(person->System.out.println(person));
				
				List<Person> personsDos = (List<Person>)repository.buscarPorApellidos("Molina", "Vergara");
				personsDos.stream().forEach(person->System.out.println(person));
				
				List<Object[]> personsTres = repository.buscarPerdonasData();
				personsTres.stream().forEach(person->System.out.println(person[0] + " " + person[1]));

	}



}

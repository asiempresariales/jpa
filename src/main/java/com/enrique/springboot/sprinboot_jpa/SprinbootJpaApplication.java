package com.enrique.springboot.sprinboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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

		create();
		
	}


	@Transactional(readOnly = true)
	public void buscarDistinctGrupos(){
		List<String> grupos = repository.findAllGruposDisctinct();
		grupos.stream().forEach(System.out::println);

	}

	@Transactional
	public void delete2(){

		repository.findAll().forEach(System.out::println);


		Scanner scanner = new Scanner(System.in);
		System.out.println(" Ingresa el Id de la persona");
		int id = scanner.nextInt();

		Optional<Person> personDb = repository.findById(id);

		//optionalPerson.ifPresentOrElse(person->repository.delete(person), ()->System.out.println("La persona no existe"));
		personDb.ifPresentOrElse(repository::delete, ()->System.out.println("La persona no existe"));

		scanner.close();

		repository.findAll().forEach(System.out::println);

	}

	@Transactional
	public void delete(){

		repository.findAll().forEach(System.out::println);


		Scanner scanner = new Scanner(System.in);
		System.out.println(" Ingresa el Id de la persona");
		int id = scanner.nextInt();

		repository.deleteById(id);

		scanner.close();

		repository.findAll().forEach(System.out::println);

	}

	@Transactional
	public void update(){

		Scanner scanner = new Scanner(System.in);
		System.out.println(" Ingresa el Id de la persona");
		int id = scanner.nextInt();

		Optional<Person> optionalPerson = repository.findById(id);

		if(optionalPerson.isPresent()){

			Person personDB = optionalPerson.orElseThrow();

			System.out.println(personDB);
			System.out.println("Ingresa el nombre de la persona");
			String nombre = scanner.next();
			personDB.setNombre(nombre);
			Person personUpdate = repository.save(personDB);
			System.out.println(personUpdate);
		
		}else{
			System.out.println("La persona no existe");
		}


		scanner.close();

	}

	@Transactional
	public void create(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre");
		String nombre = scanner.nextLine();
		System.out.println("Apellido paterno");
		String apellido1 = scanner.nextLine();
		System.out.println("Apellido materno");
		String apellido2 = scanner.nextLine();
		System.out.println("Telefono");
		String telefono = scanner.next();
		System.out.println("Mail");
		String mail = scanner.next();
		System.out.println("Grupo");
		int grupo = scanner.nextInt();

		Person person = new Person(0, nombre, apellido1, apellido2, telefono, mail, grupo);

		Person personNew = repository.save(person);

		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
		
		scanner.close();
	}

	@Transactional(readOnly = true)
	public void findOne(){

		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(102);
		// if(optionalPerson.isPresent()){
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);
		repository.findByNombreContaining("li").ifPresent(person->System.out.println(person));
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

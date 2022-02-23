package Hospital.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Hospital.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

	List<Patient> findAllByfirstName(String name);

	List<Patient> findAllBylastName(String name);
	
	Boolean existsByfirstName(String name);
	
	Boolean existsBylastName(String name);
	
	@Query(value= "SELECT * FROM PATIENT WHERE first_name = :firstname AND last_name = :lastname", nativeQuery= true)
	Patient findDuplicateName(@Param("firstname") String firstname, @Param("lastname") String lastname);

}

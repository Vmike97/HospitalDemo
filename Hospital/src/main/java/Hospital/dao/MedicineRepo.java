package Hospital.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Hospital.models.Medicine;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Integer> {

	Medicine findByName(String name);

	List<Medicine> findAllByEffect(String effect);

	Boolean existsByName(String name);
	
	Medicine deleteByName(String name);

	Boolean existsByEffect(String effect);
}

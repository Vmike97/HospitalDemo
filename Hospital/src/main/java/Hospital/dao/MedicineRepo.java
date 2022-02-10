package Hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Hospital.models.Medicine;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Integer>{

}

package Hospital.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Hospital.models.Prescription;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Integer> {
	
	List<Prescription> findAllBypId(int pid);

	List<Prescription> findAllBymedId(int medId);
}

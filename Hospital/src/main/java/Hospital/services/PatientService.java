package Hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Hospital.ErrorHandling.MyResourceNotFoundException;
import Hospital.dao.PatientRepo;
import Hospital.models.Patient;

@Service
public class PatientService {
	
	@Autowired
	PatientRepo patRepo;
	
	public Patient getPatientByID(Integer id){
		if(!patRepo.existsById(id)){
			throw new MyResourceNotFoundException();
		}else{
			return patRepo.findById(id).get();
		}
	}
}

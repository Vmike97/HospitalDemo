package Hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Hospital.dao.MedicineRepo;
import Hospital.models.Medicine;

@Service
public class MedicineService {

	@Autowired
	MedicineRepo medRepo;
	
	/*
	 * Add new medicine to the system. The medicine name must be unique.
	 * The medicine Id is auto generated but customs Id's are allowed. 
	 */
	public Medicine addNewMedicine(Medicine m){
		if(medRepo.existsByName(m.getName()) || medRepo.existsById(m.getMedId())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicine or Id already exists."); 
		}else{
			medRepo.save(m);
			return m;
		}
		
	}
	
}

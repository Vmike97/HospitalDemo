package Hospital.services;

import java.util.List;

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
	
	public Medicine findByMedName(String name){
		if(!medRepo.existsByName(name)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine name does not exist.");
		}else{
			return medRepo.findByName(name);
		}
	}
	
	public Medicine findByMedId(int id){
		if(!medRepo.existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine Id doe not exist.");
		}else{
			return medRepo.findById(id).get();
		}
	}
	
	public List<Medicine> findMedsByEffect(String effect){
		if(medRepo.existsByEffect(effect)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine effect does not exist.");
		}else{
			return medRepo.findAllByEffect(effect);
		}
	}
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

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
		String s = Formatting.capitalizeWithSpaces(name);
		if(!medRepo.existsByName(s)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine name does not exist.");
		}else{
			return medRepo.findByName(s);
		}
	}
	
	public Medicine findByMedId(int id){
		if(!medRepo.existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine Id doe not exist.");
		}else{
			return medRepo.findById(id).get();
		}
	}
	
	public List<Medicine> findMedsByEffect(String effectName){
		String effect = Formatting.capitalize(effectName);
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
		String name = Formatting.capitalizeWithSpaces(m.getName());
		if(medRepo.existsByName(name) || medRepo.existsById(m.getMedId())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicine or Id already exists."); 
		}else{
			Medicine med = new Medicine(m.getMedId(), name, m.getEffect(), m.getInStock());
			medRepo.save(med);
			return med;
		}
		
	}
	
	public String deleteMedFromName(String medName){
		String med = Formatting.capitalize(medName);
		if(!medRepo.existsByName(med)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine name does not exist.");
		}else{
			Medicine m = medRepo.findByName(med);
			medRepo.delete(medRepo.findByName(med));
			return "Medicine ID: " + m.getMedId() + "\n"
					+ "Medicine: " + m.getName() + "\n"
					+ "Successfully deleted.";
		}
	}
	
}

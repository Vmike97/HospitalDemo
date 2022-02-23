package Hospital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Hospital.dao.PatientRepo;
import Hospital.models.Patient;

@Service
public class PatientService {

	@Autowired
	PatientRepo patRepo;

	// List patient(s) by first name, throws exception if patient(s) do not
	// exist.
	public List<Patient> getPatientsByFirstName(String name) {
		String capitalized = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (!patRepo.existsByfirstName(capitalized)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such first name exists");
		}
		return patRepo.findAllByfirstName(capitalized);
	}

	// List patient(s) by last name, throws exception if patient(s) do not
	// exist.
	public List<Patient> getPatientsByLastName(String name) {
		String capitalized = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (!patRepo.existsBylastName(capitalized)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such last name exists.");
		}
		return patRepo.findAllBylastName(capitalized);
	}

	// Show patient by id, throws exception if id does not exist.
	public Patient getPatientByID(Integer id) {
		if (!patRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found.");
		} else {
			return patRepo.findById(id).get();
		}
	}

	/*
	 *  Add new patient. If both first and last name exist as one person throw exception.
	 *  Auto capitalize first and last name first letters.
	 */
	
	public Patient addNewPatient(Patient p) {
		String capFname = p.getFirstname().substring(0, 1).toUpperCase() + p.getFirstname().substring(1);
		String capLname = p.getLastname().substring(0, 1).toUpperCase() + p.getLastname().substring(1);
		if (patRepo.findDuplicateName(capFname, capLname) != null || patRepo.existsById(p.getpId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person with name or Id already exists.");	
		} else {
			// Use constructor overloading for class Patient instead of writing all these sets.
			Patient pAdjust = new Patient();
			pAdjust.setAge(p.getAge());
			pAdjust.setFirstname(capFname);
			pAdjust.setLastname(capLname);
			pAdjust.setpId(p.getpId());
			patRepo.save(pAdjust);
			return pAdjust;
		}
	}

}

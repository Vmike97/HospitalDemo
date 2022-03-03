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
	

	// List patient(s) by first name, throws exception if patient(s) do not exist.
	public List<Patient> getPatientsByFirstName(String name) {
		String s = Formatting.capitalize(name);
		if (!patRepo.existsByfirstName(s)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such first name exists");
		}
		return patRepo.findAllByfirstName(s);
	}

	// List patient(s) by last name, throws exception if patient(s) do not exist.
	public List<Patient> getPatientsByLastName(String name) {
		String s = Formatting.capitalize(name);
		if (!patRepo.existsBylastName(s)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such last name exists.");
		}
		return patRepo.findAllBylastName(s);
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
	 * Add new patient. If both first and last name exist as one person throw
	 * exception. Auto capitalize first and last name first letters.
	 */

	public Patient addNewPatient(Patient p) {
		String capFname = Formatting.capitalize(p.getFirstname());
		String capLname = Formatting.capitalize(p.getLastname());
		if (patRepo.findDuplicateName(capFname, capLname) != null || patRepo.existsById(p.getpId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person with name or Id already exists.");
		} else {
			// Used constructor overloading for class Patient instead of using set methods.
			Patient pAdjust = new Patient(p.getpId(), capFname, capLname, p.getAge());
			/*
			 * pAdjust.setAge(p.getAge()); pAdjust.setFirstname(capFname);
			 * pAdjust.setLastname(capLname); pAdjust.setpId(p.getpId());
			 */
			patRepo.save(pAdjust);
			return pAdjust;
		}
	}

	public String deletePatientFromId(int id) {
		if (!patRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist");
		} else {
			Patient p = patRepo.findById(id).get();
			patRepo.deleteById(id);
			return "Patient ID: " + p.getpId() + "\n" + "Patient Name: " + p.getFirstname() + ", " 
					+ p.getLastname() + "\n" + "Sucessfully deleted.";
		}
	}

	/*
	 * Change either name or age of patient
	 */
	public Patient editPatient(Patient p) {
		if (!patRepo.existsById(p.getpId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This patient Id does not exist.");
		} else {
			patRepo.save(p);
			return p;
		}
	}

}

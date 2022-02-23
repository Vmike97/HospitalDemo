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
		if (!patRepo.existsByfirstName(name)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such first name exists");
		}
		return patRepo.findAllByfirstName(name);
	}

	// List patient(s) by last name, throws exception if patient(s) do not
	// exist.
	public List<Patient> getPatientsByLastName(String name) {
		if (!patRepo.existsBylastName(name)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such last name exists.");
		}
		return patRepo.findAllBylastName(name);
	}

	// Show patient by id, throws exception if id does not exist.
	public Patient getPatientByID(Integer id) {
		if (!patRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found.");
		} else {
			return patRepo.findById(id).get();
		}
	}

	// Add new patient. If both first and last name exist as one person throw
	// exception.
	public Patient addNewPatient(Patient p) {
		if (patRepo.findDuplicateName(p.getFirstname(), p.getLastname()) == null) {
			patRepo.save(p);
			return p;

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person already exists.");
		}
	}

}

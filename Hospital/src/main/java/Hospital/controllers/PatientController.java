package Hospital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import Hospital.dao.PatientRepo;
import Hospital.models.Patient;
import Hospital.services.PatientService;

@Controller
public class PatientController {

	@Autowired
	PatientRepo patientRepo;
	
	@Autowired
	PatientService patientServ;

	@GetMapping("")
	@ResponseBody
	public String show() {
		return "Hello";
	}

	// List all patients.
	@GetMapping("patients")
	@ResponseBody
	public List<Patient> getAllPatients() {
		return patientRepo.findAll();
	}

	// List patients based on desired first name.
	@GetMapping("/patient/firstname/{name}")
	@ResponseBody
	public List<Patient> getPatientByFirstName(@PathVariable String name) {

		return patientServ.getPatientsByFirstName(name);
	}

	// List patients based desired on last name.
	@GetMapping("/patient/lastname/{name}")
	@ResponseBody
	public List<Patient> getPatientByLastName(@PathVariable String name) {

		return patientServ.getPatientsByLastName(name);
	}

	//
	@GetMapping("/patient/id/{id}")
	@ResponseBody
	public Patient getPatientById(@PathVariable int id){
		
		return patientServ.getPatientByID(id);
	}
	
	/* Since there are no unique variables to distinguish a person identity (i.e. SIN) in this case there
	 * shall be no 2 people of the same first and last name allowed in the system.
	*/
	// Add new patient.
	@PutMapping("/addPatient")
	@ResponseBody
	public Patient addNewPatient(@RequestBody Patient p) {
		patientServ.savePatient(p);
		return p;
	}

	// Edit Patient.
	@PutMapping("/editPatient")
	@ResponseBody
	public Patient editPatient(@RequestBody Patient p) {
		patientRepo.save(p);
		return p;
	}

	// Delete patient.
	@DeleteMapping("/deletePatient/id/{id}")
	@ResponseBody
	public String deletePatient(@PathVariable int id) {
		patientRepo.deleteById(id);
		return "Patient deleted";
	}

}

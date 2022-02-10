package Hospital.controller;

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

@Controller
public class PatientController {
	
	@Autowired
	PatientRepo patientRepo;
	
	@GetMapping("")
	@ResponseBody
	public String show(){
		return "Hello";
	}
	//List all patients
	@GetMapping("patients")
	@ResponseBody
	public List<Patient> getAllPatients(){
		return patientRepo.findAll();
	}
	//list patients based on first name
	@GetMapping("/patient/firstname/{name}")
	@ResponseBody
	public List<Patient> getPatientByFirstName(@PathVariable String name){
		
		return patientRepo.findAllByfirstName(name);
	}
	//List patients based on last name
	@GetMapping("/patient/lastname/{name}")
	@ResponseBody
	public List<Patient> getPatientByLastName(@PathVariable String name){
		
		return patientRepo.findAllBylastName(name);
	}
	//Find patient based on patient id 
	@GetMapping("/patient/id/{id}")
	@ResponseBody
	public Patient getPatientByLastName(@PathVariable int id){
		
		return patientRepo.findById(id).orElse(new Patient());
	}	
	
	//Add new patient 
	@PutMapping("/addPatient")
	@ResponseBody
	public Patient addNewPatient(@RequestBody Patient p){
		if(!patientRepo.existsById(p.getpId()) ){
			patientRepo.save(p);
			return p;
		}else{
			return patientRepo.findById(p.getpId()).orElse(null);
		}
		
	}
	
	@PutMapping("/editPatient")
	@ResponseBody
	public Patient editPatient(@RequestBody Patient p){
		patientRepo.save(p);
		return p;
	}
	
	//Delete patient
	@DeleteMapping("/deletePatient/id/{id}")
	@ResponseBody
	public String deletePatient(@PathVariable int id){
		patientRepo.deleteById(id);
		return "Patient deleted";
	}

}

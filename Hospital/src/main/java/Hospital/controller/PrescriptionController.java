package Hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import Hospital.dao.PrescriptionRepo;
import Hospital.models.Prescription;

@Controller
public class PrescriptionController {
	
	@Autowired
	PrescriptionRepo preRepo;
	
	//Get a list of all prescriptions
	@GetMapping("/prescription")
	@ResponseBody
	public List<Prescription> getAllPrescriptions(){
		return preRepo.findAll();
	}
	
	//Get prescription from id
	@GetMapping("/prescription/id/{id}")
	@ResponseBody
	public Prescription getPrescriptionFromId(@PathVariable int id){
		return preRepo.findById(id).orElse(new Prescription());
	}
	
	//Get all prescriptions by patients id
	@GetMapping("/prescription/patientId/{pid}")
	@ResponseBody
	public List<Prescription> getPatientsPrescriptions(@PathVariable int pid){
		return preRepo.findAllBypId(pid);
	}
	
	//Get a list of prescriptions which contain specific medicine
	@GetMapping("/prescription/medId/{medId}")
	@ResponseBody
	public List<Prescription> getPrescriptionsBymedId(@PathVariable int medId){
		return preRepo.findAllBymedId(medId);
	}
	
	//
	
}

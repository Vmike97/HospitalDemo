package Hospital.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import Hospital.dao.MedicineRepo;
import Hospital.models.Medicine;

@Controller
public class MedicineController {

	@Autowired
	MedicineRepo medRepo;

	// List all medicines
	@GetMapping("/medicine")
	@ResponseBody
	public List<Medicine> getAllMedicine() {
		return medRepo.findAll();
	}

	// Get medicine by name
	@GetMapping("/medicine/name/{name}")
	@ResponseBody
	public Medicine getMedicineByName(@PathVariable String name) {
		return medRepo.findByName(name);
	}

	// Get medicine from id
	@GetMapping("/medicine/id/{id}")
	@ResponseBody
	public Medicine getMedicineById(@PathVariable int id) {
		return medRepo.findById(id).orElse(new Medicine());
	}

	// List of medicine by desired effect
	@GetMapping("/medicine/effect/{effect}")
	@ResponseBody
	public List<Medicine> getMedicineWithEffect(@PathVariable String effect) {
		return medRepo.findAllByEffect(effect);
	}

	// Add new medicine
	@PutMapping("/addMedicine")
	@ResponseBody
	public Medicine addNewMedicine(@RequestBody Medicine m) {
		if (!medRepo.existsByName(m.getName())) {
			medRepo.save(m);
			return m;
		} else {
			return medRepo.findByName(m.getName());
		}
	}

	// Delete medicine by name
	@DeleteMapping("/deleteMedicine/{name}")
	@ResponseBody
	public String deleteMedicine(@PathVariable String name) {
		medRepo.deleteById(medRepo.findByName(name).getMedId());
		return "Medicine deleted";
	}

}

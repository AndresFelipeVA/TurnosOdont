package com.andresFVA.turnosOdont.ControllersMVC;

import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class MvcPatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public String getPatients(Model model){
        model.addAttribute("patients", patientService.findAll());
        return "patients";
    }

    @GetMapping("/new")
    public String getNewPatients(@ModelAttribute("newPatient") Patient patient){
        return "newPatients";
    }

    @PostMapping("/new")
    public String createPatient(@ModelAttribute ("newPatient") Patient patient){
        patientService.create(patient);
        return "redirect:/patients?added=si";
    }


    /*No implementado en la interfaz obtener un registro
    @GetMapping("/{matricula}")
        public Patient getPatient(@PathVariable Long matricula){
        return patientService.findPatient(matricula);
    }
*/

    @DeleteMapping("/{DNI}")
    public String deletePatient(@PathVariable Long DNI){
        patientService.deletePatient(DNI);
        return "redirect:/patients";
    }

}


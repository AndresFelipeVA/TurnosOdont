package com.andresFVA.turnosOdont.ControllersMVC;

import com.andresFVA.turnosOdont.models.Dentist;
import com.andresFVA.turnosOdont.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dentists")
public class MvcDentistController {
    @Autowired
    private DentistService dentistService;

    @GetMapping
    public String getDentists(Model model){
        model.addAttribute("dentists", dentistService.findAll());
        return "dentists";
    }

    @GetMapping("/new")
    public String getNewDentists(@ModelAttribute ("newDentist") Dentist dentist){
        return "newDentists";
    }

    @PostMapping("/new")
    public String createDentist(@ModelAttribute ("newDentist") Dentist dentist){
        dentistService.create(dentist);
        return "redirect:/dentists?added=si";
    }


    /*No implementado en la interfaz obtener un registro
    @GetMapping("/{matricula}")
        public Dentist getDentist(@PathVariable Long matricula){
        return dentistService.findDentist(matricula);
    }
*/

    @DeleteMapping("/{matricula}")
    public String deleteDentist(@PathVariable Long matricula){
        dentistService.deleteDentist(matricula);
        return "redirect:/dentists";
    }

}

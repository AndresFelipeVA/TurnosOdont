package com.andresFVA.turnosOdont.ControllersMVC;

import com.andresFVA.turnosOdont.models.Turno;
import com.andresFVA.turnosOdont.models.TurnoDto;
import com.andresFVA.turnosOdont.models.Utils.Mapper;
import com.andresFVA.turnosOdont.models.Utils.Person;
import com.andresFVA.turnosOdont.services.DentistService;
import com.andresFVA.turnosOdont.services.PatientService;
import com.andresFVA.turnosOdont.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/turnos")
public class MvcTurnoController {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private DentistService dentistService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String getTurnos(Model model){
        model.addAttribute("turnos", findTurnosDto());
        return "turnos";
    }

    @GetMapping("/new")
    public String getNewTurnos(Model model){
        model.addAttribute("dentists", findDentist());
        model.addAttribute("patients", findPatients());
        model.addAttribute("newTurno", new Turno());
        return "newTurnos";
    }

    @PostMapping("/new")
    public String createTurno(@ModelAttribute ("newTurno") Turno turno){
        turnoService.create(turno);
        return "redirect:/turnos?added=si";
    }


    /*No implementado en la interfaz obtener un registro
    @GetMapping("/{matricula}")
        public Turno getTurno(@PathVariable Long matricula){
        return turnoService.findTurno(matricula);
    }
*/

    @DeleteMapping("/{matricula}")
    public String deleteTurno(@PathVariable Long matricula){
        turnoService.deleteTurno(matricula);
        return "redirect:/turnos";
    }

    private List<Person> findDentist() {
        List<Person> dentistsAvailable = new ArrayList<>();
        dentistService.findAll().forEach(
                dentist->{
                    Person elemento=new Person();
                    elemento.setId(dentist.getMatricula());
                    elemento.setFullName(dentist.getNombre() +" "+dentist.getApellido());
                    dentistsAvailable.add(elemento);
                });
        return dentistsAvailable;
    }


    private List<Person> findPatients() {
        List<Person> patientsAvailable = new ArrayList<>();
        patientService.findAll().forEach(
                dentist->{
                    Person elemento=new Person();
                    elemento.setId(dentist.getDNI());
                    elemento.setFullName(dentist.getNombre() +" "+dentist.getApellido());
                    patientsAvailable.add(elemento);
                });
        return patientsAvailable;
    }

    private List<TurnoDto> findTurnosDto() {
        List<TurnoDto> turnosDto = new ArrayList<>();
        Mapper mapper = new Mapper();
        turnoService.findAll().forEach(
                turno ->{
                    TurnoDto turnoDto = mapper.toTurnoDto(turno);
                    turnosDto.add(turnoDto);
                });
        return turnosDto;
    }
}

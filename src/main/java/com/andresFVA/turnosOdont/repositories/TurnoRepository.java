package com.andresFVA.turnosOdont.repositories;

import com.andresFVA.turnosOdont.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}

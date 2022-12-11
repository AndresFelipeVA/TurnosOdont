package com.andresFVA.turnosOdont.repositories;

import com.andresFVA.turnosOdont.models.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {
}

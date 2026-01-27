package com.clinicaveterinaria.ejercicio2.repository;

import com.clinicaveterinaria.ejercicio2.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Long> {

}

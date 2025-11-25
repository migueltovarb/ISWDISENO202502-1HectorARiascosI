package com.laboratorio.turnos.controller;

import com.laboratorio.turnos.dto.ApiResponse;
import com.laboratorio.turnos.model.Equipo;
import com.laboratorio.turnos.model.EstadoEquipo;
import com.laboratorio.turnos.model.Laboratorio;
import com.laboratorio.turnos.repository.EquipoRepository;
import com.laboratorio.turnos.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
@CrossOrigin(origins = "http://localhost:3000")
public class LaboratorioController {
    
    @Autowired
    private LaboratorioRepository laboratorioRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @PostMapping
    public ResponseEntity<ApiResponse> crearLaboratorio(@RequestBody Laboratorio laboratorio) {
        try {
            // Guardar el laboratorio
            Laboratorio labGuardado = laboratorioRepository.save(laboratorio);
            
            // Generar equipos automáticamente
            List<Equipo> equipos = generarEquipos(labGuardado);
            equipoRepository.saveAll(equipos);
            
            return ResponseEntity.status(201).body(
                new ApiResponse(true, "Laboratorio creado con " + equipos.size() + " equipos", labGuardado)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ApiResponse(false, "Error al crear laboratorio: " + e.getMessage(), null)
            );
        }
    }
    
    private List<Equipo> generarEquipos(Laboratorio laboratorio) {
        List<Equipo> equipos = new ArrayList<>();
        String labId = laboratorio.getId().substring(laboratorio.getId().length() - 1); // Último carácter del ID
        
        for (int i = 1; i <= laboratorio.getCapacidadTotal(); i++) {
            Equipo equipo = new Equipo();
            equipo.setLaboratorioId(laboratorio.getId());
            equipo.setCodigo(String.format("PC-%s-%02d", labId, i));
            equipo.setTipo("Computador de escritorio");
            equipo.setEstado(EstadoEquipo.DISPONIBLE);
            equipos.add(equipo);
        }
        
        return equipos;
    }
    
    @GetMapping
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        return ResponseEntity.ok(laboratorioRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> obtenerLaboratorio(@PathVariable String id) {
        return laboratorioRepository.findById(id)
            .map(lab -> ResponseEntity.ok(new ApiResponse(true, "Laboratorio encontrado", lab)))
            .orElse(ResponseEntity.notFound().build());
    }
}

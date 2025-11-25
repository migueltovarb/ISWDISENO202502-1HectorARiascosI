package com.laboratorio.turnos.service;

import com.laboratorio.turnos.model.*;
import com.laboratorio.turnos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DataInitService implements CommandLineRunner {
    
    @Autowired
    private LaboratorioRepository laboratorioRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== VERIFICANDO DATOS ===");
        System.out.println("Laboratorios: " + laboratorioRepository.count());
        System.out.println("Equipos: " + equipoRepository.count());
        
        // Inicializar equipos si no existen
        if (equipoRepository.count() == 0 && laboratorioRepository.count() > 0) {
            System.out.println("=== INICIALIZANDO EQUIPOS ===");
            inicializarEquipos();
            System.out.println("=== EQUIPOS INICIALIZADOS ===");
            System.out.println("Total equipos: " + equipoRepository.count());
        }
    }
    
    private void inicializarLaboratorios() {
        Laboratorio labA = new Laboratorio();
        labA.setNombre("Laboratorio A");
        labA.setUbicacion("Edificio Central - Piso 2");
        labA.setCapacidadTotal(30);
        laboratorioRepository.save(labA);
        
        Laboratorio labB = new Laboratorio();
        labB.setNombre("Laboratorio B");
        labB.setUbicacion("Edificio Central - Piso 3");
        labB.setCapacidadTotal(25);
        laboratorioRepository.save(labB);
        
        System.out.println("✓ Laboratorios creados");
    }
    
    private void inicializarEquipos() {
        // Obtener todos los laboratorios existentes
        var laboratorios = laboratorioRepository.findAll();
        
        for (Laboratorio lab : laboratorios) {
            // Generar código corto del laboratorio (primera letra del nombre o últimos caracteres del ID)
            String labCode = lab.getNombre().substring(lab.getNombre().length() - 1);
            if (labCode.matches("\\d+")) {
                // Si termina en número, usar ese número
                labCode = labCode;
            } else {
                // Si no, usar la primera letra
                labCode = lab.getNombre().substring(0, 1).toUpperCase();
            }
            
            // Crear equipos según la capacidad del laboratorio
            int capacidad = lab.getCapacidadTotal();
            for (int i = 1; i <= capacidad; i++) {
                Equipo equipo = new Equipo();
                equipo.setCodigo("PC-" + labCode + "-" + String.format("%02d", i));
                equipo.setLaboratorioId(lab.getId());
                equipo.setTipo("Computador de escritorio");
                equipo.setEstado(EstadoEquipo.DISPONIBLE);
                equipo.setEspecificaciones(i <= capacidad/2 ? "Intel i7, 16GB RAM, SSD 512GB" : "Intel i5, 8GB RAM, SSD 256GB");
                equipoRepository.save(equipo);
            }
            System.out.println("✓ " + capacidad + " Equipos creados para " + lab.getNombre() + " (ID: " + lab.getId() + ")");
        }
    }
}

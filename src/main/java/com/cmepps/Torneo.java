package com.cmepps;

import java.util.Scanner;

public class Torneo {
    private Jugador jugador1;
    private Jugador jugador2;
    private int victoriasJ1;
    private int victoriasJ2;
    private int turnoActual; // Para alternar roles
    private Scanner scanner;

    public Torneo() {
        this.scanner = new Scanner(System.in);
        this.victoriasJ1 = 0;
        this.victoriasJ2 = 0;
        this.turnoActual = 1;
    }

    public void iniciar() {
        System.out.println("¡Bienvenido al Torneo de Ahorcado!");
        
        System.out.print("Introduce nombre del Jugador 1: ");
        jugador1 = new Jugador(scanner.next());
        
        System.out.print("Introduce nombre del Jugador 2: ");
        jugador2 = new Jugador(scanner.next());

        while (victoriasJ1 < 3 && victoriasJ2 < 3) {
            System.out.println("\n--- Inicio de Ronda ---");
            System.out.println("Marcador: " + jugador1.obtenerNombre() + " " + victoriasJ1 + " - " + victoriasJ2 + " " + jugador2.obtenerNombre());
            
            jugarRonda();
            
            turnoActual++;
        }
        
        verificarGanadorTorneo();
        scanner.close();
    }

    public void jugarRonda() {
        Jugador creador;
        Jugador adivinador;

        // Alternar roles: en turnos impares J1 crea, J2 adivina (o viceversa, según se prefiera)
        // El diagrama dice "Determinar Roles". Supongamos J1 empieza creando.
        if (turnoActual % 2 != 0) {
            creador = jugador1;
            adivinador = jugador2;
        } else {
            creador = jugador2;
            adivinador = jugador1;
        }

        Ronda ronda = new Ronda(creador, adivinador, scanner);
        ronda.iniciarRonda();

        if (ronda.haGanadoAdivinador()) {
            if (adivinador == jugador1) {
                victoriasJ1++;
            } else {
                victoriasJ2++;
            }
        } else {
            // Si el adivinador pierde, el creador gana el punto
            System.out.println("¡" + creador.obtenerNombre() + " gana el punto por defensa exitosa!");
            if (creador == jugador1) {
                victoriasJ1++;
            } else {
                victoriasJ2++;
            }
        }
    }

    private void verificarGanadorTorneo() {
        System.out.println("\n--- Fin del Torneo ---");
        System.out.println("Marcador Final: " + jugador1.obtenerNombre() + " " + victoriasJ1 + " - " + victoriasJ2 + " " + jugador2.obtenerNombre());
        if (victoriasJ1 >= 3) {
            System.out.println("¡El ganador del torneo es " + jugador1.obtenerNombre() + "!");
        } else {
            System.out.println("¡El ganador del torneo es " + jugador2.obtenerNombre() + "!");
        }
    }
}

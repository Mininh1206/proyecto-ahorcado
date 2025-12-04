package com.cmepps;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ronda {
    private Jugador creador;
    private Jugador adivinador;
    private Palabra palabraActual;
    private int intentosRestantes;
    private Set<Character> letrasUsadas;
    private Visualizador visualizador;
    private Scanner scanner;

    public Ronda(Jugador creador, Jugador adivinador, Scanner scanner) {
        this.creador = creador;
        this.adivinador = adivinador;
        this.intentosRestantes = 6;
        this.letrasUsadas = new HashSet<>();
        this.visualizador = new Visualizador();
        this.scanner = scanner;
    }

    public void iniciarRonda() {
        System.out.println("Turno de " + creador.obtenerNombre() + " para crear la palabra.");
        String texto;
        while (true) {
            System.out.print("Introduce la palabra secreta: ");
            texto = scanner.next();
            if (texto.matches("^[a-zA-ZñÑ]+$")) {
                break;
            }
            System.out.println("Error: La palabra solo puede contener letras (incluyendo 'ñ'). No se permiten números ni caracteres especiales.");
        }
        this.palabraActual = new Palabra(texto);
        
        visualizador.limpiarConsola();
        System.out.println("¡Palabra guardada! Turno de " + adivinador.obtenerNombre() + " para adivinar.");

        while (intentosRestantes > 0 && !verificarVictoria()) {
            visualizador.imprimirAhorcado(6 - intentosRestantes);
            System.out.println("Palabra: " + palabraActual.obtenerVisualizacion());
            System.out.println("Intentos restantes: " + intentosRestantes);
            System.out.println("Letras usadas: " + letrasUsadas);
            
            System.out.print("Ingresa una letra: ");
            String input = scanner.next().toUpperCase();
            if (input.length() != 1) {
                System.out.println("Por favor, ingresa una sola letra.");
                continue;
            }
            char letra = input.charAt(0);

            if (procesarIntento(letra)) {
                System.out.println("¡Acierto!");
            } else {
                System.out.println("Fallo.");
            }
        }

        if (verificarVictoria()) {
            System.out.println("¡" + adivinador.obtenerNombre() + " ha ganado la ronda! La palabra era: " + palabraActual.getTextoOriginal());
        } else {
            visualizador.imprimirAhorcado(6);
            System.out.println("¡" + adivinador.obtenerNombre() + " ha perdido! La palabra era: " + palabraActual.getTextoOriginal());
        }
    }

    public boolean procesarIntento(char letra) {
        if (letrasUsadas.contains(letra)) {
            System.out.println("Ya has usado esa letra.");
            return false; // No penalizamos, pero tampoco es acierto de nueva letra
        }
        letrasUsadas.add(letra);
        
        if (palabraActual.verificarLetra(letra)) {
            return true;
        } else {
            intentosRestantes--;
            return false;
        }
    }

    private boolean verificarVictoria() {
        return palabraActual.estaCompleta();
    }
    
    public boolean haGanadoAdivinador() {
        return verificarVictoria();
    }
}

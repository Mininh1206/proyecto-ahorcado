package com.cmepps;

public class Palabra {
    private String textoOriginal;
    private boolean[] estadoDescubierto;

    public Palabra(String texto) {
        this.textoOriginal = texto.toUpperCase();
        this.estadoDescubierto = new boolean[texto.length()];
    }

    public boolean verificarLetra(char letra) {
        boolean acierto = false;
        letra = Character.toUpperCase(letra);
        for (int i = 0; i < textoOriginal.length(); i++) {
            if (textoOriginal.charAt(i) == letra) {
                estadoDescubierto[i] = true;
                acierto = true;
            }
        }
        return acierto;
    }

    public String obtenerVisualizacion() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textoOriginal.length(); i++) {
            if (estadoDescubierto[i]) {
                sb.append(textoOriginal.charAt(i));
            } else {
                sb.append("_");
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public boolean estaCompleta() {
        for (boolean b : estadoDescubierto) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    
    public String getTextoOriginal() {
        return textoOriginal;
    }
}

package TrabajoPracticoFinalEDD;

import java.util.Objects;

public class Equipo implements Comparable {

    private String nombrePais;
    private String tecnico;
    private String grupoInicial;
    private int puntosGanados;
    private int golesAFavor;
    private int golesEnContra;

    public Equipo(String nombrePais, String tecnico, String grupoInicial, int golesAFavor, int golesEnContra) {
        this.nombrePais = nombrePais.toUpperCase();
        this.tecnico = tecnico.toUpperCase();
        this.grupoInicial = grupoInicial.toUpperCase();
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
    }

    public Equipo(String nombrePais) {
        this.nombrePais = nombrePais.toUpperCase();
    }

    public Equipo(String nombrePais, String tecnico, String grupoInicial) {
        this.nombrePais = nombrePais.toUpperCase();
        this.tecnico = tecnico;
        this.grupoInicial = grupoInicial;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getTecnico() {
        return tecnico;
    }

    public String getGrupoInicial() {
        return grupoInicial;
    }

    public int getPuntosGanados() {
        return puntosGanados;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public void setGrupoInicial(String grupoInicial) {
        this.grupoInicial = grupoInicial;
    }

    public void setPuntosGanados(int puntos) {
        this.puntosGanados = puntos;
    }

    public void actualizarPuntos(int golesF, int golesC) {
        if (golesF > golesC) {
            this.puntosGanados = puntosGanados + 3;

        } else if (golesF == golesC) {
            this.puntosGanados = puntosGanados + 1;

        }
    }

    public void actualizarGoles(int golesF, int golesC) {

        this.golesAFavor = golesAFavor + golesF;
        this.golesEnContra = golesEnContra + golesC;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public String toString() {

        return nombrePais + " " + golesAFavor + ";" + golesEnContra;
    }

    public String toStringExtenso() {

        return ("Nombre: " + nombrePais + " Tecnico: " + tecnico + " Grupo: " + grupoInicial + "\nGoles a favor: " + golesAFavor + " Goles en contra: " + golesEnContra + " Puntos: " + puntosGanados);

    }

    public int compareTo(Object equipo) {

        return (this.nombrePais.compareTo(((Equipo) equipo).getNombrePais()));
    }

}

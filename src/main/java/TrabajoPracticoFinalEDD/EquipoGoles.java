/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrabajoPracticoFinalEDD;

/**
 *
 * @author PC
 */
public class EquipoGoles implements Comparable{
    //Creo esta clase para poder implementar un metodo compareTo que me deje ordenar el equipo por los goles a favor.

    private Equipo equipo;
    private String clave;

    public EquipoGoles(Equipo equipo) {
        this.equipo = equipo;
        //Hago una clave con los goles y el nombre, ya que los goles pueden ser de igual cantidad.
        clave = equipo.getGolesAFavor() + equipo.getNombrePais();

    }

    public int compareTo(Object equipo) {
        EquipoGoles equipoG = (EquipoGoles) equipo;

        return (this.clave.compareTo(equipoG.getClave()));

    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getClave() {
        return clave;
    }

    public String toString() {

        return equipo.toString();
    }

}

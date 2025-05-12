package TrabajoPracticoFinalEDD;

public class Ciudad {

    private String nombre;
    private boolean alojamiento;
    private boolean sede;

    public Ciudad(String nombre) {

        this.nombre = nombre.toUpperCase();

    }

    public Ciudad(String nombre, boolean alojamiento, boolean sede) {
        this.nombre = nombre.toUpperCase();
        this.alojamiento = alojamiento;
        this.sede = sede;
    }

    public void setAlojamiento(boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    public void setSede(boolean sede) {
        this.sede = sede;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getAlojamiento() {
        return alojamiento;
    }

    public boolean getSede() {
        return sede;
    }
    
    public String toStringExtenso(){
        
        return ("Nombre: " + nombre + " Alojamiento: " + alojamiento + " Sede: " + sede);
    }

    public String toString() {
        return nombre;
    }

    public boolean equals(Object elem) {
        Ciudad ciudad = (Ciudad) elem;

        return (this.nombre.equals(ciudad.getNombre()));
    }

}

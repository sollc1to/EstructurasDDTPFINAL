package TrabajoPracticoFinalEDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;

public class CopaAmerica2024 {


    private static ArbolAVL equipos = cargarEquipos();
    private static TablaHash partidos = cargarPartidos();
    private static GrafoEtiquetado mapaCiudades = cargarMapa();
    private static File obj = new File("src/log.txt");
    private static LocalDateTime now = LocalDateTime.now();

    public static void main(String[] args) throws IOException {

        write("Inicio de sistema " + " " + now);

        Scanner sc = new Scanner(System.in);

        System.out.println();

        int eleccion;
        bienvenida();
        menu();

        eleccion = sc.nextInt();

        while (eleccion != 0) {

            switch (eleccion) {
                case 1:
                    write("Menu Eleccion 1" + " " + now);
                    modificarEquipo();

                    break;

                case 2:
                    write("Menu Eleccion 2" + " " + now);

                    modificarCiudades();
                    break;

                case 3:
                    write("Menu Eleccion 3" + " " + now);

                    agregarPartido();
                    break;

                case 4:
                    write("Menu Eleccion 4" + " " + now);

                    System.out.println(mapaCiudades.toString());
                    break;

                case 5:
                    write("Menu Eleccion 5" + " " + now);

                    System.out.println(mapaCiudades.toString());
                    break;

                case 6:
                    write("Menu Eleccion 6" + " " + now);

                    System.out.println(partidos.toString());
                    break;
                case 7:
                    write("Menu Eleccion 7" + " " + now);

                    consultaEquipo();
                    break;
                case 8:
                    write("Menu Eleccion 8" + " " + now);

                    consultaPartido();
                    break;
                case 9:
                    write("Menu Eleccion 9" + " " + now);

                    consultaViaje();
                    break;

                case 10:
                    write("Menu Eleccion 10" + " " + now);

                    System.out.println("=============================================================================");
                    System.out.println("|                               Equipos                                     |");
                    System.out.println("|===========================================================================|");
                    System.out.println(equipos.toString());
                    System.out.println("=============================================================================");
                    System.out.println("|                            Mapa Ciudades                                  |");
                    System.out.println("|===========================================================================|");
                    System.out.println(mapaCiudades.toString());
                    System.out.println("=============================================================================");
                    System.out.println("|                               Partidos                                    |");
                    System.out.println("|===========================================================================|");
                    System.out.println(partidos.toString());
                    break;
                default:

                    break;

            }
            menu();
            eleccion = sc.nextInt();

        }
        write("Fin programa" + " " + now);

    }

    public static void bienvenida() {
        System.out.println("==================================================================================");
        System.out.println("|                                                                                |");
        System.out.println("|                    Bienvenido al Menu de la Copa America 2024!                 |");
        System.out.println("|                                                                                |");
        System.out.println("|================================================================================|");
    }

    public static void menu() {
        System.out.println("==================================================================================");
        System.out.println("|                                                                                |");
        System.out.println("|                            Menu Principal                                      |");
        System.out.println("|                                                                                |");
        System.out.println("|================================================================================|");
        System.out.println("| 1. Alta/Baja/Modificacion de Equipos                                           |");
        System.out.println("| 2. Alta/Baja/Modificacion de Ciudades                                          |");
        System.out.println("| 3. Alta de Partidos                                                            |");
        System.out.println("| 4. Ver Mapa Ciudades                                                           |");
        System.out.println("| 5. Ver Equipos                                                                 |");
        System.out.println("| 6. Ver Partidos                                                                |");
        System.out.println("| 7. Consultas sobre Equipos                                                     |");
        System.out.println("| 8. Buscar Partido                                                              |");
        System.out.println("| 9. Consulta sobre viajes                                                       |");
        System.out.println("| 10.Mostrar Sistema                                                             |");
        System.out.println("| 0. Salir                                                                       |");
        System.out.println("|                                                                                |");
        System.out.println("==================================================================================");
        System.out.print("Elige una opcion: ");

    }

    public static void consultaViaje() throws IOException {
        Scanner sc = new Scanner(System.in);
        int eleccion;
        String ciudad1, ciudad2;
        Ciudad ciudad;
        String cadena = "La ciudad no se encontro.";
        Lista lista;

        System.out.println("==================================================");
        System.out.println("|                                                 |");
        System.out.println("| 1. Obtener el camino de menor tiempo de A a B   |");
        System.out.println("| 2. Obtener el camino más corto de A a B         |");
        System.out.println("| 3. Mostrar mapa de ciudades                     |");
        System.out.println("|                                                 |");
        System.out.println("|                                                 |");
        System.out.println("==================================================");
        System.out.print(" Elige una opcion: ");

        eleccion = sc.nextInt();
        sc.nextLine();

        switch (eleccion) {
            case 1:

                write("Eleccion 1" + " " + now);

                System.out.println("Ingrese el nombre de la ciudad A.");
                ciudad1 = sc.nextLine();
                ciudad = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(ciudad1));

                if (ciudad != null) {
                    System.out.println("Ingrese el nombre de la ciudad B");
                    ciudad2 = sc.nextLine();
                    ciudad = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(ciudad2));

                    if (ciudad != null) {
                        System.out.println("Verificando camino...");
                        lista = mapaCiudades.caminoMenosPeso(new Ciudad(ciudad1), new Ciudad(ciudad2));

                        if (lista.esVacia()) {
                            write("No se encontró camino entre" + ciudad1 + " " + ciudad2 + " " + now);

                        } else {
                            write("Se encontró camino entre" + ciudad1 + " " + ciudad2 + " " + now);

                        }
                        cadena = "Camino: \n" + lista.toString();

                    } else {
                        write("No se encontró ciudad " + ciudad2 + " " + now);

                    }

                } else {
                    write("No se encontró ciudad " + ciudad1 + " " + now);

                }
                System.out.println(cadena);

                break;

            case 2:
                write("Eleccion 2" + " " + now);

                System.out.println("Ingrese el nombre de la ciudad A.");
                ciudad1 = sc.nextLine();
                ciudad = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(ciudad1));

                if (ciudad != null) {
                    System.out.println("Ingrese el nombre de la ciudad B");
                    ciudad2 = sc.nextLine();
                    ciudad = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(ciudad2));

                    if (ciudad != null) {
                        System.out.println("Verificando camino...");
                        lista = mapaCiudades.caminoMasCorto(new Ciudad(ciudad1), new Ciudad(ciudad2));
                        if (lista.esVacia()) {
                            write("No se encontró camino entre " + ciudad1 + " " + ciudad2 + " " + now);

                        } else {
                            write("Se encontró camino entre " + ciudad1 + " " + ciudad2 + " " + now);

                        }
                        cadena = "Camino: \n" + lista.toString();

                    } else {
                        write("No se encontró ciudad " + ciudad2 + " " + now);
                    }

                } else {
                    write("No se encontró ciudad " + ciudad1 + " " + now);
                }

                System.out.println(cadena);
                break;

            case 3:
                write("Eleccion 3" + " " + now);
                System.out.println(mapaCiudades.toString());
                break;

            default:
                break;

        }

    }

    public static void consultaPartido() throws IOException {
        Scanner sc = new Scanner(System.in);
        String nombre1, nombre2, cadena;
        Equipo equipoAux;
        Partido partido;
        sc.nextLine();

        System.out.println("Ingrese el nombre del equipo numero uno: ");

        nombre1 = sc.nextLine();
        equipoAux = (Equipo) equipos.recuperarElemento(new Equipo(nombre1));

        if (equipoAux != null) {
            System.out.println("Ingrese el nombre del equipo numero dos: ");
            nombre2 = sc.nextLine();
            equipoAux = (Equipo) equipos.recuperarElemento(new Equipo(nombre2));

            if (equipoAux != null) {

                if (nombre1.compareTo(nombre2) > 0) {

                    partido = (Partido) partidos.recuperarElemento(new Partido(nombre2, nombre1));

                } else {
                    partido = (Partido) partidos.recuperarElemento(new Partido(nombre1, nombre2));

                }
                if (partido == null) {
                    write("No se encontro el partido " + " " + now);

                } else {
                    write("Se encontro el partido " + nombre1 + " " + nombre2 + " " + now);
                }

                cadena = "Buscando partido.." + ((partido == null) ? "El partido no pudo encontrarse." : "\nPartido: \n" + partido.toStringExtenso());

            } else {
                write("No se encontro el equipo " + nombre2 + " " + now);

                cadena = "El equipo no existe";

            }

        } else {
            write("No se encontro el equipo " + nombre1 + " " + now);
            cadena = "El equipo no existe";
        }
        System.out.println(cadena);

    }

    public static void consultaEquipo() throws IOException {
        Scanner sc = new Scanner(System.in);
        int eleccion;
        Equipo equipo, equipo2;
        String nombre, nombre2, cadena;
        System.out.println("==================================================");
        System.out.println("|                                                 |");
        System.out.println("| 1. Mostrar Equipo                               |");
        System.out.println("| 2. Mostrar Equipos en Rango (equipo1, equipo2)  |");
        System.out.println("| 3. Mostrar lista ordenada por goles a favor     |");
        System.out.println("| 4. Mostrar Equipos                              |");
        System.out.println("|                                                 |");
        System.out.println("==================================================");
        System.out.print(" Elige una opcion: ");
        eleccion = sc.nextInt();

        switch (eleccion) {
            case 1:
                write("Eleccion 1 " + " " + now);
                sc.nextLine();
                System.out.println("Ingrese el nombre del equipo: ");
                nombre = sc.nextLine().trim();
                equipo = (Equipo) equipos.recuperarElemento(new Equipo(nombre));

                if (equipo != null) {
                    write("Se encontro el equipo " + nombre + " " + now);
                    System.out.println(equipo.toStringExtenso());

                } else {
                    write("No se encontro el equipo " + nombre + " " + now);
                    System.out.println("El equipo indicado no existe.");
                }

                break;

            case 2:
                write("Eleccion 2 " + " " + now);

                sc.nextLine();

                System.out.println("Ingrese el nombre del equipo nro uno: ");
                nombre = sc.nextLine().trim();
                equipo = (Equipo) equipos.recuperarElemento(new Equipo(nombre));

                if (equipo != null) {
                    System.out.println("Ingrese el nombre del equipo nro dos: ");
                    nombre2 = sc.nextLine();

                    equipo2 = (Equipo) equipos.recuperarElemento(new Equipo(nombre));

                    if (equipo2 != null) {
                        //Creo new Equipo(nombre) porque si lo hago con el objecto equipo, no inserta bien (?

                        Lista lista = equipos.listarRango(new Equipo(nombre), new Equipo(nombre2));

                        System.out.println("Lista: \n" + lista.toString());
                        write("Se listó el rango correctamente entre " + nombre + " " + nombre2 + " " + now);

                    } else {
                        write("No se encontro el equipo " + nombre2 + " " + now);
                        System.out.println("No se encontro el equipo.");

                    }

                } else {
                    write("No se encontro el equipo " + nombre + " " + now);
                    System.out.println("No se encontro el equipo.");

                }

                break;

            case 3:
                write("Eleccion 3 " + now);
                write("Lista ordenada por goles a favor " + now);

                //Lista ordenada por goles a favor.
                listaOrdenadaGoles();
                break;

            case 4:
                write("Eleccion 4 " + now);

                System.out.println(equipos.toString());
                break;

            default:
                break;

        }

    }

    public static void listaOrdenadaGoles() {
        //Lista ordenada de arbol por nombre.
        int i = 1;
        Lista lista = equipos.listar();
        Equipo equipo;
        EquipoGoles equipoGoles;

        HeapMinimo arbolAux = new HeapMinimo();

        while (i <= lista.longitud()) {
            equipo = (Equipo) lista.recuperar(i);
            //Cargo los equipos ordenados por goles en el heap minimo.
            arbolAux.insertar(new EquipoGoles(equipo));
            i++;

        }

        lista = new Lista();

        while (!arbolAux.esVacio()) {

            lista.insertar(arbolAux.obtenerCima(), lista.longitud() + 1);
            arbolAux.eliminarCima();

        }

        System.out.println(lista.toString());

    }

    public static void agregarPartido() throws IOException {
        String nombre1, nombre2 = "", instancia, ciudad, estadio, cadena = "Los datos ingresados no son correctos.", alojamiento;
        int goles1 = 0, goles2 = 0, comparar, eleccion;
        Partido partido = null;
        Equipo equipo1 = null, equipo2 = null;
        Ciudad ciudadAux;
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("|                                             |");
        System.out.println("| 1. Mostrar ciudades que son sede            |");
        System.out.println("| 2. Continuar                                |");
        System.out.println("|                                             |");
        System.out.println("==============================================");
        System.out.println("Elige una opcion: ");
        eleccion = sc.nextInt();
        sc.nextLine();
        
        if (eleccion == 1) {
            mostrarCiudadesSede();
            
        }

        boolean insertar = false, aloj;
        System.out.println("Creando partido...");
        System.out.println("Ingrese el nombre del equipo numero uno");
        nombre1 = sc.nextLine().trim();
        equipo1 = (Equipo) equipos.recuperarElemento(new Equipo(nombre1));

        if (equipo1 != null) {
            System.out.println("Ingrese el nombre del equipo numero dos");
            nombre2 = sc.nextLine().trim();

            equipo2 = (Equipo) equipos.recuperarElemento(new Equipo(nombre2));
            if (equipo2 != null) {
                System.out.println("Ingrese la instancia (grupo, cuartos, semifinales,final)");
                instancia = sc.nextLine().toUpperCase().trim();

                if (instancia.equals("GRUPO") || instancia.equals("CUARTOS") || instancia.equals("SEMIFINALES") || instancia.equals("FINAL")) {
                    System.out.println("Ingrese el nombre de la ciudad");
                    //Esta ciudad debe ser sede. Veo si la ciudad está en el grafo, si no creo una nueva y la agrego como sede.
                    ciudad = sc.nextLine().trim();
                    ciudadAux = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(ciudad));
                    if (ciudadAux == null) {
                        write("No se encontro la ciudad " + ciudad + " " + now);
                        System.out.println("La ciudad no existe. Intente de nuevo.");

                    } else {

                        if (ciudadAux.getSede()) {
                            System.out.println("Ingrese el nombre del estadio");
                            estadio = sc.nextLine().toUpperCase().trim();
                            System.out.println("Ingrese los goles del equipo uno");
                            goles1 = sc.nextInt();
                            if (goles1 > 0) {
                                System.out.println("Ingrese los goles del equipo dos");
                                goles2 = sc.nextInt();
                                if (goles2 > 0) {
                                    comparar = nombre1.compareTo(nombre2);
                                    //Como la clave debe estar ordenada lexicamente.
                                    if (comparar != 0) {
                                        System.out.println("Insertando partido...");

                                        if (comparar < 0) {
                                            partido = new Partido(nombre1, nombre2, instancia, ciudad, estadio, goles1, goles2);
                                            insertar = partidos.insertar(partido);

                                        } else if (comparar > 0) {
                                            partido = new Partido(nombre2, nombre1, instancia, ciudad, estadio, goles2, goles1);
                                            insertar = partidos.insertar(partido);
                                        }
                                        cadena = (insertar) ? " El partido se inserto correctamente \n " + partido.toStringExtenso() : "No fue posible insertar el partido";

                                        write("Se insertó correctamente el partido " + nombre1 + " " + nombre2);

                                    } else {
                                        System.out.println("No existe un partido entre dos equipos iguales.");
                                        write("No es posible insertar partido:" + nombre1 + " " + nombre2 + " " + now);

                                    }
                                } else {
                                    write("Goles invalidos " + goles2 + " " + now);

                                }

                            } else {

                                write("Goles invalidos " + goles1 + " " + now);
                            }

                        } else {
                            write("La ciudad " + ciudad + " no es sede " + " " + now);
                            cadena = "La ciudad no es sede. Intente otra vez.";

                        }

                    }

                } else {
                    write("Instancia incorrecta " + instancia + " " + now);

                }
            } else {
                write("No se encontro el equipo " + nombre2 + " " + now);

                cadena = "No se encontro el equipo.";

            }

        } else {
            write("No se encontro el equipo " + nombre1 + " " + now);
            cadena = "No se encontro el equipo.";
        }
        System.out.println(cadena);

        //Si se inserto correctamente, debo modificar los goles de equipo y los puntos en caso de que la instancia sea GRUPO.
        if (insertar && equipo1 != null && equipo2 != null) {
            write("Actualización goles de equipos " + nombre1 + " " + nombre2 + " " + now);

            equipo1.actualizarGoles(goles1, goles2);

            equipo2.actualizarGoles(goles1, goles2);

            if (partido.getInstancia().equals("GRUPO")) {

                equipo1.actualizarPuntos(goles1, goles2);

                equipo2.actualizarPuntos(goles2, goles1);

            }

        }

    }

    public static void modificarCiudades() throws IOException {
        Scanner sc = new Scanner(System.in);
        int eleccion;
        String nombre, cadena;
        boolean resultado;

        Ciudad ciudad;
        System.out.println("==============================================");
        System.out.println("|                                             |");
        System.out.println("| 1. Alta de Ciudad                           |");
        System.out.println("| 2. Baja de Ciudad                           |");
        System.out.println("| 3. Modificacion de Ciudad                   |");
        System.out.println("| 4. Agregar Ruta                             |");
        System.out.println("| 5. Eliminar Ruta                            |");
        System.out.println("| 6. Ver mapa de ciudades                     |");
        System.out.println("|                                             |");
        System.out.println("==============================================");
        System.out.print(" Elige una opcion: ");
        eleccion = sc.nextInt();
        switch (eleccion) {
            case 1:
                sc.nextLine();
                altaCiudad();

                break;
            case 2:

                sc.nextLine();
                write("Eleccion 2 " + now);

                System.out.println("Ingrese el nombre de la ciudad que quiere eliminar: ");
                nombre = sc.nextLine().trim();
                System.out.println("Eliminando...");
                resultado = mapaCiudades.eliminarVertice(new Ciudad(nombre));
                if (resultado) {
                    write("Se eliminó con exito la ciudad " + nombre + " " + now);

                } else {
                    write("No se eliminó con exito la ciudad " + nombre + " " + now);

                }
                cadena = (resultado) ? "Se elimino con exito." : "No se elimino correctamente.";

                System.out.println(cadena);
                break;
            case 3:

                sc.nextLine();
                modificarCiudad();

                break;
            case 4:

                sc.nextLine();
                insertarRuta();

                break;

            case 5:

                sc.nextLine();
                eliminarRuta();

                break;
            case 6:
                write("Eleccion 7 " + now);

                System.out.println(mapaCiudades.toString());
                break;

            default:
                break;

        }

    }

    public static void altaCiudad() throws IOException {
        Scanner sc = new Scanner(System.in);
        Ciudad ciudad;
        String cadena, nombre, alojamiento, sede;
        boolean resultado, sed, alojamien;
        write("Eleccion 1 " + now);

        System.out.println("Ingrese el nombre de la ciudad");
        nombre = sc.nextLine().trim();

        ciudad = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(nombre));

        if (ciudad == null) {
            System.out.println("Ingrese si tiene alojamiento (S/N)");
            alojamiento = sc.nextLine().toUpperCase().trim();

            if ((alojamiento.equals("S") || alojamiento.equals("N"))) {
                System.out.println("Ingrese si es sede (S/N");
                sede = sc.nextLine().toUpperCase().trim();
                if (sede.equals("S") || sede.equals("N")) {

                    if (alojamiento.equals("S")) {
                        alojamien = true;
                    } else {
                        alojamien = false;
                    }
                    if (sede.equals("S")) {
                        sed = true;

                    } else {
                        sed = false;
                    }

                    ciudad = new Ciudad(nombre, alojamien, sed);
                    resultado = mapaCiudades.insertarVertice(ciudad);

                    cadena = "Insertando ciudad.." + ((resultado) ? "Se inserto con exito." : "No se inserto correctamente");
                    write("Se insertó ciudad correctamente " + nombre + " " + now);

                } else {

                    write("Sede incorrecta " + sede + " " + now);
                    cadena = "Los datos no se ingresaron correctamente";
                }

            } else {
                write("Alojamiento incorrecto " + alojamiento + " " + now);
                cadena = "Los datos no se ingresaron correctamente";
            }

        } else {
            write("La ciudad ya existe " + nombre + " " + now);
            cadena = "La ciudad ya existe.";
        }

        System.out.println(cadena);

    }

    public static void modificarCiudad() throws IOException {
        Scanner sc = new Scanner(System.in);
        String nombre, alojamiento, sede, atributo, cadena;
        Ciudad ciudad;

        write("Eleccion 3 " + now);

        //Modificacion de ciudad.
        System.out.println("Ingrese el nombre de la ciudad la cual quiere modificar: ");
        nombre = sc.nextLine().trim();

        ciudad = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(nombre));
        if (ciudad != null) {
            System.out.println("Ingrese que atributo quiere modificar, (alojamiento, sede)");
            atributo = sc.nextLine().toUpperCase().trim();
            if (atributo.equals("ALOJAMIENTO")) {

                System.out.println("Ingrese (S/N)");
                alojamiento = sc.nextLine().toUpperCase().trim();

                if (alojamiento.equals("S")) {

                    ciudad.setAlojamiento(true);
                    write("Se modificó correctamente el alojamiento de " + nombre + " " + now);
                    cadena = "Ciudad: " + ciudad.toStringExtenso();

                } else if (alojamiento.equals("N")) {
                    ciudad.setAlojamiento(false);
                    cadena = "Ciudad: " + ciudad.toStringExtenso();

                    write("Se modificó correctamente el alojamiento de " + nombre + " " + now);

                } else {

                    cadena = "No se ingreso correctamente el alojamiento.";
                    write("No se modificó correctamente el alojamiento de " + nombre + " " + now);

                }

            } else if (atributo.equals("SEDE")) {
                System.out.println("Ingrese (S/N)");
                sede = sc.nextLine().toUpperCase().trim();

                if (sede.equals("S")) {
                    write("Se modificó correctamente la sede de " + nombre + " " + now);
                    cadena = "Ciudad: " + ciudad.toStringExtenso();

                    ciudad.setSede(true);
                } else if (sede.equals("N")) {
                    ciudad.setSede(false);
                    write("Se modificó correctamente la sede de " + nombre + " " + now);
                    cadena = "Ciudad: " + ciudad.toStringExtenso();

                } else {
                    write("No se modificó correctamente la sede de " + nombre + " " + now);

                    cadena = "No se ingreso correctamente la sede.";
                }

            } else {
                cadena = " No se ingreso correctamente el atributo";
                write("No se ingresó correctamente el atributo " + atributo + " " + now);
            }

        } else {
            write("No se encontró la ciudad " + nombre + " " + now);
            cadena = "No se encontro la ciudad";
        }

        System.out.println(cadena);
    }

    public static void insertarRuta() throws IOException {
        Scanner sc = new Scanner(System.in);
        String origen, destino, cadena;
        int etiqueta;
        boolean resultado;
        write("Eleccion 4 " + now);

        System.out.println("Ingrese la ciudad origen: ");
        origen = sc.nextLine();

        Ciudad ciudadAux = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(origen));
        if (ciudadAux != null) {
            System.out.println("Ingrese la ciudad destino: ");
            destino = sc.nextLine();

            ciudadAux = (Ciudad) mapaCiudades.recuperarElemento(new Ciudad(destino));

            if (ciudadAux != null) {
                System.out.println("Ingrese la cantidad de tiempo de vuelo");
                etiqueta = sc.nextInt();

                if (etiqueta > 0) {
                    System.out.println("Insertando ruta...");
                    resultado = mapaCiudades.insertarArco(new Ciudad(origen), new Ciudad(destino), etiqueta);

                    cadena = (resultado) ? "La ruta se inserto correctamente." : "No fue posible insertar la ruta.";
                    write("Ruta insertada correctamente entre " + origen + " " + destino + " " + now);

                } else {

                    cadena = "El tiempo no puede ser negativo.";
                    write("Tiempo de vuelo incorrecto " + etiqueta + " " + now);
                }

            } else {
                write("No se encontró la ciudad " + destino + " " + now);

                cadena = "No se encontro la ciudad";
            }

        } else {
            write("No se encontró la ciudad " + origen + " " + now);
            cadena = "No se encontro la ciudad.";
        }
        System.out.println(cadena);

    }

    public static void eliminarRuta() throws IOException {
        Scanner sc = new Scanner(System.in);
        String origen, destino, cadena;
        boolean resultado;
        write("Eleccion 5 " + now);

        System.out.println("Ingrese la ciudad origen: ");
        origen = sc.nextLine().trim();
        System.out.println("Ingrese la ciudad destino: ");
        destino = sc.nextLine().trim();

        System.out.println("Eliminando ruta..");
        resultado = mapaCiudades.eliminarArco(new Ciudad(origen), new Ciudad(destino));
        cadena = (resultado) ? "La ruta se elimino correctamente." : "No fue posible eliminar la ruta.";
        System.out.println(cadena);
        if (resultado) {
            write("Se eliminó correctamente la ruta entre " + origen + " " + destino + " " + now);

        } else {
            write("No se eliminó correctamente la ruta entre " + origen + " " + destino + " " + now);

        }

    }

    public static void modificarEquipo() throws IOException {
        Scanner sc = new Scanner(System.in);

        int eleccion;
        boolean resultado;
        String nombre, cadena = "Los datos ingresados no son correctos.";
        System.out.println("==============================================");
        System.out.println("|                                             |");
        System.out.println("| 1. Alta de Equipo                           |");
        System.out.println("| 2. Baja de Equipo                           |");
        System.out.println("| 3. Modificacion de Equipo                   |");
        System.out.println("| 4. Mostrar Equipos                          |");
        System.out.println("|                                             |");
        System.out.println("==============================================");
        System.out.print(" Elige una opcion: ");
        eleccion = sc.nextInt();

        switch (eleccion) {
            case 1:
                sc.nextLine();
                altaDeEquipo();

                break;

            case 2:
                sc.nextLine();
                write("Eleccion 2 " + " " + now);

                System.out.println("Ingrese el nombre del equipo que quiere dar de baja: ");
                nombre = sc.nextLine().trim();

                System.out.println("Eliminando...");

                resultado = equipos.eliminar(new Equipo(nombre));

                cadena = (resultado) ? "Se elimino correctamente." : "No fue posible eliminar. El equipo no existe.";
                if (resultado) {
                    write("Se eliminó correctamente " + nombre + " " + now);

                } else {
                    write("No se eliminó correctamente " + nombre + " " + now);

                }

                System.out.println(cadena);
                break;

            case 3:
                sc.nextLine();
                modificarAtributoEquipo();

                break;

            case 4:

                write("Eleccion 4 " + now);

                System.out.println(equipos.toString());
                break;
            default:
                break;

        }

    }

    public static void modificarAtributoEquipo() throws IOException {
        Scanner sc = new Scanner(System.in);
        Equipo equipo;
        int puntosFavor;

        String nombre, grupo, cadena = "Los datos ingresados no son correctos.", eleccionAux, nombreE;

        System.out.println("Ingrese el nombre del equipo que desea modificar: ");
        nombreE = sc.nextLine().trim();

        equipo = (Equipo) equipos.recuperarElemento(new Equipo(nombreE));

        if (equipo != null) {
            System.out.println("Ingrese el atributo que desea modificar: (tecnico, grupo, pf(puntos a favor), pc(puntoscontra) ");
            eleccionAux = sc.nextLine().toUpperCase().trim();
            switch (eleccionAux) {
                case "TECNICO":
                    System.out.println("Ingrese el nombre el cual quiere modificar: ");
                    nombre = sc.nextLine().toUpperCase().trim();
                    equipo.setTecnico(nombre);
                    cadena = "Se modifico correctamente. \n" + equipo.toStringExtenso();
                    write("Modificación exitosa del atributo técnico " + nombre + " del equipo " + nombreE + now);

                    break;
                case "GRUPO":
                    System.out.println("Ingrese el grupo por el cual desea modificar (A,B,C,D): ");
                    grupo = sc.nextLine().toUpperCase().trim();
                    if (grupo.equals("A") || grupo.equals("B") || grupo.equals("C") || grupo.equals("D")) {
                        equipo.setGrupoInicial(grupo);
                        write("Modificación exitosa del atributo grupo " + grupo + " del equipo " + nombreE + now);
                        cadena = "Se modifico correctamente. \n" + equipo.toStringExtenso();

                    } else {
                        write("Modificación errónea del atributo grupo " + grupo + " del equipo " + nombreE + now);
                    }
                    break;
                case "PF":
                    System.out.println("Ingrese los goles a favor que quiere modificar: ");
                    puntosFavor = sc.nextInt();
                    if (puntosFavor > 0) {
                        equipo.setGolesAFavor(puntosFavor);
                        cadena = "Se modifico correctamente. \n" + equipo.toStringExtenso();
                        write("Modificación exitosa del atributo puntos a favor " + puntosFavor + " del equipo " + nombreE + now);

                    } else {
                        write("Modificación errónea atributo puntos a favor " + puntosFavor + " del equipo " + nombreE + now);

                        cadena = "No es posible actualizar goles negativos.";

                    }
                    break;

                case "PC":
                    System.out.println("Ingrese los goles en contra que quiere modificar: ");
                    puntosFavor = sc.nextInt();

                    if (puntosFavor > 0) {
                        write("Modificación exitosa del atributo puntos en contra " + puntosFavor + " del equipo " + nombreE + now);

                        equipo.setGolesEnContra(puntosFavor);
                        cadena = "Se modifico correctamente. \n" + equipo.toStringExtenso();

                    } else {
                        write("Modificación errónea del atributo puntos en contra " + puntosFavor + " del equipo " + nombreE + now);

                        cadena = "No es posible actualizar goles negativos.";
                    }

            }

        } else {
            write("El equipo no existe " + nombreE + " " + now);
            cadena = "El equipo no fue encontrado.";
        }
        System.out.println(cadena);
    }

    public static void altaDeEquipo() throws IOException {
        Scanner sc = new Scanner(System.in);

        String nombre, tecnico, grupo, cadena = "Los datos ingresados no son correctos.";
        int puntosFavor, puntosContra;
        Equipo equipo;
        boolean resultado;

        System.out.println("Ingrese el nombre del equipo: ");
        nombre = sc.nextLine().trim();
        write("Eleccion 1 " + now);

        equipo = (Equipo) equipos.recuperarElemento(new Equipo(nombre));
        if (equipo == null) {
            System.out.println("Ingrese el nombre del tecnico: ");
            tecnico = sc.nextLine().trim();
            System.out.println("Ingrese el grupo (A,B,C,D):");
            grupo = sc.nextLine().toUpperCase().trim();

            if ((grupo.equals("A") || grupo.equals("B") || grupo.equals("C") || grupo.equals("D"))) {
                System.out.println("Ingrese sus puntos a favor: ");
                puntosFavor = sc.nextInt();
                System.out.println("Ingrese sus puntos en contra: ");
                puntosContra = sc.nextInt();

                if (puntosFavor > 0 && puntosContra > 0) {
                    equipo = new Equipo(nombre, tecnico, grupo, puntosFavor, puntosContra);
                    resultado = equipos.insertar(equipo);
                    write("Se insertó equipo correctamente " + nombre + " " + now);
                    cadena = (resultado) ? "Se inserto correctamente." : "No se inserto correctamente.";
                    cadena = "Insertando equipo..." + cadena;

                } else {
                    write("Puntos incorrectos, PF: " + puntosFavor + " PC:" + puntosContra + " " + now);

                }

            } else {
                write("Grupo incorrecto " + grupo + " " + now);
            }
        } else {
            write("El equipo ya existe " + nombre + " " + now);
            cadena = "El equipo ya existe";

        }
        System.out.println(cadena);

    }

    public static GrafoEtiquetado cargarMapa() {
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        String cadena;
        FileReader ciudades, rutas;
        BufferedReader lector;
        Ciudad ciudad;
        String[] ruta;

        try {
            ciudades = new FileReader("src/ArchivoCiudades.txt");
            rutas = new FileReader("src/ArchivoRutas.txt");
            lector = new BufferedReader(ciudades);
            //Primero inserto los vértices.
            while ((cadena = lector.readLine()) != null) {
                ciudad = crearCiudad(cadena);
                //Las ciudades que tengan sede, deben estar en los partidos.

                grafo.insertarVertice(ciudad);

            }
            //Una vez cargadas las ciudades, cargo las rutas.
            lector = new BufferedReader(rutas);
            while ((cadena = lector.readLine()) != null) {

                ruta = cadena.split(";");

                grafo.insertarArco(new Ciudad(ruta[0]), new Ciudad(ruta[1]), Integer.parseInt(ruta[2]));

            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());

        }

        return grafo;

    }

    public static Ciudad crearCiudad(String cadena) {

        String[] atributos = cadena.split(";");

        Ciudad ciudad = new Ciudad(atributos[0], Boolean.parseBoolean(atributos[1]), Boolean.parseBoolean(atributos[2]));

        return ciudad;

    }

    public static TablaHash cargarPartidos() {
        //Paso la lista de equipos por parámetro, ya que iré actualizando los goles a medida que cargo los partidos.
        //Utilizo MapeoAUno como tabla goles para actualizar los goles de manera eficiente en los equipos luego.

        TablaHash partidos = new TablaHash();
        String cadena;
        Equipo equipo1, equipo2;
        FileReader archivo;
        BufferedReader lector;
        Partido partido;

        try {
            archivo = new FileReader("src/ArchivoPartidos.txt");
            lector = new BufferedReader(archivo);

            while ((cadena = lector.readLine()) != null) {
                partido = crearPartido(cadena);
                //A medida que creo los partidos, actualizo los goles de cada equipo.
                equipo1 = (Equipo) equipos.recuperarElemento(new Equipo(partido.getNombreEq1()));
                equipo2 = (Equipo) equipos.recuperarElemento(new Equipo(partido.getNombreEq2()));

                equipo1.actualizarGoles(partido.getGolesEq1(), partido.getGolesEq2());

                equipo2.actualizarGoles(partido.getGolesEq2(), partido.getGolesEq1());
                if (partido.getInstancia().equals("GRUPO")) {
                    equipo1.actualizarPuntos(partido.getGolesEq1(), partido.getGolesEq2());
                    equipo2.actualizarPuntos(partido.getGolesEq2(), partido.getGolesEq1());

                }

                partidos.insertar(partido);

            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());

        }
        return partidos;

    }

    public static Partido crearPartido(String cadena) {
        String[] arreglo = cadena.split(";"), golesAux;
        String goles;

        Partido partido = new Partido(arreglo[0], arreglo[1], arreglo[2], arreglo[3], arreglo[4], Integer.parseInt(arreglo[5]), Integer.parseInt(arreglo[6]));

        return partido;

    }

    public static void mostrarCiudadesSede() throws IOException {
        Lista lista = mapaCiudades.listarEnProfundidad();
        Lista listaAux = new Lista();
        Ciudad ciudad;

        while (!lista.esVacia()) {
            ciudad = (Ciudad) lista.recuperar(1);

            if (ciudad.getSede()) {
                listaAux.insertar(ciudad, listaAux.longitud() + 1);

            }
            lista.eliminar(1);

        }
        write("Listado de ciudades que son sede " + " " + now);
        System.out.println("Listado de ciudades que son sede: \n" + listaAux.toString());

    }

    public static ArbolAVL cargarEquipos() {

        ArbolAVL equiposEquipos = new ArbolAVL();
        String cadena;
        FileReader archivo;
        BufferedReader lector;
        Equipo equipo;

        try {
            archivo = new FileReader("src/ArchivoEquipos.txt");
            lector = new BufferedReader(archivo);

            while ((cadena = lector.readLine()) != null) {

                equipo = crearEquipo(cadena);

                equiposEquipos.insertar(equipo);

            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());

        }

        return equiposEquipos;

    }

    public static Equipo crearEquipo(String cadena) {
        String[] atributos = cadena.split(";");

        Equipo equipo = new Equipo(atributos[0], atributos[1], atributos[2]);

        return equipo;

    }
  
    public static void write(String cadena) throws IOException {

        try (FileWriter fout = new FileWriter(obj, true)) {
            if (obj.canWrite()) {
                fout.write(cadena + "\n");
            }
        }
    }

}

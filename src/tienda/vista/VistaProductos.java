package tienda.vista;

import empleado.dominio.Empleado;
import java.util.List;
import java.util.Scanner;
import producto.control.GestionProductos;
import producto.dao.ProductoDAO;
import producto.dominio.Producto;
import tienda.control.GestionTienda;
import tienda.control.MenuPrincipal;
import tienda.control.MenuProductos;
import util.Color;

public class VistaProductos {

    Empleado empleadoAuten;

    public VistaProductos(Empleado empleadoLogado) {
        this.empleadoAuten = empleadoLogado;

    }

    public Empleado getEmpleadoAuten() {
        return empleadoAuten;
    }

    public static MenuProductos opcionDesdeMenuProductos(Empleado empleado) {
        borrarPantalla();
        System.out.println(Color.BLUE + "--------Menú productos ------------" + Color.DEFAULT);
        System.out.println("1.1 ._Modificar nombre de Producto");
        System.out.println("1.2 ._Modificar precio de Producto");
        System.out.println("1.3 ._Modificar codigo de Producto");
        System.out.println("1.4 ._Terminar ");
        System.out.println(Color.BLUE + "------------------------------------" + Color.DEFAULT);

        int opcion = pedirOpcionEnRango(1, 4);

        MenuProductos menu = null;

        switch (opcion) {
            case 1:
                menu = MenuProductos.MODIFICAR_NOMBRE;
                GestionProductos g = new GestionProductos(empleado);
                g.readProductos();
                g.actualizarNombre();

                break;
            case 2:
                menu = MenuProductos.MODIFICAR_PRECIO;

                GestionProductos gestiona = new GestionProductos(empleado);
                gestiona.readProductos();
                gestiona.actualizarPrecio();
                break;
            case 3:
                menu = MenuProductos.MODIFICAR_CODIGO;
                GestionProductos gestion = new GestionProductos(empleado);
                gestion.readProductos();
                gestion.actualizarCodigo();
                break;
            case 4:
                menu = MenuProductos.SALIR;
                
                GestionTienda gestionTienda = new GestionTienda();
                
                gestionTienda.menu(empleado);

                break;
        }

        return menu;

    }

    private static int pedirOpcionEnRango(int min, int max) {

        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean hayError = true;

        while (hayError) {
            System.out.print("Seleccione una opción: ");
            if (leerTeclado.hasNextInt()) {
                opcion = leerTeclado.nextInt();
                hayError = opcion < min || opcion > max;
                if (hayError) {
                    muestraMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                }
            } else {
                muestraMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                leerTeclado.next();
            }
        }

        return opcion;
    }

    public static void mensajeBienvenida(Empleado empleado) {
        muestraMensaje("Bienvenido " + empleado.getNombre());
        System.out.println();
    }

    public static void muestraMensaje(String mensaje, Color color) {
        System.out.println(color + mensaje + Color.DEFAULT);
    }

    public static void muestraMensaje(String mensaje) {
        muestraMensaje(mensaje, Color.DEFAULT);
    }

    private static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

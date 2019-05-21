package tienda.control;

import empleado.control.GestionEmpleados;
import empleado.dominio.Empleado;
import empleado.errores.contraseñaException;
import empleado.errores.usuarioException;
import factura.Pedido;
import java.util.ArrayList;
import java.util.List;
import producto.dominio.Producto;
import tienda.vista.VistaProductos;
import tienda.vista.VistaTienda;
import util.Color;

public class GestionTienda {

    private Empleado empleadoAutenticado;
    private List<Producto> cesta;
    private GestionEmpleados gestionaEmpleados;
    private GestionEmpleados gestionaProductos;

    public GestionTienda() {
        empleadoAutenticado = null;
        cesta = new ArrayList<>();
        gestionaEmpleados = new GestionEmpleados(empleadoAutenticado);
    }

    public void iniciar() {

        boolean esLoginCorrecto = false;
        while (!esLoginCorrecto) {
            try {
                gestionaEmpleados.login();
                esLoginCorrecto = true;
            } catch (usuarioException e) {
                System.err.println(e.getMessage());
                System.err.println("Código de error: " + e.getCodigoError());
            } catch (contraseñaException p) {
                System.err.println(p.getMessage());
                System.err.println(Color.ANSI_RED + "Código de error: " + p.getCodigoError() + Color.DEFAULT);

            }
        }

        empleadoAutenticado = gestionaEmpleados.getEmpleadoAutenticado();
        // VistaTienda.mensajeBienvenida(empleadoAutenticado);
        System.out.println("  Has iniciado sesión como " + empleadoAutenticado.getNombre() + " " + empleadoAutenticado.getApellidos() + "\n");

        menu(empleadoAutenticado);

    }

    public void menu(Empleado empleadoAutenticado) {
        switch (VistaTienda.opcionDesdeMenuPrincipal()) {
            case HACER_PEDIDO:
                Pedido pedido = new Pedido(empleadoAutenticado);

                pedido.Pedido();
                //hacerPedido
                break;
            case MODIFICAR_PRODUCTO:
                VistaProductos vista = new VistaProductos(empleadoAutenticado);
                vista.opcionDesdeMenuProductos(empleadoAutenticado);
//                GestionProductos c = new GestionProductos();
//                c.menuProductos();

                break;
            case CAMBIAR_PASSWORD:
                GestionEmpleados gestiona = new GestionEmpleados(empleadoAutenticado);
                gestiona.actualizarPassword(empleadoAutenticado);
                // cambiarPassword
                break;
            case CERRAR_SESION:

                GestionEmpleados g = new GestionEmpleados(empleadoAutenticado);
                g.cerrarSesion(empleadoAutenticado);
                //11cerrarSesion
                break;
        }
    }

}

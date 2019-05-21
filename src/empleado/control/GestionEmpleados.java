package empleado.control;

import empleado.dominio.Empleado;
import java.util.Scanner;
import util.Color;
import empleado.dao.*;
import empleado.errores.codigoError;
import empleado.errores.contraseñaException;
import empleado.errores.usuarioException;
import factura.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tienda.control.GestionTienda;
import empleado.dao.EmpleadoDAOImp;

public class GestionEmpleados {

    Scanner scan = new Scanner(System.in);

    ControladorEmpleado controlador;
    Empleado empleadoAutenticado;
    EmpleadoDAOImp empleadoDAOImp = new EmpleadoDAOImp();

    public GestionEmpleados(Empleado empleado) {
        this.controlador = new ControladorEmpleado();
        this.empleadoAutenticado = empleado;
    }

    public void login() throws usuarioException, contraseñaException {
        Scanner leerTeclado = new Scanner(System.in);
        boolean esEmpleadoValido = false;
        boolean esPasswordValido = false;

        System.out.println(Color.BLUE + "\tBienvenido a la tienda");
        System.out.println(Color.GREEN + "***************************************\n" + Color.DEFAULT);
        System.out.print(Color.EXITO + "Introduce el código de tu usuario: ");
        while (!leerTeclado.hasNextInt()) {
            System.out.println(Color.ERROR + "Debe escribir un valor numérico" + Color.DEFAULT);
            System.out.print("Introduce el código de tu usuario: ");
            leerTeclado.next();
        }
        int codigoEntrada = leerTeclado.nextInt();

        System.out.print("Introduce tu contraseña: ");
        String passwordEntrada = leerTeclado.next();

        System.out.println("");

        // empleadoAutenticado = controlador.getEmpleadoPorCodigo(codigoEntrada);
        if (esCodigoValido(codigoEntrada)) {
            empleadoAutenticado = controlador.getEmpleadoPorCodigo(codigoEntrada);
            esEmpleadoValido = true;
            if (passwordEntrada.equals(empleadoAutenticado.getPassword())) {
                esPasswordValido = true;
            }
        }

        if (!esEmpleadoValido) {
            throw new usuarioException("Código incorrecto", codigoError.LOGIN_INCORRECTO);

        } else if (!esPasswordValido) {
            throw new contraseñaException("Contraseña incorrecta", codigoError.CONTRASEÑA_INCORRECTA);

        }

    }

    public boolean esCodigoValido(int codigo) {
        boolean valido = false;
        for (Empleado empleado : empleadoDAOImp.getEmpleados()) {
            if (codigo == empleado.getCodigo()) {
                valido = true;
            }
        }
        return valido;
    }

    public Empleado getEmpleadoAutenticado() {
        return empleadoAutenticado;
    }

    public void actualizarPassword(Empleado empleado) {
        Scanner scan = new Scanner(System.in);

        boolean next = true;
        while (next) {
            System.out.println("\nEntre la contraseña actual de : " + empleado.getNombre());

            String password = scan.next();

            if (password.equals(empleado.getPassword())) {
                next = true;
                System.out.println("Entra la nueva contraseña: ");
                String newPassword = scan.next();
                var empleados = new EmpleadoDAOImp();
                empleados.actualizarPassword(empleado, newPassword);
                System.out.println(Color.GREEN + "Contraseña cambiada correctamente \n" + Color.DEFAULT);

                GestionTienda gestionTienda = new GestionTienda();
                empleado.setPassword(newPassword);
                gestionTienda.menu(empleado);
            } else {

            }

            System.err.println("La contraseña introducida no es correcta");

        }

    }

    public void cerrarSesion(Empleado empleado) {

        boolean sigue = true;
        while (sigue) {
            System.out.print("Entre el password para " + empleado.getNombre() + " : ");

            String passw = scan.next();
            if (esPasswordCorrecta(empleado, passw)) {

                System.out.println(Color.GREEN + "  Ha salido de la cuenta de " + empleado.getNombre() + "\n" + Color.DEFAULT);
                Pedido pedido = new Pedido();
                pedido.vaciarArray();
                sigue = false;

            } else {
                System.err.println("Contraseña no valida");
                // System.out.println("pruebe otra vez");
            }
        }
        GestionTienda gestionTienda = new GestionTienda();
        gestionTienda.iniciar();

    }

    private boolean esPasswordCorrecta(Empleado emple, String password) {

        boolean seguir = false;
        if (password.equals(emple.getPassword())) {

            seguir = true;

        }

        return seguir;
    }

}

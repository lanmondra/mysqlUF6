package producto.dao;

import conexion.ConexionBD;
import conexion.ConexionBD;
import empleado.dao.EmpleadoDAOImp;
import empleado.dominio.Empleado;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import producto.dominio.Producto;

public class ProductoDAOImp implements ProductoDAO {

    List<Producto> products;

    Scanner scan = new Scanner(System.in);

    //@Override
    public ProductoDAOImp() {

         List<Producto> productList = new ArrayList<Producto>();
        String query = "SELECT * FROM productos";
        Statement statement;
        ResultSet result = null;
        /*
        Statement statement = null;
        ResultSet result = null;
         */

        try {
            Connection connect = ConexionBD.conectar();
            statement = (Statement) connect.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("fallo al conectar con la base de datos ");
        } finally {

            int productCode = 0;
            String productName = null;
            String productDescription = null;
            double productPrice = 0.0;

            try {

                while (result.next()) {
                    productCode = result.getInt("p_codigo");
                    productName = result.getString("p_nombre");
                    productDescription = result.getString("p_descripcion");
                    productPrice = result.getDouble("p_precio");

                    productList.add(new Producto(productCode, productName, productDescription, productPrice));
                }
            } catch (SQLException ex) {
                System.err.println("no se puede acceder a la base de datos");
            }

        }

        setProducts(productList);

    }

    // @Override
    public List<Producto> leerProducts() {

        Producto Productos = new Producto();
        List<Producto> productList = new ArrayList<>();

        try {
            var conection = ConexionBD.conectar();

            Statement sentencia = conection.createStatement();

            ResultSet resultado = sentencia.executeQuery("Select * from productos;");

            while (resultado.next()) {

                while (resultado.next()) {
                    int codigo = resultado.getInt("p_codigo");
                    String nombre = resultado.getString("p_nombre");
                    String descripcion = resultado.getString("p_descripcion");
                    Double precio = resultado.getDouble("p_precio");

                    productList.add(new Producto(codigo, nombre, descripcion, precio));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de lectura en: ");

        }
        return productList;
    }

    public List<Producto> getProductos() {
        return products;
    }

    public void setProducts(List<Producto> products) {
        this.products = products;
    }

    public void updateCode(int productCode, int productNewCode) {

        Producto productos = new Producto();

        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getCodigo() == productCode) {
                productos.setDescripcion(this.products.get(i).getDescripcion());
                productos.setPrecio(this.products.get(i).getPrecio());
                productos.setNombre(this.products.get(i).getNombre());
                productos.setCodigo(productNewCode);
                this.products.set(i, productos);
            }
        }
        escribirEnBD(productCode, productos);
    }

    public void updateName(int productCode, String nuevoNombre) {

        Producto productos = new Producto();

        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getCodigo() == productCode) {
                productos.setDescripcion(this.products.get(i).getDescripcion());
                productos.setPrecio(this.products.get(i).getPrecio());
                productos.setNombre(nuevoNombre);
                productos.setCodigo(this.products.get(i).getCodigo());
                this.products.set(i, productos);
            }
        }
        escribirEnBD(productCode, productos);
    }

    //@Override
    public void updatePrice(int productCode, double productPrice) {
        Producto productos = new Producto();

        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getCodigo() == productCode) {
                productos.setDescripcion(this.products.get(i).getDescripcion());
                productos.setPrecio(productPrice);
                productos.setNombre(this.products.get(i).getNombre());
                productos.setCodigo(this.products.get(i).getCodigo());
                this.products.set(i, productos);
            }
        }
        escribirEnBD(productCode, productos);
    }

    public void escribirEnBD(int codProd, Producto productoEntrante) {
        String query;
        Statement statement = null;
        ResultSet result = null;

        try {
            query = "UPDATE productos SET "
                    + "p_codigo = " + productoEntrante.getCodigo() + ", "
                    + "p_nombre = " + "\"" + productoEntrante.getNombre() + "\"" + ", "
                    + "p_descripcion = " + "\"" + productoEntrante.getDescripcion() + "\"" + ", "
                    + "p_precio = " + productoEntrante.getPrecio() + " "
                    + "WHERE p_codigo = " + codProd;
            //System.out.println(query);

            Connection connect = ConexionBD.conectar();
            statement = (Statement) connect.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            System.err.println("no se puede conectara a las bases de datos");
        }
//        finally {
       
        this.products = (new ProductoDAOImp()).leerProducts();
        //  }
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

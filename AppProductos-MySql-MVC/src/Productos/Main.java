/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Productos;

import Productos.controller.ProductosController;
import Productos.model.Producto;
import Productos.repository.ProductoRepository;
import Productos.views.frmProducto;

/**
 *
 * @author AndresMatt
 */
public class Main {
    
    public static void main (String [] args){
        
        Producto producto = new Producto();
        frmProducto viewProducto = new frmProducto();
        ProductoRepository repoProducto = new ProductoRepository();
        ProductosController controllerProducto =  new ProductosController(producto, repoProducto, viewProducto);
        
        controllerProducto.iniciar();
        viewProducto.setVisible(true);
        
    }
}

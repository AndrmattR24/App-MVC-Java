package Productos.controller;

import Productos.model.Producto;
import Productos.repository.ProductoRepository;
import Productos.views.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ProductosController implements ActionListener{
    
    private final Producto producto;
    private final ProductoRepository repoProducto;
    private final frmProducto  viewProducto;
    
    
    public ProductosController(Producto producto ,ProductoRepository repoProducto ,frmProducto viewProducto){
        this.producto = producto;
        this.repoProducto = repoProducto;
        this.viewProducto = viewProducto;
        this.viewProducto.btnGuardar.addActionListener(this);
        this.viewProducto.btnEditar.addActionListener(this);
        this.viewProducto.btnEliminar.addActionListener(this);
        this.viewProducto.btnLimpiar.addActionListener(this);
        this.viewProducto.btnBuscar.addActionListener(this);
    }
    
    //iniciamos la ventana
    public void iniciar(){
        viewProducto.setTitle("CRUD PRODUCTOS");
        viewProducto.setLocationRelativeTo(null);
        viewProducto.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent accion) {
        
        //validamos la accion que se llamo
       
        if(accion.getSource() == viewProducto.btnGuardar){
            
            if(viewProducto.txtCodigo.getText().equals("")||
               viewProducto.txtNombre.getText().equals("")||
               viewProducto.txtPrecio.getText().equals("")||
               viewProducto.txtCantidad.getText().equals("")){
               
                JOptionPane.showMessageDialog(viewProducto, "Rellenar Todos los campos", "Aviso:", JOptionPane.INFORMATION_MESSAGE);
            }else{
                
                producto.setCodigo(viewProducto.txtCodigo.getText());
                producto.setNombre(viewProducto.txtNombre.getText());
                producto.setPrecio(Double.parseDouble(viewProducto.txtPrecio.getText()));
                producto.setCantidad(Integer.parseInt(viewProducto.txtCantidad.getText()));
            
                if(repoProducto.save(producto)){
                    JOptionPane.showMessageDialog(viewProducto, "Producto Guardado", "Exito:", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                }
                else{
                    JOptionPane.showMessageDialog(viewProducto, "Error al Guardar Producto", "Error:", JOptionPane.ERROR_MESSAGE);
                }      
            }
        }
        if(accion.getSource() == viewProducto.btnEditar){
            
            if(viewProducto.txtCodigo.getText().equals("")){
                JOptionPane.showMessageDialog(viewProducto, "Ingresa un codigo para continuar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                producto.setId(Integer.parseInt(viewProducto.txtId.getText()));
                producto.setCodigo(viewProducto.txtCodigo.getText());
                producto.setNombre(viewProducto.txtNombre.getText());
                producto.setPrecio(Double.parseDouble(viewProducto.txtPrecio.getText()));
                producto.setCantidad(Integer.parseInt(viewProducto.txtCantidad.getText()));
            
                if(repoProducto.update(producto)){
                    JOptionPane.showMessageDialog(viewProducto, "Producto Editado", "Exito:", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(viewProducto, "Error al Editar Producto", "Error:", JOptionPane.ERROR_MESSAGE);
                } 
            }
        }
        if(accion.getSource() == viewProducto.btnEliminar){
            
            if(viewProducto.txtCodigo.getText().equals("")){
                JOptionPane.showMessageDialog(viewProducto, "Ingresa un codigo para continuar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
              producto.setId(Integer.parseInt(viewProducto.txtId.getText()));
            
                if(repoProducto.delete(producto)){
                    JOptionPane.showMessageDialog(viewProducto, "Producto Eliminado", "Exito:", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                }
            else{
                JOptionPane.showMessageDialog(viewProducto, "Error al Eliminar Producto", "Error:", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }  
          }
        }
        
        if(accion.getSource() == viewProducto.btnBuscar){
            
             if(viewProducto.txtCodigo.getText().equals("")){
                JOptionPane.showMessageDialog(viewProducto, "Ingresa un codigo para continuar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
              producto.setCodigo(viewProducto.txtCodigo.getText());
            
                if(repoProducto.buscar(producto)){
               
                    viewProducto.txtId.setText(String.valueOf(producto.getId()));
                
                    //traemos los datos del producto buscado
                    viewProducto.txtCodigo.setText(producto.getCodigo());
                    viewProducto.txtNombre.setText(producto.getNombre());
                    viewProducto.txtPrecio.setText(String.valueOf(producto.getPrecio()));
                    viewProducto.txtCantidad.setText(String.valueOf(producto.getCantidad()));
                }
                else{
                    JOptionPane.showMessageDialog(viewProducto, "Producto No existe", "Aviso:", JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        }
        
        if(accion.getSource() == viewProducto.btnLimpiar){
            limpiar();
        }
    }
    public void limpiar(){
        viewProducto.txtId.setText(null);        
        viewProducto.txtCodigo.setText(null);
        viewProducto.txtNombre.setText(null);
        viewProducto.txtPrecio.setText(null);
        viewProducto.txtCantidad.setText(null);
    }
}

// Proyecto.java

import java.util.ArrayList; // Importa la implementación dinámica de listas ArrayList.
import java.util.List;      // Importa la interfaz List para declarar colecciones.

 // Clase base Producto
class Producto {
    private int id;           // Identificador único del producto.
    private String nombre;    // Nombre del producto.
    private double precio;    // Precio unitario del producto.
    private int stock;        // Cantidad disponible en inventario.

    public Producto(int id, String nombre, double precio, int stock) { // Constructor que inicializa campos.
        this.id = id;                // Asigna el id recibido al atributo de la instancia.
        this.nombre = nombre;        // Asigna el nombre recibido al atributo de la instancia.
        this.precio = precio;        // Asigna el precio recibido al atributo de la instancia.
        this.stock = stock;          // Asigna el stock recibido al atributo de la instancia.
    }

    public void actualizarStock(int cantidad) { // Método para disminuir el stock (por ejemplo al vender).
        this.stock -= cantidad;                 // Resta la cantidad indicada al stock actual.
    }

    public String mostrarInfo() {               // Método que devuelve una representación legible del producto.
        return nombre + " - Precio: $" + precio + ", Stock: " + stock; // Construye y retorna la cadena.
    }

    public double getPrecio() { // Getter para el precio.
        return precio;          // Devuelve el precio del producto.
    }

    public String getNombre() { // Getter para el nombre.
        return nombre;          // Devuelve el nombre del producto.
    }

    public int getStock() {     // Getter para el stock.
        return stock;           // Devuelve la cantidad en inventario.
    }
}

 // Clase ProductoDigital que hereda de Producto
class ProductoDigital extends Producto {
    private String formato;     // Almacena el formato del producto digital (ej. PDF, MP3).

    public ProductoDigital(int id, String nombre, double precio, String formato) { // Constructor.
        super(id, nombre, precio, 0); // Llama al constructor padre; pone stock = 0 porque no aplica.
        this.formato = formato;       // Asigna el formato recibido al atributo.
    }

    @Override
    public String mostrarInfo() { // Sobrescribe mostrarInfo para incluir formato y obviar stock.
        return getNombre() + " (Digital - " + formato + ") - Precio: $" + getPrecio(); // Construye y retorna la cadena.
    }
}

 // Clase Cliente
class Cliente {
    private int id;         // Identificador único del cliente.
    private String nombre;  // Nombre del cliente.
    private String correo;  // Correo electrónico del cliente.

    public Cliente(int id, String nombre, String correo) { // Constructor.
        this.id = id;            // Asigna el id.
        this.nombre = nombre;    // Asigna el nombre.
        this.correo = correo;    // Asigna el correo.
    }

    public String registrarse() { // Método que simula el registro y devuelve un mensaje.
        return "Cliente " + nombre + " registrado con correo " + correo; // Mensaje de confirmación.
    }

    public Pedido realizarPedido(Carrito carrito) { // Crea un pedido a partir del carrito proporcionado.
        return new Pedido(9001, this, carrito, "Pendiente"); // Devuelve un Pedido con estado inicial "Pendiente".
    }

    public String getNombre() { // Getter para el nombre (útil para mensajes).
        return nombre;          // Devuelve el nombre del cliente.
    }
}

 // Clase Carrito
class Carrito {
    private int id;                          // Identificador del carrito.
    private List<ItemCarrito> productos;     // Lista que guarda objetos ItemCarrito (producto + cantidad).

    public Carrito(int id) { // Constructor.
        this.id = id;                         // Asigna el id del carrito.
        this.productos = new ArrayList<>();   // Inicializa la lista de productos vacía.
    }

    public void agregarProducto(Producto producto, int cantidad) { // Agrega un producto y su cantidad al carrito.
        productos.add(new ItemCarrito(producto, cantidad)); // Añade un nuevo ItemCarrito a la lista.
        if (!(producto instanceof ProductoDigital)) {       // Si el producto NO es digital (es físico)...
            producto.actualizarStock(cantidad);             // ...descuenta esa cantidad del stock del producto.
        }
    }

    public double calcularTotal() { // Calcula el total del carrito multiplicando precio x cantidad.
        double total = 0;           // Inicializa acumulador en 0.
        for (ItemCarrito item : productos) { // Recorre cada elemento del carrito.
            total += item.getProducto().getPrecio() * item.getCantidad(); // Suma precio * cantidad al total.
        }
        return total; // Devuelve el total acumulado.
    }

    public List<ItemCarrito> getProductos() { // Getter para obtener la lista de productos del carrito.
        return productos;                      // Retorna la lista de ItemCarrito.
    }
}

 // Clase auxiliar para manejar producto + cantidad dentro del carrito
class ItemCarrito {
    private Producto producto; // Referencia al producto.
    private int cantidad;      // Cantidad de ese producto en el carrito.

    public ItemCarrito(Producto producto, int cantidad) { // Constructor.
        this.producto = producto; // Asigna el producto.
        this.cantidad = cantidad; // Asigna la cantidad.
    }

    public Producto getProducto() { // Getter del producto.
        return producto;            // Devuelve el producto asociado.
    }

    public int getCantidad() { // Getter de la cantidad.
        return cantidad;       // Devuelve la cantidad almacenada.
    }
}

 // Clase Pedido
class Pedido {
    private int id;         // Identificador del pedido.
    private Cliente cliente;// Cliente que hizo el pedido.
    private Carrito carrito; // Carrito asociado al pedido.
    private String estado;   // Estado actual del pedido (p.ej., "Pendiente", "Procesado").

    public Pedido(int id, Cliente cliente, Carrito carrito, String estado) { // Constructor.
        this.id = id;             // Asigna el id del pedido.
        this.cliente = cliente;   // Asigna la referencia al cliente.
        this.carrito = carrito;   // Asigna la referencia al carrito.
        this.estado = estado;     // Asigna el estado inicial.
    }

    public String procesarPedido() { // Método que procesa el pedido y cambia su estado a "Procesado".
        this.estado = "Procesado"; // Actualiza el estado del pedido.
        return "Pedido " + id + " procesado para cliente " + cliente.getNombre() + "."; // Retorna mensaje de confirmación.
    }

    public void cambiarEstado(String nuevoEstado) { // Permite cambiar el estado a cualquier valor.
        this.estado = nuevoEstado; // Asigna el nuevo estado pasado como argumento.
    }

    public String getEstado() { // Getter para consultar el estado actual del pedido.
        return estado;         // Devuelve el estado.
    }
}

 // Clase principal con el método main que actúa como punto de entrada de la aplicación
public class Proyecto {
    public static void main(String[] args) { // Método main: se ejecuta al iniciar la aplicación.
        Producto p1 = new Producto(101, "Laptop Lenovo", 2500.0, 5); // Crea un producto físico con id 101 y stock 5.
        Producto p2 = new Producto(102, "Mouse Logitech", 50.0, 20); // Crea otro producto físico con id 102 y stock 20.
        Producto p3 = new ProductoDigital(103, "Ebook Python", 15.0, "PDF"); // Crea un producto digital (formato PDF).

        Cliente cliente1 = new Cliente(1, "Ana Torres", "ana@email.com"); // Instancia un cliente con datos.
        Carrito carrito1 = new Carrito(501); // Crea un carrito con id 501.

        carrito1.agregarProducto(p1, 1); // Agrega 1 unidad de la laptop al carrito (reduce stock de p1 a 4).
        carrito1.agregarProducto(p2, 2); // Agrega 2 unidades del mouse (reduce stock de p2 a 18).
        carrito1.agregarProducto(p3, 1); // Agrega 1 ebook al carrito (no afecta stock porque es digital).

        Pedido pedido1 = cliente1.realizarPedido(carrito1); // El cliente genera un pedido a partir del carrito.

        // Salidas en consola para verificar funcionamiento
        System.out.println(cliente1.registrarse()); // Imprime el mensaje de registro del cliente.

        System.out.println("\nProductos en el carrito:"); // Encabezado de la lista de productos.
        for (ItemCarrito item : carrito1.getProductos()) { // Itera por cada ItemCarrito en el carrito.
            System.out.println("- " + item.getProducto().mostrarInfo() + " x" + item.getCantidad()); // Imprime info y cantidad.
        }

        System.out.println("\nTotal del carrito: " + carrito1.calcularTotal()); // Imprime el total a pagar.
        System.out.println(pedido1.procesarPedido()); // Procesa el pedido e imprime la confirmación.
        System.out.println("Estado final del pedido: " + pedido1.getEstado()); // Imprime el estado final del pedido.
    }
}

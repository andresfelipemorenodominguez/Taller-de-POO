import java.util.ArrayList;
import java.util.List;

// ---------------- Clase Persona (superclase) ----------------
class Persona {
    int id;           // Identificador único de la persona
    String nombre;    // Nombre de la persona
    String correo;    // Correo de la persona

    // Constructor de Persona
    public Persona(int id, String nombre, String correo) {
        this.id = id;          // Asigna el id
        this.nombre = nombre;  // Asigna el nombre
        this.correo = correo;  // Asigna el correo
    }

    // Método para mostrar la información de la persona
    public void mostrarInfo() {
        System.out.println("ID: " + id +
                           " | Nombre: " + nombre +
                           " | Correo: " + correo);
    }
}

// ---------------- Clase Cliente (hereda de Persona) ----------------
class Cliente extends Persona {

    // Constructor de Cliente (usa super para reutilizar el constructor de Persona)
    public Cliente(int id, String nombre, String correo) {
        super(id, nombre, correo); // Llama al constructor de la clase padre Persona
    }

    @Override // Polimorfismo: redefinimos el método mostrarInfo()
    public void mostrarInfo() {
        System.out.println("Cliente: " + nombre +
                           " | Correo: " + correo);
    }
}

// ---------------- Clase Producto ----------------
class Producto {
    int id;          // Identificador del producto
    String nombre;   // Nombre del producto
    float precio;    // Precio unitario
    int stock;       // Cantidad disponible en inventario

    // Constructor de Producto
    public Producto(int id, String nombre, float precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Método para mostrar información del producto
    public void mostrarInfo() {
        System.out.println("ID: " + id +
                           " | Nombre: " + nombre +
                           " | Precio: $" + precio +
                           " | Stock: " + stock);
    }
}

// ---------------- Clase Carrito ----------------
class Carrito {
    int id;                     // Identificador del carrito
    List<Producto> productos;   // Lista de productos en el carrito

    // Constructor de Carrito (inicializa la lista vacía)
    public Carrito(int id) {
        this.id = id;
        this.productos = new ArrayList<>();
    }

    // Método para agregar un producto al carrito
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Método para mostrar los productos en el carrito
    public void mostrarInfo() {
        System.out.println("Carrito ID: " + id);
        for (Producto p : productos) { // Recorremos cada producto
            p.mostrarInfo();
        }
    }

    // Método para calcular el total a pagar en el carrito
    public float calcularTotal() {
        float total = 0;
        for (Producto p : productos) { // Sumamos el precio de cada producto
            total += p.precio;
        }
        return total;
    }
}

// ---------------- Clase Pedido ----------------
class Pedido {
    int id;           // Identificador del pedido
    Cliente cliente;  // Cliente que hace el pedido
    Carrito carrito;  // Carrito asociado al pedido
    String estado;    // Estado del pedido (Ej: Pendiente, Pagado, Enviado)

    // Constructor de Pedido
    public Pedido(int id, Cliente cliente, Carrito carrito, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.carrito = carrito;
        this.estado = estado;
    }

    // Método para mostrar la información completa del pedido
    public void mostrarInfo() {
        System.out.println("Pedido ID: " + id +
                           " | Estado: " + estado +
                           " | Total: $" + carrito.calcularTotal());
        cliente.mostrarInfo();   // Muestra info del cliente (polimorfismo)
        carrito.mostrarInfo();   // Muestra productos del carrito
    }
}

// ---------------- Clase Main (ejecución principal) ----------------
public class Main {
    public static void main(String[] args) {
        // Crear productos
        Producto p1 = new Producto(1, "Laptop", 2500.99f, 10);
        Producto p2 = new Producto(2, "Mouse", 25.50f, 50);

        // Crear un carrito y agregar productos
        Carrito carrito = new Carrito(101);
        carrito.agregarProducto(p1);
        carrito.agregarProducto(p2);

        // Crear cliente
        Cliente cliente = new Cliente(1, "Andrés", "andres@mail.com");

        // Crear pedido con cliente y carrito
        Pedido pedido = new Pedido(5001, cliente, carrito, "Pendiente");

        // Mostrar toda la información del pedido
        pedido.mostrarInfo();
    }
}
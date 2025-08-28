// Importamos clases necesarias para manejar listas dinámicas
import java.util.ArrayList; // Implementación de lista dinámica
import java.util.List;      // Interfaz genérica para listas

// ---------------- Clase Persona (superclase) ----------------
class Persona {
    int id;           // Identificador único de la persona
    String nombre;    // Nombre de la persona
    String correo;    // Correo electrónico de la persona

    // Constructor de Persona: inicializa los atributos
    public Persona(int id, String nombre, String correo) {
        this.id = id;          // Asigna el valor recibido al campo id
        this.nombre = nombre;  // Asigna el valor recibido al campo nombre
        this.correo = correo;  // Asigna el valor recibido al campo correo
    }

    // Método para mostrar la información de la persona
    public void mostrarInfo() {
        // Imprime el ID, nombre y correo en consola
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
        // Imprime una versión personalizada para Cliente
        System.out.println("Cliente: " + nombre +
                           " | Correo: " + correo);
    }
}

// ---------------- Clase Producto ----------------
class Producto {
    int id;          // Identificador del producto
    String nombre;   // Nombre del producto
    float precio;    // Precio unitario del producto
    int stock;       // Cantidad disponible en inventario

    // Constructor de Producto: inicializa atributos
    public Producto(int id, String nombre, float precio, int stock) {
        this.id = id;           // Asigna id al campo correspondiente
        this.nombre = nombre;   // Asigna nombre al campo correspondiente
        this.precio = precio;   // Asigna precio al campo correspondiente
        this.stock = stock;     // Asigna stock al campo correspondiente
    }

    // Método para mostrar la información de un producto
    public void mostrarInfo() {
        // Imprime id, nombre, precio y stock en consola
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

    // Constructor de Carrito (inicializa la lista vacía de productos)
    public Carrito(int id) {
        this.id = id;                       // Asigna el id del carrito
        this.productos = new ArrayList<>(); // Inicializa la lista como vacía
    }

    // Método para agregar un producto al carrito
    public void agregarProducto(Producto p) {
        productos.add(p); // Agrega el producto a la lista
    }

    // Método para mostrar los productos dentro del carrito
    public void mostrarInfo() {
        System.out.println("Carrito ID: " + id); // Muestra el id del carrito
        // Recorre la lista de productos e imprime cada uno
        for (Producto p : productos) {
            p.mostrarInfo(); // Llama al método mostrarInfo de cada producto
        }
    }

    // Método para calcular el total del carrito
    public float calcularTotal() {
        float total = 0; // Inicializa el acumulador
        // Recorre todos los productos del carrito
        for (Producto p : productos) {
            total += p.precio; // Suma el precio de cada producto
        }
        return total; // Devuelve la suma total
    }
}

// ---------------- Clase Pedido ----------------
class Pedido {
    int id;           // Identificador único del pedido
    Cliente cliente;  // Cliente que realiza el pedido
    Carrito carrito;  // Carrito asociado al pedido
    String estado;    // Estado del pedido (Ej: Pendiente, Pagado, Enviado)

    // Constructor de Pedido: inicializa los atributos
    public Pedido(int id, Cliente cliente, Carrito carrito, String estado) {
        this.id = id;             // Asigna el id
        this.cliente = cliente;   // Asigna el cliente
        this.carrito = carrito;   // Asigna el carrito
        this.estado = estado;     // Asigna el estado
    }

    // Método para mostrar toda la información del pedido
    public void mostrarInfo() {
        // Imprime id, estado y el total calculado del carrito
        System.out.println("Pedido ID: " + id +
                           " | Estado: " + estado +
                           " | Total: $" + carrito.calcularTotal());

        cliente.mostrarInfo(); // Muestra información del cliente (polimorfismo)
        carrito.mostrarInfo(); // Muestra el contenido del carrito
    }
}

// ---------------- Clase Main (ejecución principal) ----------------
public class Main {
    public static void main(String[] args) {
        // Crear productos con sus datos
        Producto p1 = new Producto(1, "Laptop", 2500.99f, 10); // Producto 1
        Producto p2 = new Producto(2, "Mouse", 25.50f, 50);    // Producto 2

        // Crear un carrito con id 101
        Carrito carrito = new Carrito(101);
        carrito.agregarProducto(p1); // Agregar producto 1
        carrito.agregarProducto(p2); // Agregar producto 2

        // Crear cliente con sus datos
        Cliente cliente = new Cliente(1, "Andrés", "andres@mail.com");

        // Crear un pedido con cliente y carrito
        Pedido pedido = new Pedido(5001, cliente, carrito, "Pendiente");

        // Mostrar toda la información del pedido (pedido + cliente + carrito)
        pedido.mostrarInfo();
    }
}

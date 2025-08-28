class Producto:  # Define la clase base para productos físicos.
    def __init__(self, id, nombre, precio, stock):  # Constructor con identificador, nombre, precio y stock.
        self.id = id               # Asigna el identificador del producto.
        self.nombre = nombre       # Asigna el nombre del producto.               #SELF es una referencia al objeto actual de la clase.
        self.precio = precio       # Asigna el precio del producto.
        self.stock = stock         # Asigna la cantidad disponible en inventario.

    def actualizar_stock(self, cantidad):  # Método para decrementar el stock según una cantidad vendida.
        self.stock -= cantidad             # Resta la cantidad al stock actual.

    def mostrar_info(self):  # Devuelve una cadena con la información del producto.
        return f"{self.nombre} - Precio: ${self.precio}, Stock: {self.stock}"  # Formatea y retorna la info.


class ProductoDigital(Producto):  # Clase para productos digitales que hereda de Producto.
    def __init__(self, id, nombre, precio, formato):  # Constructor específico para productos digitales.
        super().__init__(id, nombre, precio, stock=None)  # Llama al constructor padre, sin stock (no aplica).
        self.formato = formato     # Guarda el formato del archivo (por ejemplo, PDF, MP3, etc.).

    def mostrar_info(self):  # Sobrescribe la presentación de info para productos digitales.
        return f"{self.nombre} (Digital - {self.formato}) - Precio: ${self.precio}"  # Muestra sin stock.


class Cliente:  # Representa a un cliente del sistema.
    def __init__(self, id, nombre, correo):  # Constructor con datos básicos del cliente.
        self.id = id               # Identificador único del cliente.
        self.nombre = nombre       # Nombre del cliente.
        self.correo = correo       # Correo electrónico del cliente.

    def registrarse(self):  # Simula el registro del cliente, devolviendo un mensaje.
        return f"Cliente {self.nombre} registrado con correo {self.correo}"  # Mensaje de confirmación.

    def realizar_pedido(self, carrito):  # Crea un pedido a partir del carrito asociado.
        pedido = Pedido(9001, self, carrito, "Pendiente")  # Instancia un Pedido con estado inicial.
        return pedido              # Retorna el pedido creado.


class Carrito:  # Modelo de un carrito de compras.
    def __init__(self, id):  # Constructor del carrito.
        self.id = id                  # Identificador del carrito.
        self.productos = []           # Lista de tuplas (producto, cantidad) agregados al carrito.

    def agregar_producto(self, producto, cantidad=1):  # Agrega un producto con una cantidad al carrito.
        self.productos.append((producto, cantidad))    # Añade la tupla (producto, cantidad) a la lista.
        if isinstance(producto, Producto) and not isinstance(producto, ProductoDigital):  # Si es físico...
            producto.actualizar_stock(cantidad)        # ...disminuye el stock del producto físico.

    def calcular_total(self):  # Calcula el total a pagar por los productos del carrito.
        total = 0              # Inicializa el acumulador del total.
        for producto, cantidad in self.productos:  # Recorre cada tupla (producto, cantidad).
            total += producto.precio * cantidad    # Suma precio * cantidad al total.
        return total            # Retorna el total calculado.


class Pedido:  # Representa un pedido realizado por un cliente.
    def __init__(self, id, cliente, carrito, estado):  # Constructor del pedido.
        self.id = id                 # Identificador del pedido.
        self.cliente = cliente       # Referencia al cliente que realiza el pedido.
        self.carrito = carrito       # Referencia al carrito asociado al pedido.
        self.estado = estado         # Estado actual del pedido (p.ej., Pendiente, Procesado).

    def procesar_pedido(self):  # Cambia el estado del pedido a Procesado y devuelve un mensaje.
        self.estado = "Procesado"  # Actualiza el estado interno del pedido.
        return f"Pedido {self.id} procesado para cliente {self.cliente.nombre}."  # Mensaje de confirmación.

    def cambiar_estado(self, nuevo_estado):  # Permite actualizar el estado del pedido arbitrariamente.
        self.estado = nuevo_estado           # Asigna el nuevo estado.


if __name__ == "__main__":  # Punto de entrada: se ejecuta solo si el archivo se corre directamente.
    p1 = Producto(101, "Laptop Lenovo", 2500.0, 5)       # Crea un producto físico con stock 5.
    p2 = Producto(102, "Mouse Logitech", 50.0, 20)       # Crea otro producto físico con stock 20.
    p3 = ProductoDigital(103, "Ebook Python", 15.0, "PDF")  # Crea un producto digital en formato PDF.

    cliente1 = Cliente(1, "Ana Torres", "ana@email.com")  # Instancia un cliente con sus datos.
    carrito1 = Carrito(501)                               # Crea un carrito de compras.

    carrito1.agregar_producto(p1, 1)  # Agrega 1 unidad de la laptop al carrito (stock de p1 baja a 4).
    carrito1.agregar_producto(p2, 2)  # Agrega 2 unidades del mouse (stock de p2 baja a 18).
    carrito1.agregar_producto(p3, 1)  # Agrega 1 ebook (no afecta stock por ser digital).

    pedido1 = cliente1.realizar_pedido(carrito1)  # El cliente genera un pedido con el carrito actual.

    print(cliente1.registrarse())  # Imprime el mensaje de registro del cliente.
    print("\nProductos en el carrito:")  # Título para listar productos del carrito.
    for producto, _ in carrito1.productos:  # Itera por los productos en el carrito (ignora cantidad aquí).
        print("-", producto.mostrar_info())  # Imprime la información de cada producto.

    print(f"\nTotal del carrito: {carrito1.calcular_total()}")  # Muestra el total a pagar.

    print(pedido1.procesar_pedido())       # Procesa el pedido e imprime el mensaje de confirmación.
    print("Estado final del pedido:", pedido1.estado)  # Muestra el estado final del pedido.

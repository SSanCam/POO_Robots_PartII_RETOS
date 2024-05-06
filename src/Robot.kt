import kotlin.reflect.KFunction2

/**
 * Clase Robot
 *
 * @property nombre String El nombre del robot
 * @property posX Int Da la posición en el eje X.
 * @property posY Int Da la posición en el eje Y.
 * @property direccion String Indica en la dirección hacia la que está direccionado el robot.
 * @property modificarDireccion Modifica la dirección en la que se mueven los robots.
 */
class Robot(
    nombre: String,
    private var posX: Int = 0,
    private var posY: Int = 0,
    direccion: DireccionesPosibles,
    modificarDireccionesPosibles: KFunction2<DireccionesPosibles, Int, DireccionesPosibles>
) {

    // GETTER - SETTERS
    var nombre: String = nombre
        set(value) {
            require(nombre.isNotBlank()) { "Éste campo no puede estar vacío." }
            field = value
        }

    private var direccion: String = DireccionesPosibles.POSITIVEY.toString()
        set(value) {
            require(direccion in DireccionesPosibles.entries.map { it.name }) { "La dirección $value no es válida." }
            field = value
        }

    // Métodos

    /**
     * Mover
     *
     * Este método se encarga de mover el robot en la dirección establecida.
     * No retorna ningún valor, ya que los cambios quedan almacenados en las propiedades del objeto.
     *
     * @param movimiento Un array de elementos enteros que representa los movimientos en las direcciones X e Y.
     *
     */
    fun mover(movimiento: IntArray) {
        for (paso in movimiento) {
            when (direccion) {
                DireccionesPosibles.POSITIVEX.toString() -> posX += paso
                DireccionesPosibles.NEGATIVEX.toString() -> posX -= paso
                DireccionesPosibles.POSITIVEY.toString() -> posY += paso
                DireccionesPosibles.NEGATIVEY.toString() -> posY -= paso
            }
            // Cambia la dirección trás el movimiento.
            direccion = modificarDireccion(direccion).toString()
        }

    }

    /**
     * Obtener dirección
     *
     * Este método devuelve la dirección actual hacia la que está orientado el robot.
     * @return La dirección actual del robot.
     */
    private fun obtenerDireccion(): String {
        return direccion
    }

    /**
     * Obtener posición
     *
     * Este método devuelve la posición del robot.
     * @return La posición actual del robot en formato (posX, posY).
     */
    private fun obtenerPosicion(): String {
        return ("$posX-$posY")
    }


    override fun toString(): String {
        return "El robot $nombre está en la posición ${obtenerPosicion()}, ${obtenerDireccion()}"
    }

    /**
     * Clase que llama a la función ya creada [cambiarDireccion]
     */
    private fun modificarDireccion(direccionActual: String): DireccionesPosibles {
        return modificarDireccion(direccionActual)
    }

    /*
    // CAMBIO DIRECCION DE POO ROBOTS PART I.
    /**
     * Cambiar dirección
     * Cambia la dirección del Robot cada vez que realiza un movimiento.
     */
    private fun cambiarDireccion() {

        when (direccion) {
            DireccionesPosibles.POSITIVEY.toString() -> direccion = DireccionesPosibles.NEGATIVEX.toString()
            DireccionesPosibles.POSITIVEX.toString() -> direccion = DireccionesPosibles.POSITIVEY.toString()
            DireccionesPosibles.NEGATIVEX.toString() -> direccion = DireccionesPosibles.NEGATIVEY.toString()
            DireccionesPosibles.NEGATIVEY.toString() -> direccion = DireccionesPosibles.POSITIVEX.toString()
        }
    }
    */
    companion object {
        /**
         * modificar dirección para R2D2
         * @param direccion [DireccionesPosibles] es la posición que tendrá el Robot trás el movimiento.
         * @param posX [Int] Usamos éste parámetro para evitar conflictos en el código, aun que no se haga uso de él.
         * @return Dirección final en la que está orientado el Robot.
         */
        fun modificarDireccionR2D2(direccion: DireccionesPosibles, posX: Int): DireccionesPosibles {
            return if (direccion == DireccionesPosibles.POSITIVEY) DireccionesPosibles.NEGATIVEX else direccion
        }

        /**
         * Modificar dirección para DAW1A
         *
         * @param direccion [DireccionesPosibles] Dirección en la que se encuentra orientado el Robot.
         * @param posX [Int] El lado del eje X hacia el que el Robot se ha quedado orientado.
         * @return La posición final hacia la que el Robot queda orientado tras el movimiento.
         */
        fun modificarDireccionDAW1A(direccion: DireccionesPosibles, posX: Int): DireccionesPosibles {
            // Verificar la posición X y girar la dirección en consecuencia
            return when {
                posX > 0 -> DireccionesPosibles.NEGATIVEY // Girar 180° si posX es positivo
                posX < 0 -> DireccionesPosibles.POSITIVEX // Girar 90° si posX es negativo
                else -> direccion // Mantener la dirección si posX es cero
            }
        }

        /**
         * Modificar direección para DAW1B
         *
         * @param direccionActual [DireccionesPosibles] La dirección en la que estaba orientado el Robot cuando acaba el movimiento.
         * @param posY [Int] Indica hacia qué lado del eje Y se encuentra orientado el Robot.
         * @return Devuelve la dirección hacia la que se encuentra orientado el Robot una vez realizado el movimiento.
         */
        fun modificarDireccionDAW1B(direccionActual: DireccionesPosibles, posY: Int): DireccionesPosibles {
            // Verificar la posición Y y girar la dirección en consecuencia
            return when {
                posY > 0 -> DireccionesPosibles.NEGATIVEX // Girar -90° si posY es positivo
                posY < 0 -> DireccionesPosibles.POSITIVEX // Girar 270° si posY es negativo
                else -> direccionActual // Mantener la dirección si posY es cero
            }
        }

        /**
         * Modificar dirección para DAM1
         * Su posición final es aleatoria, pero se usa [posY] para evitar conflictos en el código.
         *  @param direccionActual [DireccionesPosibles] Es la dirección en la que se orientará el robot después del movimiento.
         */
        fun modificarDireccionDAM1(direccionActual: DireccionesPosibles, posY: Int): DireccionesPosibles {
            // Generar una nueva dirección aleatoria diferente a la actual
            var nuevaDireccion: DireccionesPosibles
            do {
                nuevaDireccion = DireccionesPosibles.entries.toTypedArray().random()
            } while (nuevaDireccion == direccionActual)
            return nuevaDireccion
        }
    }

}
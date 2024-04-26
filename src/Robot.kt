/**
 * Clase Robot
 *
 * @property nombre String El nombre del robot
 * @property posX Int Da la posición en el eje X.
 * @property posY Int Da la posición en el eje Y.
 * @property direccion String Indica en la dirección hacia la que está direccionado el robot.
 * @property modificarDireccion Modifica la dirección en la que se mueven los robots según su tipo.
 */
class Robot(
    nombre: String,
    posX: Int,
    posY: Int,
    direccion: DireccionesPosibles,
    modificarDireccion: (DireccionesPosibles) -> DireccionesPosibles
) {

    // GETTER - SETTERS
    private var nombre: String = nombre
        set(value) {
            require(nombre.isNotBlank()) { "Éste campo no puede estar vacío." }
            field = value
        }
    private var posX: Int = 0
        set(value) {
            field = value
        }
    private var posY: Int = 0
        set(value) {
            field = value
        }
    private var direccion: String = DireccionesPosibles.POSITIVEY.toString()
        set(value) {
            require(direccion in DireccionesPosibles.entries.map { it.name }) { "La dirección $value no es válida." }
            field = value
        }


    /**
     * Clase que llama a la función ya creada [cambiarDireccion]
     */
    private fun modificarDireccion() {
        obtenerDireccion()
    }
    //METODOS DE CLASE:
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
            cambiarDireccion()
        }

    }

    /**
     * Obtener dirección
     *
     * Este método devuelve la dirección actual hacia la que está orientado el robot.
     * @return La dirección actual del robot.
     */
    private fun obtenerDireccion(): String {
        val direccionFormat = direccion
        return direccionFormat
    }

    /**
     * Obtener posición
     *
     * Este método devuelve la posición del robot.
     * @return La posición actual del robot en formato (posX, posY).
     */
    private fun obtenerPosicion(): String {
        return "($posX, $posY)"
    }

    override fun toString(): String {
        return "El robot $nombre está en la posición ${obtenerPosicion()}, ${obtenerDireccion()}."
    }

}
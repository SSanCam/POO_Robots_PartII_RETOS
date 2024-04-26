fun main() {

    val robot1 = Robot("R2D2", 0, 0, DireccionesPosibles.POSITIVEY, modificarDireccion = DireccionesPosibles(direccion))
    val robot2 = Robot("DAW1A", (-5..5).random(), 0, DireccionesPosibles.POSITIVEX, modificarDireccion = {})
    val robot3 = Robot("DAW1B", 0, (-10..10).random(), DireccionesPosibles.entries.random(), modificarDireccion = {})
    val robot4 = Robot("DAM1", (-5..5).random(), (-5..5).random(), DireccionesPosibles.entries.random(), modificarDireccion = {})

    val robots: List<Robot> = listOf(robot1, robot2, robot3, robot4)

}
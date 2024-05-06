fun main() {
    val robot1 = Robot("R2D2", 0, 0, DireccionesPosibles.POSITIVEY, Robot::modificarDireccionR2D2)
    val robot2 = Robot("DAW1A", (-5..5).random(), 0, DireccionesPosibles.POSITIVEX, Robot::modificarDireccionDAW1A)
    val robot3 = Robot("DAW1B", 0, (-10..10).random(), DireccionesPosibles.entries.toTypedArray().random(), Robot::modificarDireccionDAW1B)
    val robot4 = Robot("DAM1", (-5..5).random(), (-5..5).random(), DireccionesPosibles.entries.toTypedArray().random(), Robot::modificarDireccionDAM1)

    val robots: List<Robot> = listOf(robot1, robot2, robot3, robot4)

    println("Ingrese el número de movimientos para los robots:")
    val movimientos = readLine()?.toIntOrNull() ?: 0

    robots.forEach { robot ->
        println("Moviendo al robot ${robot.nombre}...")
        val movimientosRobot = IntArray(movimientos) { (-20..20).random() }
        robot.mover(movimientosRobot)
        println(robot)
    }
}

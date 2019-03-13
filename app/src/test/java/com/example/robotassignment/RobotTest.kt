package com.example.robotassignment

import com.example.robotassignment.DataClass.Position
import com.example.robotassignment.DataClass.Robot
import com.example.robotassignment.DataClass.Room
import org.junit.Test

import org.junit.Assert.*

class RobotTest {
    val startPosition = Position(0, 0, "E")
    val roomSize = Room(5, 5)


    @Test
    fun route() {
        val robot = Robot(startPosition, roomSize)
        val testRoute = "RFLFFLRF"
        val expected = Position(1, 3, "e")
        robot.route(testRoute)
        assertEquals(expected,robot.position)
    }

    @Test
    fun validateInputStringPositive() {
        val robot = Robot(startPosition, roomSize)
        val route = "LFFRFRFRFF"
        val output = robot.validateInputString(route)
        assertEquals(true,output)

    }

    @Test
    fun validateInputStringNegative() {
        //negative
        val robot = Robot(startPosition, roomSize)
        val route = "LFFRBLABLABLAXX"
        val output = robot.validateInputString(route)
        assertEquals(false,output)

    }

    @Test
    fun turnDirection() {
        // Turn right
        val robot = Robot(startPosition, roomSize)
        robot.turnDirection("r")
        assertEquals("s", robot.position.direction)
        // Turn left
        robot.turnDirection("l")
        assertEquals("e", robot.position.direction)

    }

    @Test
    fun move() {
        // Move with direction E
        val robot = Robot(startPosition, roomSize)
        robot.move()
        val expectedE = Position(0, 1, "E")
        assertEquals(expectedE,robot.position)

        // Move with direction S
        robot.turnDirection("r")
        robot.move()
        val expectedS = Position(1, 1, "s")

        // Move with direction W
        assertEquals(expectedS, robot.position)
        robot.turnDirection("r")
        robot.move()
        val expectedW = Position(1, 0, "w")
        assertEquals(expectedW, robot.position)

        // Move with direction N
        robot.turnDirection("r")
        robot.move()
        val expectedN = Position(0, 0, "n")
        assertEquals(expectedN,robot.position)

    }
}
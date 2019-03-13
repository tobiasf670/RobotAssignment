package com.example.robotassignment.DataClass

class Robot(var position : Position, var roomSize: Room) {




    fun route(commands: String) : Boolean {
        if (validateInputString(commands)) {
            for (i in 0 until commands.length) {
                when (commands.get(i).toLowerCase().toString()) {
                    "l" -> this.turnDirection("l")
                    "r" -> this.turnDirection("r")
                    "f" -> this.move()
                    else -> println("Wrong input")
                }
            }
            return true
        } else {
            return false
        }
    }

    fun validateInputString(commands: String) : Boolean {
        val length = commands.length


        for (i in 0 until length) {

            when (commands.get(i).toLowerCase().toString()) {
                "l" -> println("Letter L is ok")
                "r" -> println("Letter R is ok")
                "f" -> println("Letter F is ok")
                else -> return false
            }
        }

        return true
    }

    fun turnDirection( direction : String) {


        when (this.position.direction.toLowerCase()) {
            "n" ->
                if (direction.equals("l", ignoreCase = true)) {
                // west
                    this.position.direction = "w"
                 } else {
                    // east
                    this.position.direction = "e"
                }
            "s" ->
                if (direction.equals("l", ignoreCase = true)) {
                    // east
                    this.position.direction = "e"
                } else {
                    // west
                    this.position.direction = "w"
                }
            "e" ->
                if (direction.equals("l", ignoreCase = true)) {
                    // north
                    this.position.direction = "n"
                } else {
                    // south
                    this.position.direction = "s"
                }
            "w" ->
                if (direction.equals("l", ignoreCase = true)) {
                    // south
                    this.position.direction = "s"
                } else {
                    // North
                    this.position.direction = "n"
                }
            else -> print("Wrong input")
        }
    }

    fun move(){
        when (this.position.direction.toLowerCase()) {
            "n" -> this.position.x -= 1
            "s" -> this.position.x += 1
            "e" -> this.position.y += 1
            "w" -> this.position.y -= 1
            else -> print("Wrong input")
        }
    }
}


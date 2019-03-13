package com.example.robotassignment

import android.util.Log

class Robot(var postion : Position, var roomSize: RoomController) {




    fun route(commands: String) {
        if (validateInputString(commands)) {
            for (i in 0 until commands.length) {
                when (commands.get(i).toLowerCase().toString()) {
                    "l" -> this.turnDirection("l")
                    "r" -> this.turnDirection("r")
                    "f" -> this.move()
                    else -> println("Wrong input")
                }
            }
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

    fun validateRoute() {


    }

    fun turnDirection( direction : String) {


        when (this.postion.direction.toLowerCase()) {
            "n" ->
                if (direction.equals("l", ignoreCase = true)) {
                // west
                    this.postion.direction = "w"
                 } else {
                    // east
                    this.postion.direction = "e"
                }
            "s" ->
                if (direction.equals("l", ignoreCase = true)) {
                    // east
                    this.postion.direction = "e"
                } else {
                    // west
                    this.postion.direction = "w"
                }
            "e" ->
                if (direction.equals("l", ignoreCase = true)) {
                    // north
                    this.postion.direction = "n"
                } else {
                    // south
                    this.postion.direction = "s"
                }
            "w" ->
                if (direction.equals("l", ignoreCase = true)) {
                    // south
                    this.postion.direction = "s"
                } else {
                    // North
                    this.postion.direction = "n"
                }
            else -> print("Wrong input")
        }
    }

    fun move(){
        Log.d("Before Move", "" + this.postion)
        when (this.postion.direction.toLowerCase()) {
            "n" -> this.postion.x -= 1
            "s" -> this.postion.x += 1
            "e" -> this.postion.y += 1
            "w" -> this.postion.y -= 1
            else -> print("Wrong input")
        }

        Log.d("After Move", "" + this.postion)
    }
}


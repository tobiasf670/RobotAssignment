package com.example.robotassignment.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.robotassignment.DataClass.Position
import com.example.robotassignment.R
import com.example.robotassignment.DataClass.Room
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    var startPostion: Position? = null
    var roomSize: Room? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        // Added a clicklistener, and sent the data to new activity
        button_next.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            var positionString = editText_postion.text.toString().replace("\\s+", " ")

            if (positionString.length > 2) {

                var startX = positionString.get(1).toString().toInt()
                var startY = positionString.get(0).toString().toInt()
                var startCompas = positionString.get(2).toString()
                this.startPostion = Position(startX, startY, startCompas)
            }


            var x = editText_row.text.toString().toInt()
            var y = editText_colum.text.toString().toInt()
            this.roomSize = Room(x, y)
            // To pass any data to next activity
            intent.putExtra("startPosition", this.startPostion)
            intent.putExtra("roomSize", this.roomSize)
            // start your next activity
            startActivity(intent)
        }
    }
}

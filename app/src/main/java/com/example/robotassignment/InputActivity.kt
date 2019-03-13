package com.example.robotassignment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    var startPostion : Position? = null
    var roomSize : RoomController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)


        button_next.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

           var postionString = editText_postion.text.toString().replace("\\s+"," ")

            if (postionString.length > 2) {

                var startX = postionString.get(1).toString().toInt()
                var startY = postionString.get(0).toString().toInt()
               var startCompas = postionString.get(2).toString()
                Log.d("TOBIASFIRSTX", "" +startX)
                Log.d("TOBIASFIRSTY", "" +startY)

               this.startPostion = Position(startX,startY,startCompas)
            }


            var x = editText_row.text.toString().toInt()
            var y = editText_colum.text.toString().toInt()
            this.roomSize = RoomController(x,y)
            // To pass any data to next activity
            intent.putExtra("startPostion", this.startPostion)
            intent.putExtra("roomSize", this.roomSize)
            // start your next activity
            startActivity(intent)
        }
    }
}

package com.example.robotassignment

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //val ROWS = 5
    //val COLUMNS = 7
    var startPostion : Position? = null
    val tableLayout by lazy { TableLayout(this) }

    var robot: Robot? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.startPostion = getIntent().getExtras().getSerializable("startPostion") as? Position
        val roomSize = getIntent().getExtras().getSerializable("roomSize") as? RoomController

        this.robot = roomSize?.let { this.startPostion?.let { it1 -> Robot(it1, it) } }



        val lp = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tableLayout.apply {
            layoutParams = lp
            isShrinkAllColumns = true
        }

        var roomX = 0
        roomSize?.row?.let {
            roomX = it
        }
        var roomY = 0
        roomSize?.col?.let {
            roomY = it
        }

        createTable(roomX, roomY)

        val buttonRow = tableLayout.getChildAt(this.startPostion!!.x) as TableRow
        buttonRow.getChildAt(this.startPostion!!.y).setBackgroundColor(Color.GREEN)

        button_move.setOnClickListener {
           val route = editText_commands.text.toString()
            this.robot?.route(route)
            Log.d("FINAL POSTION", "" + this.robot?.postion)
            editText_commands.clearFocus()
            val buttonRowEnd = tableLayout.getChildAt(this.robot!!.postion.x) as TableRow
            val button = buttonRowEnd.getChildAt(this.robot!!.postion.y) as Button
            button.setBackgroundColor(Color.RED)
            val endX =  this.robot!!.postion.x.toString()
            val endY = this.robot!!.postion.y.toString()
            val endDirection = this.robot!!.postion.direction
            button.setText("R " +endY +" "+ endX + " " + endDirection)
            editText_commands.hideKeyboard()
            button_move.isClickable = false


        }
    }

    fun createTable(rows: Int, cols: Int) {

        for (i in 0 until rows
        ) {

            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            for (j in 0 until cols) {


                val button = Button(this)
                button.apply {
                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)

                    if(i == startPostion!!.x && j == startPostion!!.y) {
                        text = "R " + startPostion!!.y + " "  + startPostion!!.x + " " + startPostion!!.direction
                    } else {

                        text = "R $j C $i"
                    }
                }
                row.addView(button)
            }
            tableLayout.addView(row)
        }
        constraintLayout.addView(tableLayout)

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

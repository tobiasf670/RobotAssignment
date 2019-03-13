package com.example.robotassignment.Activity

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import com.example.robotassignment.DataClass.Position
import com.example.robotassignment.R
import com.example.robotassignment.DataClass.Robot
import com.example.robotassignment.DataClass.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var startPosition: Position? = null
    val tableLayout by lazy { TableLayout(this) }

    var robot: Robot? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Receive data from intent / from another activity
        this.startPosition = getIntent().getExtras().getSerializable("startPosition") as? Position
        val roomSize = getIntent().getExtras().getSerializable("roomSize") as? Room
        // Create robot object
        this.robot = roomSize?.let {
            this.startPosition?.let { it1 ->
                Robot(
                    it1,
                    it
                )
            }
        }

        // Create the tablelaout with buttons,
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

        val buttonRow = tableLayout.getChildAt(this.startPosition!!.x) as TableRow
        buttonRow.getChildAt(this.startPosition!!.y).setBackgroundColor(Color.GREEN)

        button_move.setOnClickListener {
            val route = editText_commands.text.toString()
            if (this.robot?.route(route) == true) {
                editText_commands.clearFocus()
                val buttonRowEnd = tableLayout.getChildAt(this.robot!!.position.x) as TableRow
                val button = buttonRowEnd.getChildAt(this.robot!!.position.y) as Button
                button.setBackgroundColor(Color.RED)
                val endX = this.robot!!.position.x.toString()
                val endY = this.robot!!.position.y.toString()
                val endDirection = this.robot!!.position.direction
                button.setText("R " + endY + " " + endX + " " + endDirection)
                editText_commands.hideKeyboard()
                button_move.isClickable = false
            } else {
                editText_commands.setText("Wrong Input")
            }


        }
    }

    fun createTable(rows: Int, cols: Int) {

        for (i in 0 until rows
        ) {

            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            for (j in 0 until cols) {


                val button = Button(this)
                button.apply {
                    layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )

                    if (i == startPosition!!.x && j == startPosition!!.y) {
                        text = "R " + startPosition!!.y + " " + startPosition!!.x + " " + startPosition!!.direction
                    } else {

                        text = "R $i C $j"
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

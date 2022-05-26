package com.example.proyecto_3_tetris_db.tetris

import android.content.Intent
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_3_tetris_db.dal.conn.AppDatabase
import com.example.proyecto_3_tetris_db.dal.models.Point
import com.example.proyecto_3_tetris_db.tetris.shapes.Shape
import com.example.proyecto_3_tetris_db.tetris.shapes.ShapeFactory
import com.example.proyecto_3_tetris_db.ui.GameOverActivity
import kotlin.random.Random

class TGame(
     var parentActivity: AppCompatActivity,
     var grid: GridLayout
    ) {
    var tBoard:TBoard
    lateinit var currentShape:Shape

    var running:Boolean =false

    var arr_shapes:MutableList<Shape> = ArrayList()
    var arr_points:MutableList<Point> = ArrayList()
    var time:Long = 1000
    var frameIndex:Int = 0
    var score:Int = 0

    init {
        tBoard= TBoard(parentActivity,grid,15)
        tBoard.clearScreen()

        var shape = ShapeFactory.createFromDB(parentActivity,tBoard)
        if(shape == null){
            //Inicia nueva partida
            createRadomShape()
        }else{
            currentShape = shape
        }
        loadDataDB()
        render()
        start()
    }

    fun loadDataDB(){
       var list =  AppDatabase.getInstance(parentActivity).pointDao().selectAll() as ArrayList<Point>
        arr_points = list
        System.out.println("Load data succes")
    }
    fun createRadomShape(){
        var ir = Random.nextInt(0, ShapeFactory.types.size);
        currentShape= ShapeFactory.create(ShapeFactory.types[ir], tBoard)
    }
    fun stop(){
        running = false;
    }
    fun start() {
        running = true;
        Thread {
            while (running) {
                Thread.sleep(time)
                parentActivity.runOnUiThread {
                    moveDown()
                    render()
                }
            }
        }.start()
    }

    fun render(){
        if(!running) return
        frameIndex++
        tBoard.clearScreen()
        currentShape.draw()
        currentShape.toDto().updateDb(parentActivity)
        validateRowComplete()

        for(poin in arr_points){
            if(poin.y<=0){
                showGameOver()
            }
            poin.draw(tBoard)
        }

    }

    fun end(){
        stop()
        AppDatabase.getInstance(parentActivity).shapeDao().delete(AppDatabase.getInstance(parentActivity).shapeDao().selectById("current"))
        AppDatabase.getInstance(parentActivity).pointDao().deleteAll()
        parentActivity.finish()

    }

    fun showGameOver() {
        if(!running) return
        parentActivity.startActivity(Intent(parentActivity, GameOverActivity::class.java).putExtra("points", score))
        end()
    }

    fun validateRowComplete() {
        var mt = arrayOfNulls<Int>(tBoard.rowCount)
        for (i in 0 until  tBoard.rowCount){
            mt[i] = 0
        }
        var arr_to_remove:MutableList<Point> = ArrayList()
        for(poin in arr_points){
            mt[poin.y] = mt[poin.y]!! + 1
        }
        for(i in 0 until mt.size){
            if(mt[i] as Int >= tBoard.columnCount){
                score+=1
                for(poin in arr_points){
                    if(poin.y == i){
                        arr_to_remove.add(poin)
                    }
                }
            }
        }
       arr_points.removeAll(arr_to_remove)
        for (rm in arr_to_remove){
            rm.deleteDb(parentActivity)
            for(pt in arr_points){
                if(pt.y< rm.y && pt.x == rm.x){
                    System.out.println("asd")
                    pt.y = pt.y+1;
                    pt.updateDb(parentActivity)
                }
            }
        }

    }

    fun moveDown(){
        if(!currentShape.moveDown(arr_points)){
            //Cunado el shape llega al final
            arr_shapes.add(currentShape);
            for(pt in currentShape.getPoints()){
                arr_points.add(pt);
              pt.updateDb(parentActivity)
            }
            createRadomShape()
        }
        render()
    }
    fun moveLeft(){
        currentShape.moveLeft(arr_points)
        render()

    }
    fun moveRight(){
        currentShape.moveRight(arr_points)
        render()
    }
    fun rotate(){
        currentShape.rotate()
        render()
    }

}
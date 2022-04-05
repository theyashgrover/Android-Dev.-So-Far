package com.example.joddboard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class DrawingView(context : Context , attrs : AttributeSet) : View(context , attrs){
     //Variables (sort of an object) of different classes.
     private var mDrawPath : CustomPath? = null
     private var mCanvasBitmap : Bitmap? = null
     private var mDrawPaint : Paint? = null
     private var mCanvasPaint : Paint? = null
     private var mBrushSize : Float = 0.toFloat()
     private var color = Color.BLACK
     private var canvas : Canvas? = null
    //to persist what's on screen
    private val mPaths = ArrayList<CustomPath>() //what we are doing is saving all the mDrawPath(s) in this mPaths variable
    private val mUndoPaths = ArrayList<CustomPath>()

    //initialization
    init{
        setUpDrawing() // Calling the function as initialization
    }

    //for undo and stuff
    fun onClickUndo(){
        if(mPaths.size > 0){
            mUndoPaths.add(mPaths.removeAt(mPaths.size -1))
            invalidate() //calls undraw() {to redraw}
        }
    }

    //Setting the board for drawing ..

    private fun setUpDrawing(){
        mDrawPaint =  Paint()
        mDrawPath = CustomPath(color,mBrushSize)
        mDrawPaint!!.color = color
        mDrawPaint!!.style = Paint.Style.STROKE
        mDrawPaint!!.strokeJoin = Paint.Join.ROUND
        mDrawPaint!!.strokeCap = Paint.Cap.ROUND
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
        //mBrushSize = 20.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasBitmap = Bitmap.createBitmap(w , h , Bitmap.Config.ARGB_8888)
        canvas = Canvas(mCanvasBitmap!!)
    }

    //Changes Canvas to Canvas? if fails
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mCanvasBitmap!!, 0f, 0f, mCanvasPaint)

        //adding all the drawing already done with the help of loops
        for (path in mPaths){
            mDrawPaint!!.strokeWidth = path.brushThickness
            mDrawPaint!!.color = path.color
            canvas.drawPath(path, mDrawPaint!!)
        } //in this for loop we have created a new variable path which stores the brushthickness and color and saves the path drawn with both of them in use..

        //the below if statement ensures that annotation can be done only if nothing is already drawn..
        if (!mDrawPath!!.isEmpty) {
            mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness
            mDrawPaint!!.color = mDrawPath!!.color
            canvas.drawPath(mDrawPath!!, mDrawPaint!!)
        }
    }

    //the function below defines what should happen when one touches the screen , we have listed the three main important event actions below
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y

        when(event?.action){
            //three most important events that happen when one touches the screen :

            //when one's finger is down on touch screen
                MotionEvent.ACTION_DOWN ->{
                 mDrawPath!!.color = color
                 mDrawPath!!.brushThickness = mBrushSize

                 mDrawPath!!.reset()
                 //these if statements are for null safety
                 if (touchX != null) {
                     if (touchY != null) {
                         mDrawPath!!.moveTo(touchX , touchY)
                     }
                 }
             }
            //when one's finger is moving on touch screen
            MotionEvent.ACTION_MOVE ->{
                //these if statements are for null safety
                if (touchX != null) {
                    if (touchY != null) {
                        mDrawPath!!.lineTo(touchX , touchY)
                    }
                }
            }
            //when one's finger is moved away(up) from touch screen
            MotionEvent.ACTION_UP ->{
                mPaths.add(mDrawPath!!)
                mDrawPath = CustomPath(color , mBrushSize)
            }
            else -> return false
        }
        invalidate() //a method which is important to call for the actions above to work (search about this)
        return true
    }

    //function for setting the brush size :
    fun setSizeForBrush(newSize : Float){
        mBrushSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , newSize , resources.displayMetrics) //read about unit , matrix and value parameters of applyDimension method (use ctrl + left mouse button) to visit the javaclass
        mDrawPaint!!.strokeWidth = mBrushSize
    }

    fun setColor(newColor: String){
        color = Color.parseColor(newColor)
        mDrawPaint!!.color = color
    }

    internal inner class CustomPath(var color:Int , var brushThickness:Float) : Path() {

    }


}
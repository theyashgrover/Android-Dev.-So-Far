package com.example.justmemeit
//importing modules .. 
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.cronet.CronetHttpStack
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    var currentImageURL: String?=null
//onCreate Method starts here :
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme() //calling the loadmeme() function as soon as app starts (i.e. app modules are created)
    }
//loadMeme() function starts here..
    private fun loadMeme(){
        progressBar.visibility=View.VISIBLE
        // Instantiate the RequestQueue 
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url , null,
            Response.Listener { response ->
            currentImageURL = response.getString("url")
            Glide.with(this).load(currentImageURL).listener(object:RequestListener<Drawable>{

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                   progressBar.visibility=View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility=View.GONE
                    return false
                }
            }).into(memeImageView)
            },
            Response.ErrorListener {

            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
//creating the function for share button..
    fun shareMeme(view: View) {

    val intent = Intent(Intent.ACTION_SEND) //action send is a pre-existing method and used for the share functionality..
        intent.type = "text/plain" //defining the type of intent is important in impicit intents..
    intent.putExtra(Intent.EXTRA_TEXT ,"Hi there , Checkout this coolmeme I got from Reddit ! $currentImageURL ") //creating a default message for sharing image
        val chooser = Intent.createChooser(intent,"Share using :") //creating the chooser
        startActivity(chooser) //starting the chooser overlay activity
    }
    
//creating the function for next button..    
    fun nextMeme(view: View) {
        loadMeme() //calling loadmeme() function here as we want to load another meme as person clicks on next..

    }
}

package com.example.kotlindemo

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlindemo.adapter.MainActivityAdapter
import com.example.kotlindemo.callbacks.ItemClickListner
import com.example.kotlindemo.common.Config
import com.example.kotlindemo.model.MainModel
import com.example.kotlindemo.model.VideoModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), ItemClickListner {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    var names: MutableList<String> = mutableListOf<String>()
    //    var recView: RecyclerView? = null
    var modelList = mutableListOf<MainModel>()
    var videoModelList = mutableListOf<VideoModel>()
    var progDiaog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addModelData()
        findViews()

        recView.setHasFixedSize(true)
        recView.layoutManager = LinearLayoutManager(this)

        recView.adapter = MainActivityAdapter(
            videoModelList,
            MainActivity@ this,
            { mainModel -> itemClick(mainModel) })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signOutBtn.setOnClickListener {
            mGoogleSignInClient?.signOut()?.addOnCompleteListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        getHomeVideosData("UCOsyDsO5tIt-VZ1iwjdQmew")
    }

    private fun findViews() {
        progDiaog = ProgressDialog(this)
        progDiaog?.setMessage("Loading")
        progDiaog?.setCancelable(true)
        progDiaog?.show()

    }

    fun addModelData() {
        modelList.add(
            MainModel(
                "Sunny",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
        modelList.add(
            MainModel(
                "Karan",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
        modelList.add(
            MainModel(
                "Arun",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
        modelList.add(
            MainModel(
                "Ankesh",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
        modelList.add(
            MainModel(
                "Karan",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
        modelList.add(
            MainModel(
                "Arun",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
        modelList.add(
            MainModel(
                "Ankesh",
                "#170 Mahavir colony",
                "https://images.all-free-download.com/images/graphicthumb/hd_flowers_photo_04_hd_picture_169264.jpg"
            )
        )
    }

    override fun onItemClick(pos: Int) {

    }

    fun itemClick(mainModel: VideoModel) {
//        Toast.makeText(this, "${mainModel.title}", Toast.LENGTH_SHORT).show()
        var intent = Intent(this, YouTubeActivty::class.java)
        var bundle = Bundle()
        bundle.putSerializable("MODEL", mainModel)
        intent.putExtra("BUNDLE", bundle)
        startActivity(intent)
    }

    fun getHomeVideosData(channelId: String) {
        val stringRequest = object : StringRequest(Request.Method.GET,
            "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=" + channelId + "&maxResults=10&order=date&type=video&key=" + Config.DEVELOPER_KEY,
            Response.Listener { response ->
                parseVideoData(response)
//                Toast.makeText(this, response + "", Toast.LENGTH_SHORT).show()
                if (progDiaog?.isShowing!!)
                    progDiaog?.dismiss()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@MainActivity,
                    error.message + " Something went wrong...",
                    Toast.LENGTH_SHORT
                ).show()
                if (progDiaog?.isShowing!!)
                    progDiaog?.dismiss()

            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        stringRequest.retryPolicy = DefaultRetryPolicy(
            30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun parseVideoData(response: String) {
        try {

            val jsonObject = JSONObject(response)
            val items = jsonObject.getJSONArray("items")
            for (i in 0 until items.length()) {
                val itemsJSONObject = items.getJSONObject(i)
                val snippet = itemsJSONObject.getJSONObject("snippet")
                val thumbnails = snippet.getJSONObject("thumbnails")
                val videosModel =
                    VideoModel(
                        itemsJSONObject.getJSONObject("id").getString("videoId"),
                        snippet.getString("publishedAt"),
                        snippet.getString("channelId"),
                        snippet.getString("title"),
                        snippet.getString("description"),
                        thumbnails.getJSONObject("medium").getString("url")
                    )

                videoModelList.add(videosModel)
            }

            recView.adapter = MainActivityAdapter(
                videoModelList,
                MainActivity@ this,
                { mainModel -> itemClick(mainModel) })

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

}

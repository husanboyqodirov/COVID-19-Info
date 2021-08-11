package softromeda.covid19info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewPrecautions.layoutManager = LinearLayoutManager(
                this,
                LinearLayout.HORIZONTAL,
                false
        )
        val precautionsList = ArrayList<Model>()
        precautionsList.add(
                Model(
                        R.drawable.soap,
                        getString(R.string.hand_wash),
                        getString(R.string.hand_wash_detail)
                )
        )
        precautionsList.add(
                Model(
                        R.drawable.hone,
                        getString(R.string.stay_home),
                        getString(R.string.stay_home_detail)
                )
        )
        precautionsList.add(
                Model(
                        R.drawable.distance,
                        getString(R.string.social_distance),
                        getString(R.string.social_distance_detail)
                )
        )
     
        val precautionsAdapter = PrecautionsAdapter(precautionsList)
        recyclerViewPrecautions.adapter = precautionsAdapter

        btnKnowMore.setOnClickListener {
            var intent = Intent(this@MainActivity, KnowMoreActivity::class.java)
            startActivity(intent)
        }

        txtViewPrecautions.setOnClickListener {
            var intent = Intent(this@MainActivity, PrecautionActivity::class.java)
            startActivity(intent)
        }
        getGlobalData()

//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//        var imgUrl = "https://resources.gabriele.ai/coronavirus/$year-$month-$day-01_01-COVID-world-en_generic_COVID_EN_GENERIC_world.png"
//        Picasso.get().load(imgUrl).into(imgGraph)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            trimCache(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun trimCache(context: Context) {
        try {
            val dir: File = context.getCacheDir()
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir)
            }
        } catch (e: java.lang.Exception) {

        }
    }

    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }

        // The directory is now empty so delete it
        return dir!!.delete()
    }

    fun getGlobalData(){
        val url ="https://disease.sh/v3/covid-19/all"

        val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                {
                    var jsonObject = JSONObject(it.toString())
                    txtInfected.text = jsonObject.getString("cases")
                    txtRecovered.text = jsonObject.getString("recovered")
                    txtActive.text = jsonObject.getString("active")
                    txtDeceased.text = jsonObject.getString("deaths")
                    txtInfectedT.text = jsonObject.getString("todayCases")
                    txtRecoveredT.text = jsonObject.getString("todayRecovered")
                    txtDeceasedT.text = jsonObject.getString("todayDeaths")

                },
                {
                    Toast.makeText(this@MainActivity, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
                }
        )
        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        requestQueue.add(stringRequest)
    }

}

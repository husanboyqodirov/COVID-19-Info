package softromeda.covid19info

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_precaution.*

class PrecautionActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precaution)

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val precautionsList = ArrayList<Model>()
        precautionsList.add(Model(R.drawable.soap,getString(R.string.hand_wash),getString(R.string.hand_wash_detail)))
        precautionsList.add(Model(R.drawable.hone,getString(R.string.stay_home),getString(R.string.stay_home_detail)))
        precautionsList.add(Model(R.drawable.distance,getString(R.string.social_distance),getString(R.string.social_distance_detail)))
        precautionsList.add(Model(R.drawable.clean,getString(R.string.clean_object),getString(R.string.clean_object_detail)))
        precautionsList.add(Model(R.drawable.cover,getString(R.string.avoid_touching),getString(R.string.avoid_touching_detail)))

        val precautionsAdapter = PrecautionsAdapter(precautionsList)

        recyclerView.adapter = precautionsAdapter
    }
}

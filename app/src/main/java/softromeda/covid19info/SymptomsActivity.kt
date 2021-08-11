package softromeda.covid19info

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_symptoms.*

class SymptomsActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val symptomsList = ArrayList<Model>()
        symptomsList.add(Model(R.drawable.cough,getString(R.string.dry_cough),""))
        symptomsList.add(Model(R.drawable.fever,getString(R.string.fever),""))
        symptomsList.add(Model(R.drawable.pain,getString(R.string.headache),getString(R.string.headache_detail)))
        symptomsList.add(Model(R.drawable.sore_throat,getString(R.string.sore_throat),getString(R.string.sore_throat_detail)))

        val symptomsAdapter = SymptomsAdapter(symptomsList)

        recyclerView.adapter = symptomsAdapter
    }
}

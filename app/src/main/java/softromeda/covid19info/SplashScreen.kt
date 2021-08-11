package softromeda.covid19info

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*

class SplashScreen : AppCompatActivity() {

    var checkedLangIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_splash_screen)

        btnPopMenu.setOnClickListener {
            val popMenu = PopupMenu(this, btnPopMenu)
            popMenu.menuInflater.inflate(R.menu.menu_pop, popMenu.menu)
            popMenu.setOnMenuItemClickListener { item ->
                when (item!!.itemId) {
                    R.id.itemLanguage -> {
                        showChangeLanguageDialog();
                    }
                    R.id.itemAbout -> {
                        val intent = Intent(this@SplashScreen,AboutActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
            popMenu.show()
        }

        btnGo.setOnClickListener {
            val intent = Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
        }
        contGetStarted.setOnClickListener {
            val intent = Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun showChangeLanguageDialog() {
        val listItems = arrayOf("O'zbek", "Ўзбек", "English", "한국어")

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle(R.string.choose_language)
        mBuilder.setSingleChoiceItems(listItems, checkedLangIndex) { dialog, which ->
            if (which == 0) {
                setLocale("uz")
                recreate()
            }
            else if (which == 1) {
                setLocale("kri")
                recreate()
            }
            else if (which == 2) {
                setLocale("en")
                recreate()
            }
            else if (which == 3) {
                setLocale("ko")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocale(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    public fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if(language == "uz")
            checkedLangIndex = 0
        else if (language == "kri")
            checkedLangIndex = 1
        else if (language == "en")
            checkedLangIndex = 2
        else if (language == "ko")
            checkedLangIndex = 3
        if (language != null) {
            setLocale(language)
        }
    }
}
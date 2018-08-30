package newsappbrowser.bacancy.com.kotlinnewsapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*
import newsappbrowser.bacancy.com.kotlinnewsapp.R
import newsappbrowser.bacancy.com.kotlinnewsapp.adapter.ArrayListAdapter
import newsappbrowser.bacancy.com.kotlinnewsapp.sqlite.SqliteManager
import android.R.array
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.inmobi.sdk.InMobiSdk
import kotlinx.android.synthetic.main.navigation_layout.*
import newsappbrowser.bacancy.com.kotlinnewsapp.utils.InmobiUtils


class MainActivity : AppCompatActivity() {

    var dbHelper: SqliteManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InMobiSdk.init(this@MainActivity, "11dec3f4aa354071ae848b4dbce699bd")
        InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG)

        dbHelper = SqliteManager(this)
        dbHelper?.copyDatabase()

//        drawericon.setOnClickListener { drawer_layout.openDrawer(Gravity.LEFT); }

        val spinner = findViewById<View>(R.id.lng_spinner) as Spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        setSpinner(spinner)
//        setSpinner(lng_spinner_drw)
        getselectionLang("4")

        fab.setOnClickListener {  }
    }

    public fun setSpinner(spinner: Spinner) {
        var arraylist = dbHelper?.getLangList()
        var stringarray = ArrayList<String>()
        for (items in arraylist!!) {
            if (!items?.tags.isNullOrBlank()) {
                stringarray.add(items.tags!!)
            }
        }

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stringarray)
        spinner.setSelection(3)

        toolbar_title.text = stringarray?.get(spinner.selectedItemPosition) + " NEWS"
        spinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                Log.e("spinner", "=>" + p2 + " :: " + p3)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("spinner", "=>" + p0)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.e("spinner", "=>" + p2 + " :: " + p3)
                getselectionLang((p2 + 1).toString())

                toolbar_title.text = stringarray?.get(spinner.selectedItemPosition) + " NEWS"

                InmobiUtils.showInterstitial(this@MainActivity)
            }
        }
    }

    public fun getselectionLang(lang: String) {

        InmobiUtils.showInterstitial(this@MainActivity)
        var newsArray = dbHelper?.getSelectlistItem(lang)
        Log.e("newsArray", "=>" + newsArray?.size)

        var adapter = ArrayListAdapter(newsArray!!, this@MainActivity)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)
        recyclerview.adapter = adapter
        /* if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
             drawer_layout.closeDrawer(GravityCompat.START)
         }*/
    }

    override fun onBackPressed() {
        /* if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
             drawer_layout.closeDrawer(GravityCompat.START)
         } else {*/
        super.onBackPressed()
//        }
    }

}

package newsappbrowser.bacancy.com.kotlinnewsapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_row_news.view.*
import newsappbrowser.bacancy.com.kotlinnewsapp.R
import newsappbrowser.bacancy.com.kotlinnewsapp.activity.WebActivity
import newsappbrowser.bacancy.com.kotlinnewsapp.utils.InmobiUtils
import newsappbrowser.bacancy.com.kotlinnewsapp.utils.NewsClass

class ArrayListAdapter(val items: ArrayList<NewsClass>, val context: Activity) : RecyclerView.Adapter<ArrayListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.titalTxt.text = items.get(position).newsname
        Log.e("pos", "=>" + items.get(position))

        holder.itemView.setOnClickListener {
            /*
                        (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                                .add(R.id.fragmentholder, DetailedFragment.newInstance(items.get(position), ""))
                                .addToBackStack("")
                                .commit()*/
            context.startActivity(Intent(context, WebActivity::class.java).putExtra("url", items.get(position).url))
        }

        holder.bind(items.get(position), context, position);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_news, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titalTxt = itemView.newspaper_name

        fun bind(news: NewsClass, context: Activity, pos: Int) {

            itemView?.setOnClickListener({
                InmobiUtils.showInterstitial(context)
                context.startActivity(Intent(context, WebActivity::class.java).putExtra("weburl", news.url).putExtra("title", news.newsname))
//                Toast.makeText(context, pos.toString() + " ", Toast.LENGTH_LONG).show()
            })


        }
    }

}

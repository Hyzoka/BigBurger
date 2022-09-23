package com.test.bigburger.ui.main.menu

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.test.bigburger.R
import com.test.bigburger.model.Menu
import com.test.bigburger.utils.KauIItem
import com.test.bigburger.utils.load

open class MenuItem(var data: Menu) : KauIItem<MenuItem, MenuItem.ViewHolder>(
    R.layout.item_menu, { ViewHolder(it) }, R.id.fastadapter_listitem_id
) {

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.title.text = data.title
        holder.descText.text = data.description
        holder.menuImage.load(data.thumbnail,RequestOptions().centerCrop())
        var price = StringBuilder(data.price.toString()).insert(data.price.toString().length-2,",")
        holder.price.text = "$price€"

    }

    override fun unbindView(holder: ViewHolder) {}

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = itemView.findViewById(R.id.title) as TextView
        var descText = itemView.findViewById<TextView>(R.id.descText)
        var menuImage = itemView.findViewById<ImageView>(R.id.menuImage)
        var price =  itemView.findViewById<TextView>(R.id.salary)
    }
}
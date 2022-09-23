package com.test.bigburger.ui.main.menu

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.bigburger.R
import com.test.bigburger.model.Menu
import com.test.bigburger.utils.KauIItem

open class MenuItem(var data: Menu) : KauIItem<MenuItem, MenuItem.ViewHolder>(
    R.layout.item_menu, { ViewHolder(it) }, R.id.fastadapter_listitem_id
) {

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.educationItem.setText(data.title)
    }

    override fun unbindView(holder: ViewHolder) {}

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var educationItem = itemView.findViewById(R.id.title) as TextView
    }
}
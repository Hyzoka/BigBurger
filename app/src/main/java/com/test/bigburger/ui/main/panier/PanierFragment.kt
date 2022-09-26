package com.test.bigburger.ui.main.panier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.test.bigburger.R
import com.test.bigburger.model.Menu
import com.test.bigburger.ui.main.menu.MenuItem
import com.test.bigburger.utils.Utils
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_panier.*

class PanierFragment : Fragment() {


    companion object {
        fun newInstance(listMenu : List<Menu>) = PanierFragment().apply {
            this.menuList = listMenu
        }
    }

    private lateinit var viewModel: PanierViewModel
    private lateinit var menuList : List<Menu>
    private var totalPrice = 0
    private val menuAdapter = FastItemAdapter<IItem<*, *>>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_panier, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PanierViewModel::class.java)
        setupUi()
        setupListOfMenu()

    }

    private fun setupUi(){
        menuList.forEach {
            totalPrice += it.price
        }
        price.text = "Total is : ${Utils.formatPrice(totalPrice)}€"
    }

    private fun setupListOfMenu(){
        menuAdapter.clear()
        menuAdapter.add(menuList.map { MenuItem(it) })

        listBasket.layoutManager = LinearLayoutManager(requireContext())
        listBasket.adapter = menuAdapter
    }
}
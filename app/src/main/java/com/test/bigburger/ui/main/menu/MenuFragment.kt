package com.test.bigburger.ui.main.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.test.bigburger.R
import com.test.bigburger.ui.main.panier.PanierFragment
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private val menuAdapter = FastItemAdapter<IItem<*, *>>()
    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        viewModel()
        setupListOfMenu()
        setupListener()
        checkBasketList()
    }

    private fun viewModel(){
        viewModel.menuList.observe(requireActivity(), Observer { list ->
            menuAdapter.clear()
            menuAdapter.add(list.map { MenuItem(it) })
        })
    }

    private fun setupListOfMenu(){
        listMenu.layoutManager = LinearLayoutManager(requireContext())
        listMenu.adapter = menuAdapter

        menuAdapter.withOnPreClickListener { v, adapter, item, position ->
            if(item is MenuItem) {
                viewModel.addMenuList(item.data)

            }
            checkBasketList()
            false
        }
    }

    private fun checkBasketList(){
        if (viewModel.basketList.size >= 1){
            countMenu.text = viewModel.basketList.size.toString()
            countMenu.visibility = View.VISIBLE
        }
    }

    private fun setupListener(){
        floatingPanier.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, PanierFragment.newInstance(viewModel.getMenu()))
                .addToBackStack(null)
                .commit()
        }
    }
}
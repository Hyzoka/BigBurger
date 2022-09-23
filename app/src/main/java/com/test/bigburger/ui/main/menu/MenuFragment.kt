package com.test.bigburger.ui.main.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.test.bigburger.R
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
    }

    private fun viewModel(){
        viewModel.menuList.observe(requireActivity(), Observer { list ->
            menuAdapter.clear()
            menuAdapter.add(list.map { MenuItem(it) })
        })
    }

    private fun setupListOfMenu(){
        listMenu.layoutManager = LinearLayoutManager(requireContext())
        menuAdapter.withSelectable(true)
        menuAdapter.withSelectWithItemUpdate(true)
        listMenu.adapter = menuAdapter

        menuAdapter.withOnPreClickListener { _, _, _, _ ->

            false
        }
    }

}
package com.test.bigburger.ui.main.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bigburger.manager.BurgerManager
import com.test.bigburger.model.Menu

class MenuViewModel : ViewModel() {

    val menuList: MutableLiveData<List<Menu>> = MutableLiveData()
    private val burgerManager = BurgerManager()
    var basketList = mutableListOf<Menu>()


    init {
        burgerManager.getBurgerListMenu()
        burgerManager.burgerObs.subscribe {
            menuList.postValue(it)
        }
    }

    fun addMenuList(menu: Menu){
        basketList.add(menu)
    }

    fun getMenu() = basketList.toList()
}
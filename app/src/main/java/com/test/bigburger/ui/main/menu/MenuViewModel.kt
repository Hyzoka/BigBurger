package com.test.bigburger.ui.main.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bigburger.manager.BurgerManager
import com.test.bigburger.model.Menu

class MenuViewModel : ViewModel() {

    val menuList: MutableLiveData<List<Menu>> = MutableLiveData()
    private val burgerManager = BurgerManager()

    init {
        getMenuList()
        burgerManager.burgerObs.subscribe {
            menuList.postValue(it)
        }
    }

    fun getMenuList(){
        burgerManager.getBurgerListMenu()
    }
}
package com.test.bigburger.manager

import android.annotation.SuppressLint
import com.test.bigburger.model.Menu
import com.test.bigburger.repo.MenuApi
import com.test.bigburger.service.RetrofitServiceBuilder
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Response

class BurgerManager {

    val retrofit =  RetrofitServiceBuilder.buildService(MenuApi::class.java)

    var burgerObs = PublishSubject.create<List<Menu>>()

    /*** GET  BURGER ***/
    @SuppressLint("CheckResult")
    fun getBurgerListMenu(){
        val success = { response: Response<List<Menu>> ->
            if(response.isSuccessful && response.body() != null){
                burgerObs.onNext(response.body())
            }
        }
        val error = { it : Throwable ->
            it.printStackTrace()
        }

        retrofit.getMenus().subscribeOn(Schedulers.io()).subscribe(success,error)
    }

}
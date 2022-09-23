package com.test.bigburger.manager

import android.annotation.SuppressLint
import android.util.Log
import com.test.bigburger.model.Menu
import com.test.bigburger.repo.MenuApi
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
                Log.i("GET_DATA_ENSAH","${response.body()}")
                burgerObs.onNext(response.body())
            }
        }
        val error = { it : Throwable ->
            Log.i("GET_DATA_ENSAH","${it.message}")
            it.printStackTrace()
        }

        retrofit.getMenus().subscribeOn(Schedulers.io()).subscribe(success,error)
    }
}
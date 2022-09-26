package com.test.bigburger.utils


object Utils{

    fun formatPrice(price : Int) : String {
        return StringBuilder(price.toString()).insert(price.toString().length-2,",").toString()
    }
}


package com.mvp.model

class User {
    var token: String? = null
    var name: String? = null
    var email: String? = null
    private var phone: String? = null
    var address: String? = null
    var picture: String? = null
    var wallet: Long = 0

    fun getPhone(): String {
        return phone!!.replace("+91", "")
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

}
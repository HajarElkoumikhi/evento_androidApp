package com.evento

import com.evento.Users
import com.google.gson.annotations.SerializedName

/**
 * Created by Eyehunt Team on 12/06/18.
 */
class UsersList {
    @SerializedName("items")
    var users: List<Users>? = null
}
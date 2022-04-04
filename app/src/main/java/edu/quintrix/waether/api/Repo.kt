package edu.quintrix.waether.api

data class Repo(
    var login : String? = "",
    var url : String? = "",
    var bio : String? = "",
    var followers : Int? = 0,
    var created_at : String? = "",
    var name : String? = ""
    )
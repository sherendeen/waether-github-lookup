package edu.quintrix.waether.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user}")
    fun getInfo(@Path("user") user : String) : Call<Repo>
}
package edu.quintrix.waether.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quintrix.waether.api.*
import retrofit2.converter.gson.GsonConverterFactory

class GithubFetcher {

    fun fetchContents(user: String): Repo? {
        val responseLiveData: MutableLiveData<Repo> = MutableLiveData()

        var theRepo: Repo = Repo()

        val dataCall: Call<Repo> = githubApi.getInfo(user)

        dataCall.enqueue(object : Callback<Repo> {
            override fun onResponse(call: Call<Repo>, response: Response<Repo>) {

                if (response.code() != 200) {
                    Log.d(TAG, "Please check connection!!!")
                    return
                }

                Log.d(
                    TAG, "Here's the data: ${response.body()?.login} " +
                            "${response.body()?.name} " +
                            "${response.body()?.followers} " +
                            "${response.body()?.bio} " +
                            "${response.body()?.url} "
                )

                // try to put the data into the recycler
                //response.body().login


//                responseLiveData.value = response.body()

                /// val login : String = "",
                //    val url : String = "",
                //    val bio : String = "",
                //    val followers : Int = 0,
                //    val created_at : String = "",
                //    val name : String = ""

                theRepo.bio = response.body()?.bio
                theRepo.login = response.body()?.login
                theRepo.url = response.body()?.url

                theRepo.followers = response.body()?.followers
                theRepo.created_at = response.body()?.created_at
                theRepo.name = response.body()?.name


                Log.d(TAG, "EXITING ONRESPONSE()")
            }

            override fun onFailure(call: Call<Repo>, t: Throwable) {
                Log.e(TAG, "Failed to get any useful data", t)
            }

        })

        return theRepo
    }

    private val githubApi: GithubService

    init {
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build() as Retrofit

        githubApi = retrofit.create(GithubService::class.java)


    }


}


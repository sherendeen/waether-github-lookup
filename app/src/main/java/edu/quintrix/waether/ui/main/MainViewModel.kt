package edu.quintrix.waether.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData
import edu.quintrix.waether.api.GithubFetcher
import edu.quintrix.waether.api.Repo


class MainViewModel : ViewModel() {
    private var resultText = ""
    private var result: MutableLiveData<Repo> = MutableLiveData()

    fun getFormattedStringRepresentingOutput(user:String/*repo: MutableLiveData<Repo>*/) {

        val information: MutableLiveData<Repo> = GithubFetcher().fetchContents(user)
        this.resultText = """
            login: ${information.value?.login}
            name: ${information.value?.name}
            bio: ${information.value?.bio}
            followers: ${information.value?.followers}
            url: ${information.value?.url}
        """.trimIndent()
//        currentRepo.setValue(value.toString())

        result.setValue(information.value)
        Log.e(TAG, "Exited getFormattedStringRepresentingOutput")
    }

    fun getResult():MutableLiveData<Repo>{
        return result
    }


}
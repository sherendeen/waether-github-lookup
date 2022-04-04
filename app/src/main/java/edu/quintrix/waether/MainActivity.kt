package edu.quintrix.waether

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import edu.quintrix.waether.api.GithubFetcher
import edu.quintrix.waether.api.Repo

class MainActivity : AppCompatActivity() {

    private lateinit var txt_results : TextView
    private lateinit var btn_submit : Button
    private lateinit var edt_text_contents : EditText

//    companion object {
//        fun newInstance() = MainActivity()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_results = findViewById(edu.quintrix.waether.R.id.text_results)
        btn_submit = findViewById(R.id.button_submit)
        edt_text_contents =  findViewById(edu.quintrix.waether.R.id.input_text)
//        val isFragmentContainerEmpty = savedInstanceState == null
//        if (isFragmentContainerEmpty) {
//            supportFragmentManager.beginTransaction().add(R.id.fragment_container_view,
//                MainActivity.newInstance()).commit()
//        }

//        val githubLiveData: LiveData<Repo> =
//         txt_results = txt_results.findViewById<TextView>(R.id.text_vew)

        btn_submit.setOnClickListener {

            val user = edt_text_contents.text.toString()
            val information:Repo? = GithubFetcher().fetchContents(user)

            if (edt_text_contents.text.isNotEmpty()) {

                txt_results.setText(
                    "Github user result:\nLogin: ${information?.login.toString()} " +
                            "\nName: ${information?.name.toString()} \nBio: ${information?.bio.toString()}" +
                            " \nFollowers: ${information?.followers.toString()} \nURL: ${information?.url.toString()}"
                )
            } else {
                Toast.makeText(this,"Username field cannot be blank!", Toast.LENGTH_LONG)
            }
        }


    }

}
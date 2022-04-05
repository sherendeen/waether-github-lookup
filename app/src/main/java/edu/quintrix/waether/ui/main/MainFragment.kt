package edu.quintrix.waether.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import edu.quintrix.waether.R
import edu.quintrix.waether.api.GithubFetcher
import edu.quintrix.waether.api.Repo
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        class resultObserver : Observer<Repo> {
            override fun onChanged(value: Repo?) {

                if (value != null) {
                    resultText.text = """
            login: ${value?.login}
            name: ${value?.name}
            bio: ${value?.bio}
            followers: ${value?.followers}
            url: ${value?.url}
        """.trimIndent()
                }
            }
        }

        val myResultObserver = resultObserver()

        viewModel.getResult().observe(viewLifecycleOwner, myResultObserver)

//        inputText =  inputText.findViewById(R.id.input_text)

        button_submit.setOnClickListener {




            if ( input_text.text.isNotEmpty()) {

                val user = input_text.text.toString()
//



                viewModel.getFormattedStringRepresentingOutput(user)




            } else {
                Toast.makeText(this.context,
                    "You have not written anything in the input field", Toast.LENGTH_LONG)
            }

        }
    }
}


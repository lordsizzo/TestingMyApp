package luis.sizzo.testingmyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import luis.sizzo.testingmyapp.databinding.ActivityMainBinding
import luis.sizzo.testingmyapp.model.UI_State
import luis.sizzo.testingmyapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }
    var encryptedString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOberserver()
        initViews()
    }

    private fun initViews() {
        binding.etTest.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getStringEncripted(binding.etTest.text.toString())
            }
        })

        binding.btnSplitTest.setOnClickListener{
            viewModel.getSplitEncripted(encryptedString)
        }
    }

    private fun initOberserver() {
        viewModel.getStringResponse.observe(this) { result ->
            try {
                when (result) {
                    is UI_State.LOADING -> {

                    }
                    is UI_State.SUCCESS<*> -> {
                        val stringResult = result.response
                        stringResult?.let {
                            binding.resultTest.text = "Encrypted: ${it.toString()}"
                            encryptedString = it.toString()
                        } ?: showError("Error at casting")
                    }
                    is UI_State.ERROR -> {
                        result.error.localizedMessage?.let { error -> showError(error) }
                    }
                }
            } catch (e: Exception) {
                showError(e.toString())
            }
        }

        viewModel.getFlipStringResponse.observe(this) { result ->
            try {
                when (result) {
                    is UI_State.LOADING -> {

                    }
                    is UI_State.SUCCESS<*> -> {
                        val stringResult = result.response
                        stringResult?.let {
                            binding.flipResultTest.text = "Flipped: ${it.toString()}"
                        } ?: showError("Error at casting")
                    }
                    is UI_State.ERROR -> {
                        result.error.localizedMessage?.let { error -> showError(error) }
                    }
                }
            } catch (e: Exception) {
                showError(e.toString())
            }
        }

    }

    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error occurred")
            .setMessage(message)
            .setNegativeButton("CLOSE") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
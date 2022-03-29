package uz.ilkhomkhuja.dagger2example.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.ilkhomkhuja.dagger2example.adapters.UserAdapter
import uz.ilkhomkhuja.dagger2example.app.App
import uz.ilkhomkhuja.dagger2example.databinding.ActivityMainBinding
import uz.ilkhomkhuja.dagger2example.utils.UserResource
import uz.ilkhomkhuja.dagger2example.viewmodels.UserViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var userViewModel: UserViewModel
    private lateinit var job: Job
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()
        userAdapter = UserAdapter()
        binding.rv.adapter = userAdapter

        launch {
            userViewModel.getStateFlow().collect {
                when (it) {
                    is UserResource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rv.visibility = View.GONE
                    }
                    is UserResource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rv.visibility = View.VISIBLE
                        userAdapter.submitList(it.list)
                    }
                    is UserResource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}
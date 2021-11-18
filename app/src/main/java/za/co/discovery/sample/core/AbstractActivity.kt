package za.co.discovery.sample.core

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class AbstractActivity<V: ViewBinding> : AppCompatActivity() {
    protected var binding: V? = null

    fun requireBinding(): V = requireNotNull(binding)

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
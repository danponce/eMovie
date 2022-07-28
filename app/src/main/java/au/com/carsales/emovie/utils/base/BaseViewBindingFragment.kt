package au.com.carsales.emovie.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * Created by Dan on 11, February, 2021
 * Copyright (c) 2021 Carsales. All rights reserved.
 *
 * Used to have a fragment implement View Binding
 * in a more faster and easier way
 */
abstract class BaseViewBindingFragment<T : ViewBinding> : BaseNavFragment() {

    // For reflection function
    private lateinit var persistentClass: Class<T>
    private var _binding: T? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get the actual class
        persistentClass = ((this.javaClass.genericSuperclass as ParameterizedType?)?.actualTypeArguments?.get(0) as Class<T>)

        _binding = ViewBindingUtil.inflate(inflater, container, false, persistentClass)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

internal class ViewBindingUtil {

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <T : ViewBinding> inflate(
            inflater: LayoutInflater,
            root: ViewGroup?,
            attachToRoot: Boolean,
            clazz: Class<T>
        ): T {
            return clazz
                .getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                .invoke(null, inflater, root, attachToRoot) as T
        }

        inline fun <reified T : ViewBinding> inflate(
            inflater: LayoutInflater
        ): T {
            return T::class.java
                .getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, inflater) as T
        }

        inline fun <reified T : ViewBinding> inflate(
            inflater: LayoutInflater,
            clazz: Class<T>
        ): T {
            return clazz
                .getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, inflater) as T
        }
    }
}

package com.example.vamz_sem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vamz_sem.filmy.GlobalViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * BaseFragment je abstraktná trieda, ktorá slúži ako základná implementácia pre všetky fragmenty v aplikácii.
 * Poskytuje spoločnú funkcionalitu a metódy, ktoré môžu byť použité vo všetkých fragmentoch.
 *
 * @param T Typový parameter, ktorý rozširuje triedu ViewDataBinding a reprezentuje dátové viazanie pre layout fragmentu.
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var binding: T  // Premenná pre viazanie dát layoutu fragmentu
    lateinit var globalViewModel: GlobalViewModel  // Zdieľaný view model

    /**
     * Metóda, ktorá sa volá pri vytvorení zobrazenia fragmentu.
     * Inflatuje layout fragmentu a viaže ho na premennú [binding].
     * @return je koreňový [View] layoutu fragmentu.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateData()
        binding = DataBindingUtil.inflate(inflater,getFragmentView(),container,false)
        return binding.root
    }

    /**
     * Abstraktná metóda, ktorá vracia ID layoutu fragmentu.
     * @return [Int] ID layoutu fragmentu
     */
    abstract fun getFragmentView(): Int

    /**
     * Metóda pre aktualizáciu údajov vo view modely.
     * Asynchrónne vykonáva aktualizáciu zoznamu údajov [globalViewModel.data] v samostatnej korutine.
     */
    fun updateData(){
        lifecycleScope.launch {
            globalViewModel.data.value =  globalViewModel.database.getAllFilmyData()
            globalViewModel.data.value =  globalViewModel.data.value!!.sortedBy { it.id }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        globalViewModel = ViewModelProvider(requireActivity())[GlobalViewModel::class.java]
    }
}
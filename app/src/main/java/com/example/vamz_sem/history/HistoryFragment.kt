package com.example.vamz_sem.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentHistoryBinding
import com.example.vamz_sem.filmy.FilmyData

/**
 * HistoryFragment je fragment, ktorý zobrazuje históriu prezeraných filmov.
 * Zobrazuje zoznam filmov, ktoré boli už "videné" v minulosti.
 */
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    private lateinit var adapter: AdapterHistory
    /**
     * Metóda, ktorá sa volá pri vytvorení zobrazenia fragmentu.
     * Inicializuje UI a nastavuje RecyclerView s adaptérom pre zobrazovanie histórie filmov.
     *
     * @param inflater [LayoutInflater] objekt pre nadúvanie XML layoutu
     * @param container [ViewGroup] rodičovský view
     * @param savedInstanceState [Bundle] stav fragmentu
     * @return [View] vytvorené zobrazenie fragmentu
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        updateData()
        return binding.root
    }
    /**
     * Metóda, ktorá vracia ID layoutu fragmentu.
     * @return [Int] ID layoutu fragmentu
     */
    override fun getFragmentView(): Int = R.id.historyFragment

    /**
     * Metóda, ktorá sa volá po vytvorení zobrazenia fragmentu.
     * Aktualizuje dáta a nastavuje adaptér pre RecyclerView s históriou filmov.
     *
     * @param view [View] zobrazenie fragmentu
     * @param savedInstanceState [Bundle] stav fragmentu
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()
        val pomData = ArrayList<FilmyData>()

        for(data in globalViewModel.data.value!!){
           if(data.list == "history"){
               pomData.add(data)
           }
        }

        AdapterHistory(pomData,globalViewModel).apply {
            binding.historyRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.historyRecycler.adapter = this
        }
    }
}

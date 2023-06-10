package com.example.vamz_sem.history

import android.os.Bundle
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentHistoryBinding
import com.example.vamz_sem.database.FilmyData

/**
 * HistoryFragment je fragment, ktorý zobrazuje históriu prezeraných filmov.
 * Zobrazuje zoznam filmov, ktoré boli už "videné" v minulosti.
 */
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    /**
     * Metóda, ktorá vracia ID layoutu fragmentu.
     * @return [Int] ID layoutu fragmentu
     */
    override fun getFragmentView(): Int = R.layout.fragment_history

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

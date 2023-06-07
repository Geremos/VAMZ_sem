package com.example.vamz_sem.my_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentMyListBinding
import com.example.vamz_sem.filmy.FilmyData
/**
 * MyListFragment je fragment zodpovedný za zobrazenie zoznamu filmov v "Mojom zozname".
 * Zobrazuje filmy, ktoré boli pridané do zoznamu "myList".
 */
class MyListFragment : BaseFragment<FragmentMyListBinding>() {

    /**
     * Metóda, ktorá sa volá pri vytvorení zobrazenia fragmentu.
     * Aktualizuje údaje pomocou metódy updateData().
     * @param inflater [LayoutInflater] objekt, ktorý sa používa na inflatovanie layoutu fragmentu
     * @param container [ViewGroup] rodičovský view, do ktorého sa fragment vkladá
     * @param savedInstanceState [Bundle] stav fragmentu, ak existuje
     * @return [View] koreňový view layoutu fragmentu
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyListBinding.inflate(layoutInflater)
        updateData()
        return binding.root
    }

    /**
     * Metóda, ktorá sa volá po vytvorení zobrazenia fragmentu.
     * Initializes the UI and sets up the RecyclerView with the list of films.
     * Aktualizuje údaje pomocou metódy updateData().
     * Vytvára pomocný zoznam filmov [pomData] z údajov v [globalViewModel.data].
     * Vytvára a nastavuje adaptér [AdapterMyList] pre RecyclerView.
     * @param view [View] zobrazenie fragmentu
     * @param savedInstanceState [Bundle] stav fragmentu, ak existuje
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()

        val pomData = ArrayList<FilmyData>()

        for(data in globalViewModel.data.value!!){
            if(data.list == "myList"){
                pomData.add(data)
            }
        }

        AdapterMyList(pomData, globalViewModel).apply {
            binding.myListRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.myListRecycler.adapter = this
        }
    }
    /**
     * Metóda, ktorá vracia ID layoutu fragmentu.
     * @return [Int] ID layoutu fragmentu
     */
    override fun getFragmentView(): Int = R.id.myListFragment
}

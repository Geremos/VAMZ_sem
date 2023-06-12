package com.example.vamz_sem.filmy


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyBinding
import kotlinx.coroutines.launch

/**
 * Fragment zobrazujúci zoznam filmov.
 */
class FilmyFragment : BaseFragment<FragmentFilmyBinding>() {
    private lateinit var adapter: AdapterFilmy
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var popupWindow : PopupWindow
    /**
     * Metóda pre získanie ID layoutu fragmentu.
     * @return ID layoutu fragmentu.
     */
    override fun getFragmentView(): Int = R.layout.fragment_filmy
    /**
     * Metóda volaná po vytvorení fragmentu.
     *
     * @param view Vytvorený pohľad fragmentu.
     * @param savedInstanceState Uložený stav fragmentu.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()

        // Nastavenie LayoutManagera pre zobrazenie filmov v mriežke s príslušným počtom stĺpcov
        layoutManager = if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            GridLayoutManager(requireContext(),4 , GridLayoutManager.VERTICAL, false)
        } else {
            GridLayoutManager(requireContext(),2 , GridLayoutManager.VERTICAL, false)
        }

        // Vytvorenie a konfigurácia PopupWindow pre zobrazenie žánrov
        val popupView = layoutInflater.inflate(R.layout.popup_window, null)
        val zanerRecycler = popupView.findViewById<RecyclerView>(R.id.zaner_recycler)
        zanerRecycler.layoutManager = LinearLayoutManager(context)
        popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

        zanerRecycler.adapter =
            globalViewModel.genres.value?.let { AdapterZaner(it,globalViewModel) }

        // Sledovanie zmeny zoznamu žánrov a aktualizácia adaptéra
        globalViewModel.genres.observe(viewLifecycleOwner, Observer { value ->
            zanerRecycler.adapter = AdapterZaner(value,globalViewModel)
        })

        // Obsluha kliknutia na tlačidlo pre otvorenie PopupWindow
        binding.menuFilterBtn.setOnClickListener {
            Log.d("popUP","open")
            showCustomPopup()
        }

        // Inicializácia adaptéra pre zobrazenie filmov v RecyclerView
        adapter = AdapterFilmy(emptyList(), globalViewModel).apply {
            binding.filmyRecycler.layoutManager = layoutManager
            binding.filmyRecycler.adapter = this
        }

        // Sledovanie zmeny filtrovaných žánrov a aktualizácia zoznamu filmov
        globalViewModel.filterGenres.observe(viewLifecycleOwner, Observer { value ->
            if(value.isNotEmpty()){
                val filterList = globalViewModel.data.value?.filter { containsAnyGenre(it.genre!!, value) }
                adapter = AdapterFilmy(filterList!!, globalViewModel).apply {
                    binding.filmyRecycler.layoutManager = layoutManager
                    binding.filmyRecycler.adapter = this
                }
            } else {
                adapter = globalViewModel.data.value?.let {
                    AdapterFilmy(it, globalViewModel).apply {
                        binding.filmyRecycler.layoutManager = layoutManager
                        binding.filmyRecycler.adapter = this
                    }
                }!!
            }
        })

        // Sledovanie zmeny zoznamu filmov a aktualizácia RecyclerView
        globalViewModel.data.observe(viewLifecycleOwner, Observer { value ->
            adapter = AdapterFilmy(value, globalViewModel).apply {
                binding.filmyRecycler.layoutManager = layoutManager
                binding.filmyRecycler.adapter = this
                extractGenres()
            }
        })


        // Obsluha kliknutia na tlačidlo pre zobrazenie alebo skrytie vyhľadávacieho poľa
        binding.searchBtnId.setOnClickListener {
            if (binding.searchViewFilmy.visibility == View.VISIBLE) {
                binding.searchViewFilmy.visibility = View.GONE
                binding.searchBtnId.setImageResource(R.drawable.ic_search)

            } else {
                binding.searchViewFilmy.visibility = View.VISIBLE
                binding.searchBtnId.setImageResource(R.drawable.ic_close)
            }
        }

        // Obsluha vyhľadávania filmov
        binding.searchViewFilmy.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            /**
             * Metóda volaná po potvrdení vyhľadávacieho dotazu.
             *
             * @param query Vyhladávací dotaz.
             * @return True, ak sa má pokračovať v spracovaní dotazu, false inak.
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            /**
             * Metóda volaná pri zmene vyhľadávacieho dotazu.
             *
             * @param newText Nový vyhľadávací dotaz.
             * @return True, ak sa má pokračovať v spracovaní dotazu, false inak.
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty())
                    lifecycleScope.launch {
                        val pomData = globalViewModel.database.getFilteredByInput(newText)
                        adapter.updateDataSet(pomData)
                    } else {
                    globalViewModel.data.value?.let { adapter.updateDataSet(it) }
                }
                return true
            }
        })


    }

    /**
     * Extrahuje žánre filmov z databázy a aktualizuje LiveData objekt `genres`.
     */
    private fun extractGenres() {
        lifecycleScope.launch {
            val zanre = ArrayList<String>()
            for (data in globalViewModel.database.getAllFilmyData()) {
                val pomZanerList = data.genre?.split(", ")?.toList()
                if (pomZanerList != null) {
                    for (i in pomZanerList)
                        if (!zanre.contains(i)) {
                            zanre.add(i)
                        }
                }
            }
            globalViewModel.genres.value = zanre
        }
    }

    /**
     * Skontroluje, či film obsahuje aspoň jeden zo žánrov v zozname `najdiZanre`.
     * @param zanre Žánre filmu oddelené čiarkami
     * @param najdiZanre Zoznam žánrov, ktoré sa majú hľadať
     * @return True, ak film obsahuje aspoň jeden žáner zo zoznamu `najdiZanre`, inak False
     */
    private fun containsAnyGenre(zanre: String, najdiZanre: List<String>): Boolean {
        val genres = zanre.split(", ").map { it.trim() }
        return genres.any { najdiZanre.contains(it) }
    }
    /**
     * Zobrazí vyskakovacie okno pre filtrovanie žánrov.
     */
    private fun showCustomPopup() {
        popupWindow.showAtLocation(requireView(), Gravity.START, 0, 0)
    }
    /**
     * Zavrie vyskakovacie okno.
     */
    private fun dismissCustomPopup() {
        popupWindow.dismiss()
    }
    /**
     * Zavrie vyskakovacie okno po stlačení tlačídla späť.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        dismissCustomPopup()
    }

}

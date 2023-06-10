package com.example.vamz_sem.filmy
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.GlobalViewModel
import com.example.vamz_sem.databinding.ZanerItemBinding


/**
 * Adaptér pre zobrazenie zoznamu žánrov.
 *
 * @param zanre zoznam žánrov
 * @param globalViewModel Referencia na [GlobalViewModel] pre zdieľanie dát medzi fragmentmi.
 */
class AdapterZaner(
    var zanre: List<String>,
    var globalViewModel: GlobalViewModel
) : RecyclerView.Adapter<ZanerViewHolder>() {
    private var checkedList = MutableList(zanre.size) { false }


    /**
     * Vytvára nový objekt ViewHolderu pre jednotlivé položky žánru.
     *
     * @param parent rodičovský ViewGroup
     * @param viewType typ zobrazenia položky
     * @return nový objekt ZanerViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZanerViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ZanerItemBinding.inflate(from, parent, false)
        return ZanerViewHolder(binding, globalViewModel)
    }

    /**
     * Vráti počet položiek v zozname žánrov.
     *
     * @return počet položiek v zozname žánrov
     */
    override fun getItemCount(): Int {
        return zanre.size
    }

    /**
     * Vykreslí dáta pre konkrétny ViewHolder na základe jeho pozície.
     *
     * @param holder ViewHolder, do ktorého sa majú vykresliť dáta
     * @param position pozícia položky v zozname
     */
    override fun onBindViewHolder(holder: ZanerViewHolder, position: Int) {
        holder.bindZaner(zanre[position],false)

        holder.zanerBinding.genreCheckBox.setOnCheckedChangeListener { _, isChecked ->
            checkedList[position] = isChecked
            setCheckedItems()
        }
    }


    /**
     * ViewHolder pre zobrazenie jednej položky žánru.
     *
     * @param zanerBinding objekt prepojenia s layoutom jednej položky žánru
     * @param globalViewModel inštancia triedy GlobalViewModel
     */
    private fun setCheckedItems() {
        val checkedItems = ArrayList<String>()
        for (index in zanre.indices) {
            Log.d("index", "$index")
            if (checkedList[index]) {
                checkedItems.add(zanre[index])
            }
        }
        globalViewModel.filterGenres.value = checkedItems
    }

}

/**
 * ViewHolder pre zobrazenie jednej položky žánru.
 *
 * @param zanerBinding objekt prepojenia s layoutom jednej položky žánru
 * @param globalViewModel Referencia na [GlobalViewModel] pre zdieľanie dát medzi fragmentmi.
 */
class ZanerViewHolder(
    var zanerBinding: ZanerItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(zanerBinding.root) {

    /**
     * Nastavuje hodnoty pre zobrazenie jednej položky žánru.
     *
     * @param zaner názov žánru
     * @param checked true, ak je položka označená, false inak
     */
    fun bindZaner(zaner: String, checked: Boolean) {
        zanerBinding.genreText.text = zaner
        zanerBinding.genreCheckBox.isChecked = checked
    }
}

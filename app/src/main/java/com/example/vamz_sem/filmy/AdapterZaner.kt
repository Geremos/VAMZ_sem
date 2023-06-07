package com.example.vamz_sem.filmy
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.databinding.ZanerItemBinding


class AdapterZaner(
    var zanre: List<String>,
    var globalViewModel: GlobalViewModel
) : RecyclerView.Adapter<ZanerViewHolder>() {
    private var checkedList = MutableList(zanre.size) { false }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZanerViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ZanerItemBinding.inflate(from, parent, false)
        return ZanerViewHolder(binding, globalViewModel)
    }

    override fun getItemCount(): Int {
        return zanre.size
    }

    override fun onBindViewHolder(holder: ZanerViewHolder, position: Int) {
        holder.bindZaner(zanre[position],false)

        holder.zanerBinding.genreCheckBox.setOnCheckedChangeListener { _, isChecked ->
            checkedList[position] = isChecked
            setCheckedItems()
        }
    }

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

class ZanerViewHolder(
    var zanerBinding: ZanerItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(zanerBinding.root) {
    fun bindZaner(zaner: String, checked: Boolean) {
        zanerBinding.genreText.text = zaner
        zanerBinding.genreCheckBox.isChecked = checked
    }
}

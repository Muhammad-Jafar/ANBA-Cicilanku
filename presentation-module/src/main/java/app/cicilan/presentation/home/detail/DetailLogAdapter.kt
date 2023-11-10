package app.cicilan.presentation.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.cicilan.entities.ItemLogEntity
import app.cicilan.presentation.databinding.ItemDetailLogBinding

class DetailLogAdapter :
    ListAdapter<ItemLogEntity, DetailLogAdapter.ViewHolder>(DIFF_CALLBACK_LOG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemDetailLogBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemDetailLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemLogEntity) = with(binding) {
            /* tanggalTransaksi.text = item.tglTransaksiFormat
             nominalTransaksi.text = item.nominalTransaksiToRupiah
             noteTransaksi.text = item.catatanTransaksi*/
        }
    }

    companion object {
        /* Diff Callback RecycleView for ItemLogEntity*/
        val DIFF_CALLBACK_LOG = object : DiffUtil.ItemCallback<ItemLogEntity>() {
            override fun areItemsTheSame(
                oldItem: ItemLogEntity,
                newItem: ItemLogEntity,
            ): Boolean = oldItem.idLog == newItem.idLog

            override fun areContentsTheSame(
                oldItem: ItemLogEntity,
                newItem: ItemLogEntity,
            ): Boolean = oldItem == newItem
        }
    }
}

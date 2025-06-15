package app.cicilan.navigation.home.current

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.cicilan.entities.Item
import app.cicilan.navigation.R
import app.cicilan.navigation.databinding.ItemCurrentBinding
import app.cicilan.navigation.home.HomeFragmentDirections
import com.google.android.material.color.MaterialColors.getColor

class CurrentAdapter :
    ListAdapter<Item, CurrentAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemCurrentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemCurrentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Item) = with(binding) {
            avatar.apply {
                scaleType = if (items.gambarBarang != null) {
                    setImageURI(items.gambarBarang!!.toUri())
                    ImageView.ScaleType.CENTER_CROP
                } else {
                    setBackgroundColor(getColor(this.rootView, R.attr.colorCustomContainer))
                    setImageResource(R.drawable.icon_bg_image)
                    ImageView.ScaleType.CENTER_INSIDE
                }
            }

            nameProduct.text = items.namaBarang
            nameUser.text = items.namaPenyicil
            /* cicilanPerBulan.text = items.nominalPerBulanToRupiah*/

            with(itemProgress) {
                max = items.nominalBayar
                /*setProgressCompat(items.nominalLunas, true)*/
            }
            /*sisaCicilanContent.text =
                rupiahFormat(items.nominalBayar - items.nominalLunas)*/

            itemView.setOnClickListener {
                val direction =
                    HomeFragmentDirections.actionMainToDetail(items.idCicilan)
                it.findNavController().navigate(direction)
            }
        }
    }

    companion object {
        /* Diff Callback RecycleView for ItemEntity*/
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item,
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item,
            ): Boolean = oldItem.idCicilan == newItem.idCicilan
        }
    }
}

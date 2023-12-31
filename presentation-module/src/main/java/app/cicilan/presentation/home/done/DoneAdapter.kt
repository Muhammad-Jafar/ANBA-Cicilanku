package app.cicilan.presentation.home.done

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.cicilan.entities.ItemEntity
import app.cicilan.presentation.R
import app.cicilan.presentation.databinding.ItemDoneBinding
import app.cicilan.presentation.home.HomeFragmentDirections
import app.cicilan.presentation.home.current.CurrentAdapter.Companion.DIFF_CALLBACK
import com.google.android.material.color.MaterialColors

class DoneAdapter : ListAdapter<ItemEntity, DoneAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemDoneBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemDoneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: ItemEntity) = with(binding) {
            avatar.apply {
                scaleType = if (items.gambarBarang != null) {
                    setImageURI(items.gambarBarang!!.toUri())
                    ImageView.ScaleType.CENTER_CROP
                } else {
                    setBackgroundColor(
                        MaterialColors.getColor(
                            this.rootView,
                            R.attr.colorCustomContainer,
                        ),
                    )
                    setImageResource(R.drawable.icon_bg_image)
                    ImageView.ScaleType.CENTER_INSIDE
                }
            }

            nameProduct.text = items.namaBarang
            nameUser.text = items.namaPenyicil
            /*totalLaba.text = "+ ".plus(items.totalLabaToRupiah)
            lunasPada.text = items.lunasPadaFormat*/

            itemView.setOnClickListener {
                val direction =
                    HomeFragmentDirections.actionMainToDetail(items.idCicilan)
                it.findNavController().navigate(direction)
            }
        }
    }

    companion object {
        /* Diff Callback RecycleView for ItemLogEntity*/
        val DIFF_CALLBACK_LOG = object : DiffUtil.ItemCallback<ItemEntity>() {
            override fun areItemsTheSame(
                oldItem: ItemEntity,
                newItem: ItemEntity,
            ): Boolean = oldItem.idCicilan == newItem.idCicilan

            override fun areContentsTheSame(
                oldItem: ItemEntity,
                newItem: ItemEntity,
            ): Boolean = oldItem == newItem
        }
    }
}

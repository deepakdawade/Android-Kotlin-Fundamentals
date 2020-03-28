package com.example.androidkotlinfundamentals.marsrealestate.marsoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinfundamentals.databinding.GridViewItemBinding
import com.example.androidkotlinfundamentals.marsrealestate.network.MarsProperty

class PhotoGridAdapter(private val onClickListener:OnClickListener) :
    ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallBack) {

    class MarsPropertyViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(marsProperty: MarsProperty) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (marsProperty:MarsProperty)-> Unit){
        fun onclick(marsProperty: MarsProperty) = clickListener(marsProperty)
    }
    companion object DiffCallBack : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPropertyViewHolder {
        val binding = GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return MarsPropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.onBind(marsProperty)
        holder.itemView.setOnClickListener {
            onClickListener.onclick(marsProperty)
        }
    }


}

//class PhotoGridAdapter( val onClickListener: OnClickListener ) :
//    ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

/*class MarsPropertyViewHolder(private var binding: GridViewItemBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(marsProperty: MarsProperty) {
        binding.property = marsProperty
        // This is important, because it forces the data binding to execute immediately,
        // which allows the RecyclerView to make the correct view size measurements
        binding.executePendingBindings()
    }
}*/

/**
 * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
 * has been updated.
 */
/*companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
    override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem.id == newItem.id
    }
}
*/
/*  override fun onCreateViewHolder(parent: ViewGroup,
                                  viewType: Int): MarsPropertyViewHolder {
      return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
  }
*/
/**
 * Replaces the contents of a view (invoked by the layout manager)
 */
/* override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
     val marsProperty = getItem(position)
     holder.itemView.setOnClickListener {
         onClickListener.onClick(marsProperty)
     }
     holder.bind(marsProperty)
 }*/

/**
 * Custom listener that handles clicks on [RecyclerView] items.  Passes the [MarsProperty]
 * associated with the current item to the [onClick] function.
 * @param clickListener lambda that will be called with the current [MarsProperty]
 */
/*class OnClickListener(val clickListener: (marsProperty:MarsProperty) -> Unit) {
    fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
}
}*/
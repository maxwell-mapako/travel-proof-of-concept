package za.co.discovery.sample.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.Disposable
import za.co.discovery.sample.R
import za.co.discovery.sample.databinding.AdapterImageBinding
import za.co.discovery.sample.domain.Content

class ImageAdapter : ListAdapter<Content, ImageAdapter.ImageViewHolder>(ImageAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val adapterImageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_image, parent, false)
        return ImageViewHolder(adapterImageView).apply {
            bind(adapterImageView)
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder(getItem(position))
    }

    override fun onViewRecycled(holder: ImageViewHolder) {
        holder.unbind()
        super.onViewRecycled(holder)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: AdapterImageBinding? = null
        private var disposable: Disposable? = null


        operator fun invoke(content: Content) {
            disposable = binding?.image?.load(content.image)
        }

        fun bind(view: View) {
            binding = AdapterImageBinding.bind(view)
        }

        fun unbind() {
            if (disposable?.isDisposed == false)
                disposable?.dispose()
            binding = null
        }
    }

    private companion object : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(
            oldItem: Content,
            newItem: Content
        ) = oldItem.image == newItem.image

        override fun areContentsTheSame(
            oldItem: Content,
            newItem: Content
        ) = oldItem == newItem
    }
}
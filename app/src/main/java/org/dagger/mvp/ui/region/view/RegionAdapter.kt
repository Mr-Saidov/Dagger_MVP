package org.dagger.mvp.ui.region.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_region.view.*
import org.dagger.mvp.R
import org.dagger.mvp.model.Region
import org.dagger.mvp.util.blockClickable

class RegionAdapter(val regions: ArrayList<Region>) :
    RecyclerView.Adapter<RegionAdapter.RegionHolder>() {
    var listener: ((Region) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RegionHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_region,
                parent,
                false
            )
        )

    override fun getItemCount() = regions.size

    override fun onBindViewHolder(holder: RegionHolder, position: Int) {
        holder.bind(regions[position])
        holder.itemView.cvRegion.setOnClickListener {
            it.blockClickable()
            listener?.invoke(regions[position])
        }
    }

    inner class RegionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tvRegionName)

        fun bind(region: Region) {
            tvName.text = region.nameUz
        }
    }
}
package com.ost.avtomaktab.ui.regions.view.branchs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_branch.view.*
import org.dagger.mvp.R
import org.dagger.mvp.model.Branch

class BranchAdapter(var data: ArrayList<Branch>) :
    RecyclerView.Adapter<BranchAdapter.BranchsHolder>() {
    var listener: ((Branch) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BranchsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_branch,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BranchsHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.branchPhone.setOnClickListener { listener?.invoke(data[position]) }
    }

    inner class BranchsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvBranchName: TextView
        var tvBranchAddressTV: TextView
        var tvBranchAddress: TextView
        var tvBranchPhoneTV: TextView
        var tvBranchPhone: TextView
        var cardView: CardView

        init {
            tvBranchName = itemView.findViewById(R.id.branchName)
            tvBranchAddressTV = itemView.findViewById(R.id.branchAddressTV)
            tvBranchAddress = itemView.findViewById(R.id.branchAddress)
            tvBranchPhoneTV = itemView.findViewById(R.id.branchPhoneTV)
            tvBranchPhone = itemView.findViewById(R.id.branchPhone)
            cardView = itemView.findViewById(R.id.branchCard)
        }

        fun bind(branch: Branch) {
            tvBranchName.text = branch.nameUz
            tvBranchAddress.text = branch.address
            tvBranchPhone.text = branch.phone
        }
    }
}


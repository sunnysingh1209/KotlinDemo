package com.example.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.SignupAdapterBinding
import com.example.kotlindemo.viewmodels.CountryModel

class SignUpAdapter(
    context: Context,
    var list: MutableList<CountryModel>,
    var action: (Int) -> Unit
) :
    RecyclerView.Adapter<SignUpAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {

        val employeeListItemBinding: SignupAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.signup_adapter, parent, false
        )
        return MyView(employeeListItemBinding)
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
//        holder.binding.country = list.get(position)
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
            action(holder.adapterPosition)
        }
    }

    class MyView(var binding: SignupAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
//        var signUpAdapter_id: TextView? = null
//
//        init {
//            signUpAdapter_id = itemView.findViewById(R.id.signUpAdapter_id) as TextView
//        }

        fun bind(countryModel: CountryModel) {
            binding.country = countryModel
//            signUpAdapter_id?.text = countryViewModel.name
            binding.executePendingBindings()
        }

    }

}
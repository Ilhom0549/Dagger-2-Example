package uz.ilkhomkhuja.dagger2example.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.ilkhomkhuja.dagger2example.database.entity.UserEntity
import uz.ilkhomkhuja.dagger2example.databinding.ItemUserBinding

class UserAdapter : ListAdapter<UserEntity, UserAdapter.Vh>(UserDiffUtil()) {

    inner class Vh(private var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(user: UserEntity) {
            itemUserBinding.apply {
                nameTv.text = "Name: ${user.name}"
                usernameTv.text = "Username: ${user.username}"
                emailTv.text = "Email: ${user.email}"
            }
        }
    }

    class UserDiffUtil : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
}
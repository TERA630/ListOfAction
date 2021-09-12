package io.terameteo.listofaction.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.terameteo.listofaction.MainViewModel
import io.terameteo.listofaction.R
import io.terameteo.listofaction.databinding.ItemCategoryBinding

class CategoryListAdaptor(private val mViewModel: MainViewModel)
    : ListAdapter<CategoryWithChecked, RecyclerView.ViewHolder>(CategoryDiffCallBack) {
    class ItemViewHolder(val mBinding:ItemCategoryBinding)
        :RecyclerView.ViewHolder(mBinding.root)

    override fun getItemCount(): Int {
        return mViewModel.categories.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(mBinding = binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (position==0){
//            (holder as ItemViewHolder).mBinding.categoryText.text = "全て"
//            val itemView = holder.mBinding.root
//            itemView.background = decideDrawable((mViewModel.currentCategory.value == ""),itemView)
//            holder.mBinding.root.setOnClickListener {
//                mViewModel.currentCategory.postValue("")
//            }
//        } else {
            itemBind(position,(holder as ItemViewHolder).mBinding)
 //       }
    }
    private fun itemBind(position: Int, binding:ItemCategoryBinding){
        val item = getItem(position)
         binding.categoryText.text = item.title
         binding.root.background = decideDrawable(item.checked,binding.root)

    }
    private fun decideDrawable(condition:Boolean,itemView: View):Drawable{
        val resourceId = if(condition) R.drawable.square_round_white  else R.drawable.square_round_gray
        val drawable = ResourcesCompat.getDrawable( itemView.resources, resourceId, itemView.context.theme)
        return if (drawable == null ){
            Log.w("categoryListAdaptor","getDrawable was failed")
            itemView.background
        } else{
            drawable
        }
    }
}
object CategoryDiffCallBack : DiffUtil.ItemCallback<CategoryWithChecked>() {
    override fun areItemsTheSame(
        old: CategoryWithChecked, new: CategoryWithChecked
    ): Boolean {
        return old.title == new.title
    }
    override fun areContentsTheSame(
        old: CategoryWithChecked, new: CategoryWithChecked
    ): Boolean {
        return (old.checked == new.checked) && (old.title == new.title)
    }
}
class CategoryWithChecked(
    val title:String,
    var checked:Boolean = false)
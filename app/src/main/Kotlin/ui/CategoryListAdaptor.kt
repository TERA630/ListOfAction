package io.terameteo.listofaction.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.terameteo.listofaction.MainViewModel
import io.terameteo.listofaction.R
import io.terameteo.listofaction.databinding.ItemCategoryBinding

class CategoryListAdaptor( private val mViewModel: MainViewModel )
    : RecyclerView.Adapter<CategoryListAdaptor.ItemViewHolder>(){
    class ItemViewHolder( val mBinding:ItemCategoryBinding )
        :RecyclerView.ViewHolder(mBinding.root)

    override fun getItemCount(): Int {
        return mViewModel.categories.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(mBinding = binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            itemBind(position, holder.mBinding)
    }
    private fun itemBind(position: Int, binding:ItemCategoryBinding){
        val item = mViewModel.categories[position]
         binding.categoryText.text = item.title
         binding.categoryDeleteButton.setOnClickListener {
             mViewModel.categories.remove(item)
         }
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
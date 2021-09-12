package io.terameteo.listofaction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import io.terameteo.listofaction.MainViewModel
import io.terameteo.listofaction.R
import io.terameteo.listofaction.databinding.FragmentCategoryBinding

// Fragmentではon ViewCreatedでViewのBindをするように推奨されているが､
// ViewBindingがあると､Bindをそのまま使えるのでonCreteViewでやってしまう

class CategoryFragment : Fragment() {
    private val mViewModel: MainViewModel by activityViewModels()
    private lateinit var mBinding: FragmentCategoryBinding
    private lateinit var mAdapter:CategoryListAdaptor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        mBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        mAdapter = CategoryListAdaptor(mViewModel)
        mBinding.categoryList.adapter = mAdapter
        mBinding.backToMain.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_nav_home)
        }
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.makeListFromItem.setOnClickListener {
            mViewModel.makeCategoryFromCurrentList()
        }
        mAdapter.notifyDataSetChanged()
    }
}
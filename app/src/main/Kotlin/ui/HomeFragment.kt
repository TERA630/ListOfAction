package io.terameteo.listofaction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.flexbox.*
import io.terameteo.listofaction.*
import io.terameteo.listofaction.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val mViewModel: MainViewModel by activityViewModels()
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mAdaptor: MainListAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val flexBoxLayoutManager = FlexboxLayoutManager(this.context).apply {
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
            justifyContent = JustifyContent.FLEX_START
            alignItems = AlignItems.FLEX_START
        }
        mBinding.homeList.layoutManager = flexBoxLayoutManager

        mAdaptor = MainListAdaptor(
            viewModel = mViewModel,
            mViewModel.getDateStr(mViewModel.currentPage.valueOrZero(), DATE_EN))
        mBinding.homeList.adapter = mAdaptor

//        val textView: TextView = mBinding.textHome
//        textView.setText(R.string.large_text)

        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.currentPage.observe(this.viewLifecycleOwner){
            mBinding.dateShowing.text = mViewModel.getDateStr(it, DATE_JP)
            mAdaptor.dateChange(mViewModel.getDateStr(it, DATE_EN))
        }
        mViewModel.allItemList.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()) return@observe
            mAdaptor.submitList(it)
        }
    }

}
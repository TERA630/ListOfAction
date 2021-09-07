package io.terameteo.listofaction.ui

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter


class DetailArrayAdapter(context: Context,viewId:Int,list:List<String>)
    : ArrayAdapter<String>(context,viewId,list){
    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence): FilterResults {
                return FilterResults()
            }
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                if(results.count >0 ){
                    this@DetailArrayAdapter.notifyDataSetChanged()
                } else {
                    this@DetailArrayAdapter.notifyDataSetInvalidated()
                }
            }
        }
    }
}

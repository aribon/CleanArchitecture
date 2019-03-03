package com.insign.clean

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import fr.insign.testapp.liblist.ListHelper
import fr.insign.testapp.liblist.ListingHelper
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_list1.view.*

class ListActivity : AppCompatActivity() {

    private var orientation : Int = RecyclerView.VERTICAL
    private lateinit var adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        makeList()

        list_fab.setOnClickListener {
            orientation = if (orientation == RecyclerView.VERTICAL) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL
            list_recycler.layoutManager = LinearLayoutManager(baseContext, orientation, false)
            adapter.notifyDataSetChanged()
        }
    }

    private fun makeDataList(nbr: Int) : MutableList<String> {
        val dataList = mutableListOf<String>()
        for (i in 0 until nbr)
            dataList.add("Item : $i")
        return dataList
    }

    private fun makeList() {
        adapter = ListHelper.Builder<String>(this)
                .orientation(ListingHelper.Orientation.VERTICAL)
                .itemType(R.layout.item_list1, bind)
                .data(makeDataList(50))
                .into(list_recycler)
                .get()
    }

    val bind: (View, String) -> Unit = { view, label ->
        view.text.text = label
    }
}

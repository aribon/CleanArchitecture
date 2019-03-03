package com.insign.clean

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import fr.insign.testapp.liblist.ListHelper
import fr.insign.testapp.liblist.ListingHelper
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_list1.view.*
import kotlinx.android.synthetic.main.item_list2.view.*

class MultipleTypeListActivity : AppCompatActivity() {

    private var orientation : Int = RecyclerView.VERTICAL
    private lateinit var adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_mutiple_type_list)
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
            dataList.add("$i")
        return dataList
    }

    private fun makeList() {
        adapter = ListHelper.Builder<String>(this)
                .orientation(ListingHelper.Orientation.VERTICAL)
                .itemType(R.layout.item_list1, 1, bind1)
                .itemType(R.layout.item_list2, 2, bind2)
                .findType(findType)
                .data(makeDataList(50))
                .into(list_recycler)
                .get()
    }

    val bind1: (View, String) -> Unit = { view, label ->
        view.text.text = label
    }

    val bind2: (View, String) -> Unit = { view, label ->
        view.setBackgroundColor(Color.parseColor("#333333"))
        view.text1.setTextColor(Color.WHITE)
        view.text2.setTextColor(Color.WHITE)
        view.text1.text = "Item : $label"
        view.text2.text = "Description : Lorem ipsum dolor sit amet ..."
    }

    val findType: (Int, String) -> Int = { position: Int, label: String ->
        if (position == 0 || position % 10 == 0) 2 else 1
    }
}

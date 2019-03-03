package fr.insign.testapp.liblist

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

/**
 * Created by anthony.ribon
 * On 22/08/2018
 */

class ListHelper<T> : ListingHelper<T> {

    constructor(
            context: Context,
            orientation: Orientation,
            itemTypes: MutableList<ItemType<T>>,
            findType: ((Int, T) -> Int)?,
            data: MutableList<T>?,
            refresh: ((ListingHelper.OnRefreshListener<T>) -> Unit)?,
            list: RecyclerView?)
            : super(context, orientation, itemTypes, findType, data, refresh, list)

    constructor(builder: Builder<T>) : super(builder)

    init {
        make()
    }

    override fun make(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        if (list is RecyclerView) {
            list.layoutManager = LinearLayoutManager(context, orientation.value(), false)
            list.adapter = adapter
        } else {
            Toast.makeText(context, "ERROR LIST ADAPTER", Toast.LENGTH_LONG).show()
        }
    }

    class Builder<T>: ListingHelper.Builder<T> {

        constructor(context: Context) : super(context)

        constructor(
                context: Context,
                orientation: Orientation,
                itemTypes: MutableList<ItemType<T>>,
                findType: ((Int, T) -> Int)?,
                data: MutableList<T>?,
                list: RecyclerView?) : super(context) {
            this.data = data
            this.orientation = orientation
            this.itemTypes = itemTypes
            this.findType = findType
            this.list = list
        }

        constructor(builder: ListingHelper.Builder<T>) : this(
                builder.context,
                builder.orientation,
                builder.itemTypes,
                builder.findType,
                builder.data,
                builder.list)

        override fun build() = ListHelper(this)
    }
}
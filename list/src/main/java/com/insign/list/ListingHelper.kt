package fr.insign.testapp.liblist

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by anthony.ribon
 * On 21/08/2018
 */

abstract class ListingHelper<T> {

    enum class Orientation(val orientation: Int) {
        VERTICAL(RecyclerView.VERTICAL),
        HORIZONTAL(RecyclerView.HORIZONTAL);

        fun value() = orientation
    }

    val context: Context
    val orientation: Orientation
    val itemTypes: MutableList<ItemType<T>>?
    val findType: ((Int, T) -> Int)?
    var data: MutableList<T>?
    val refresh: ((ListingHelper.OnRefreshListener<T>) -> Unit)?
    val list: RecyclerView?

    protected val adapter = Adapter()
    protected val layoutManager: RecyclerView.LayoutManager? = null

    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    protected var isLoading = false

    constructor(
            context: Context,
            orientation: Orientation,
            itemTypes: MutableList<ItemType<T>>,
            findType: ((Int, T) -> Int)?,
            data: MutableList<T>?,
            refresh: ((ListingHelper.OnRefreshListener<T>) -> Unit)?,
            list: RecyclerView?) {
        this.context = context
        this.orientation = orientation
        this.itemTypes = itemTypes
        this.findType = findType
        this.data = data
        this.refresh = refresh
        this.list = list
    }

    constructor(builder: Builder<T>) : this(
            builder.context,
            builder.orientation,
            builder.itemTypes,
            builder.findType,
            builder.data,
            builder.refresh,
            builder.list
    )

    var refreshListener = object : OnRefreshListener<T> {
        override fun onRefreshStart() {

        }

        override fun onRefreshCompleted(data: MutableList<T>) {
            this@ListingHelper.data = data
            this@ListingHelper.adapter.notifyDataSetChanged()
            this@ListingHelper.adapter.notifyItemRangeChanged(0, data.size)
            swipeRefreshLayout?.isRefreshing = false
        }

        override fun onRefreshError() {

        }
    }

    fun make() {
        this.makeRefresh()
        this.make(adapter)
    }

    fun makeRefresh() {
        if (refresh != null) {
            val parent = list?.parent

            if (parent is ViewGroup) {
                val indexList = parent.indexOfChild(list)
                parent.removeViewAt(indexList)
                swipeRefreshLayout = SwipeRefreshLayout(context)
                swipeRefreshLayout!!.addView(list)
                swipeRefreshLayout!!.setOnRefreshListener {
                    refresh.invoke(refreshListener)
                }
                parent.addView(swipeRefreshLayout, indexList)
            }
        }
    }

    fun get(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return adapter
    }

    private fun findLayout(viewType: Int): Int {
        for (i in 0 until itemTypes!!.size) {
            if (viewType == itemTypes[i].type)
                return itemTypes[i].layout
        }

        return 0
    }

    protected abstract fun make(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)

    abstract class Builder<T>(open val context: Context) {

        open var orientation: Orientation = Orientation.VERTICAL
            protected set

        open var itemTypes: MutableList<ItemType<T>> = mutableListOf()
            protected set

        open var data: MutableList<T>? = null
            protected set

        open var layout: Int = 0
            protected set

        open var bind: ((itemView: View, data: T) -> Unit)? = null
            protected set

        open var findType: ((position: Int, data: T) -> Int)? = null

        open var refresh: ((refreshListener: OnRefreshListener<T>) -> Unit)? = null

        open var loadMore: ((Any) -> MutableList<T>)? = null

        open var list: RecyclerView? = null
            protected set

        fun orientation(orientation: Orientation) = apply { this.orientation = orientation }

        fun itemType(layout: Int, type: Int, bind: (View, T) -> Unit) = apply { itemTypes.add(ItemType(layout, type, bind)) }

        fun itemType(layout: Int, bind: (View, T) -> Unit) = apply { itemTypes.add(ItemType(layout, 0, bind)) }

        fun findType(findType: ((position: Int, data: T) -> Int)) = apply { this.findType = findType }

        fun data(data: MutableList<T>) = apply { this.data = data }

        fun refresh(refresh: ((refreshListener: OnRefreshListener<T>) -> Unit)?) = apply { this.refresh = refresh }

        fun into(list: RecyclerView?): ListingHelper<T> {
            this.list = list
            return build()
        }

        abstract fun build(): ListingHelper<T>
    }

    data class ItemType<T>(var layout: Int, var type: Int, var bind: (View, T) -> Unit)

    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val layout = findLayout(viewType)
            return object : RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(layout, parent, false)) {}
        }

        override fun getItemCount() = data!!.size

        override fun getItemViewType(position: Int): Int {
            return findType?.invoke(position, data!![position]) ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            for (i in 0 until itemTypes!!.size) {
                if (holder.itemViewType == itemTypes[i].type)
                    itemTypes[i].bind(holder.itemView, data?.get(position)!!)
            }
        }

        fun clearItems() {
            data?.clear()
            notifyDataSetChanged()
        }

        fun addItem(item: T?) {
            data?.add(item!!)
            notifyItemInserted(data!!.size - 1)
        }

        fun removeItem(item: T?) {
            val index = data?.indexOf(item)
            data?.removeAt(index!!)
            notifyItemRemoved(index!!)
        }

        fun removeItem(position: Int) {
            data?.removeAt(position)
            notifyItemRemoved(position)
        }

        fun addBackLoader() {
            addItem(null)
        }
    }

    interface OnRefreshListener<T> {
        fun onRefreshStart()
        fun onRefreshCompleted(data: MutableList<T>)
        fun onRefreshError()
    }
}
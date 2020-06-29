package com.benmohammad.mvvmarchnews.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.benmohammad.mvvmarchnews.repository.api.network.Resource
import com.benmohammad.mvvmarchnews.utils.widget.CompleteRecyclerView

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer {it?.apply(observer)})
}

fun<ResultType> Resource<ResultType>.load(list: CompleteRecyclerView, f: (ResultType?) -> Unit) {
    list.showState(status)
    load(f)
}

fun <ResultType> Resource<ResultType>.load(f: (ResultType?) -> Unit) {
    if(!status.isLoading()) {
        f(data)
    }
}
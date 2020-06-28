package com.benmohammad.mvvmarchnews.repository.api.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.benmohammad.mvvmarchnews.app.AppExecutors
import retrofit2.Response

abstract class NetworkAndDBBoundResource<ResultType, RequestType> @MainThread constructor(private val appExecutors: AppExecutors) {


    private val result = MediatorLiveData<Resource<ResultType?>>()

    init {
        result.value = Resource.loading()
        val dbSource = this.loadFromDb()

        result.addSource(dbSource) {
            data -> result.removeSource(dbSource)

            if(shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) {
                    newData -> setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) {
            result.setValue(Resource.loading())
        }

        result.addSource(apiResponse) {
            response ->
                result.removeSource(dbSource)
                result.removeSource(apiResponse)

                response?.apply {
                    if(status.isSuccessful()) {
                        appExecutors.diskIO().execute{
                            processResponse(this)?.let {
                                requestType -> saveCallResult(requestType)
                            }
                            appExecutors.mainThread().execute{
                                result.addSource(loadFromDb()) {
                                    newValue ->
                                    setValue(Resource.success(newValue))
                                }
                            }
                        }
                    } else {
                        onFetchFailed()
                        result.addSource(dbSource) {
                            result.setValue(Resource.error(errorMessage))
                        }
                    }
                }
            }
        }


    @MainThread
    private fun setValue(newValue: Resource<ResultType?>) {
        if(result.value != newValue) result.value = newValue
    }

    protected fun onFetchFailed(){}

    fun asLIveData(): LiveData<Resource<ResultType?>> {
        return result
    }

    @WorkerThread
    private fun processResponse(response: Resource<RequestType>): RequestType? {
        return response.data
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<Resource<RequestType>>
}
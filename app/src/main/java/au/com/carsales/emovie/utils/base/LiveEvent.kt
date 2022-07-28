package au.com.carsales.emovie.utils.base

import androidx.lifecycle.*

/**
 * Emit events when the observation detect a change.
 * This class allows you to associate with an external MutableLiveData.
 */
class LiveEvent<T>(private val source: MutableLiveData<T> = MutableLiveData<T>()) {

    /**
     * MediatorLiveData - Used to handle async values by postValue
     */
    private val mediator = MediatorLiveData<Event<T>>()
    private var version = 0L
    private var skipFirstValue = false

    init {
        skipFirstValue = source.value != null

        mediator.addSource(source) {
            mediator.value = Event(it, version + 1)
            version++
            skipFirstValue = false
        }
    }

    var value: T?
        set(value) {
            source.value = value
        }
        get() = source.value

    fun postValue(value: T) {
        source.postValue(value)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        mediator.observe(owner, Observer<Event<T>> { t ->
            if (!skipFirstValue && t?.version != version)
                observer.onChanged(t?.value)
        })
    }

    /**
     * Notify all observers of a change (Optional)
     */
    fun triggerEvents() {
        source.value = source.value
    }

    private data class Event<T>(val value: T, val version: Long)
}
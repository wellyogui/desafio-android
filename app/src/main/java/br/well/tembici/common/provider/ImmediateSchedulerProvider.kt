package br.well.tembici.common.provider

import br.well.tembici.common.provider.BaseSchedulerProvider
import rx.Scheduler
import rx.schedulers.Schedulers

class ImmediateSchedulerProvider :
    BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}

package com.amir.neosofttest.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.amir.neosofttest.model.StatsMo

class StaticsScreenScreenController : TypedEpoxyController<List<String>>() {
    override fun buildModels(userData: List<String>?) {

        if (userData == null) return


        HeaderEpoxyModel("All Data").id(
            "all_Data" + ""
        ).addTo(this)


        userData.forEach {
            StaticsScreenEpoxyModel(it).id(it).addTo(this)
        }
        HeaderEpoxyModel("Statics(Top 3 characters Occurrence)").id("Stats").addTo(this)

        val stringBuilder = StringBuilder()

        userData.forEach {
            stringBuilder.append(it.toLowerCase())
        }
        val filter = stringBuilder.toString().toCharArray().groupBy { it }

        val list = filter.toSortedMap().map {
            StatsMo(
                ch = it.key.toString(), count = it.value.size
            )
        }.sortedBy {
            it.count
        }.reversed()

        repeat(3) {
            val model = list[it]
            StaticsScreenEpoxyModel("${model.ch} = ${model.count}").id(model.ch).addTo(this)

        }
    }

}
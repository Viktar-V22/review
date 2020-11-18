package com.domain.irregular

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.irregulars.Irregular
import com.boundaries.irregulars.IrregularsRepository
import com.core.common.Result
import com.core.common.Result.NONE
import com.core.common.TrainType.IRREGULAR
import com.core.common.isPositive
import com.interactors.irregular.InputField
import com.interactors.irregular.InputField.*
import com.interactors.irregular.State
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList

class StateCaseImpl @Inject constructor(
    private val irregulars: IrregularsRepository,
    private val images: ImagesRepository,
    private val results: TrainsResultRepository,
    private val settings: TrainsSettingsStore
) : StateCase {

    private val allVerbs = ArrayList<Irregular>()
    private val verbs = ArrayDeque<Irregular>()
    private val typed = Hashtable<InputField, String>()
    private var verified = false

    override suspend fun state(): State {
        if (allVerbs.isEmpty()) prepare()

        return if (verbs.isEmpty()) State.Completed else {
            val base = verbs.first()
            val ru = base.ru.joinToString(", ")
            val image = images.imageUrl(base.ru)

            if (verified) {
                val verb = correct()

                State.Irregular(
                    image,
                    ru,
                    verb.infinitive.verb,
                    verb.past.verb,
                    verb.pp.verb,
                    result(typed(INFINITIVE), verb.infinitive.verb),
                    result(typed(PAST), verb.past.verb),
                    result(typed(PAST_PARTICIPLE), verb.pp.verb),
                    verb.infinitive.transcription,
                    verb.past.transcription,
                    verb.pp.transcription,
                    verified
                )
            } else State.Irregular.notVerified(ru, image)
        }
    }

    override fun isMute() = settings.isMute(IRREGULAR)

    override suspend fun checkOrNext(infinitive: String, past: String, pp: String): Boolean {
        if (verified) {
            if (verbs.isNotEmpty()) {
                val correct = correct()
                val base = verbs.first()

                val isCorrect = isTypedCorrect()
                results.store(correct.id, IRREGULAR, Result.fromBoolean(isCorrect))

                if (isCorrect) {
                    verbs.remove(correct)
                    if (correct != base) verbs.remove(base)
                } else verbs.shuffle()
            }
            typed.clear()
        } else typed.let {
            it[INFINITIVE] = infinitive
            it[PAST] = past
            it[PAST_PARTICIPLE] = pp
        }
        verified = !verified
        return verified
    }

    override fun forSpeech(field: InputField) = if (verbs.isEmpty()) "" else {
        val correct = correct()

        when (field) {
            INFINITIVE -> correct.infinitive.verb
            PAST -> correct.past.verb
            PAST_PARTICIPLE -> correct.pp.verb
        }
    }

    private suspend fun prepare() {
        irregulars.prepare() // TODO
        images.prepare() // TODO

        val count = settings.count(IRREGULAR)
        val ids = results.minResult(IRREGULAR, count)

        val verbs = irregulars.verbs()
        this.verbs.clear()
        this.verbs.addAll(verbs.filter { ids.contains(it.id) }.shuffled())

        allVerbs.clear()
        allVerbs.addAll(verbs)
    }

    private fun result(typed: String, correct: String) = if (!verified) NONE else
        Result.fromBoolean(typed == correct)

    private fun isTypedCorrect(): Boolean {
        val correct = correct()

        return result(typed(INFINITIVE), correct.infinitive.verb).isPositive() &&
                result(typed(PAST), correct.past.verb).isPositive() &&
                result(typed(PAST_PARTICIPLE), correct.pp.verb).isPositive()
    }

    private fun correct(): Irregular {
        val base = verbs.first()
        val infTyped = typed(INFINITIVE)

        return if (infTyped != base.infinitive.verb) {
            allVerbs.firstOrNull {
                it.ru.intersect(base.ru).isNotEmpty() && it.infinitive.verb == infTyped
            } ?: base

        } else base
    }

    private fun typed(field: InputField) = typed[field] ?: ""
}
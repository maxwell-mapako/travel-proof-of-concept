package za.co.discovery.sample.data

import za.co.discovery.sample.R
import za.co.discovery.sample.domain.Content

object SampleInteractor {
    fun getContentList(size: Int): List<Content> {
        val imageListSequence = generateSequence {
            listOf(
                R.drawable.british_airways,
                R.drawable.flysafair,
                R.drawable.airlink,
                R.drawable.lift,
                R.drawable.travelit
            )
        }

        return imageListSequence
            .mapIndexed { index, list ->
                if (index != 0)
                    list.shuffled()
                else list
            }
            .flatten()
            .map { Content(it) }
            .take(size)
            .toList()
    }
}
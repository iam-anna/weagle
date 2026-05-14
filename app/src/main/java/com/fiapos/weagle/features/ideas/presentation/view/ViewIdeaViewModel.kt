package com.fiapos.weagle.features.ideas.presentation.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.domain.models.IdeaStatus
import com.fiapos.weagle.domain.models.IdeaType
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import java.time.LocalDate

class ViewIdeaViewModel(
    private val repository: IdeaRepository,
    private val ideaId: String
): ViewModel() {

    var idea by mutableStateOf<Idea?>(
        null
    )
        private set

    var votes by mutableStateOf(0)
        private set

    init {
        loadIdea()
    }

    private fun loadIdea() {
//        idea = repository.getIdeaId(ideaId)
        idea = Idea(
            id = "1234",
            title = "Mock da Ideia",
            description = "Descrição da ideia lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem.",
            type = IdeaType.IDEA,
            status = IdeaStatus.PENDING,
            createdBy = "John Doe",
            isEdited = true,
            createdAt = LocalDate.now(),
            votes = 2
        )

        votes = idea?.votes ?: 0
    }

    fun upvoteIdea() {
        votes ++
    }

    fun downvoteIdea() {
        votes --
    }
}
package com.example.hiroki_kawada.githubrepositories.presenters.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiroki_kawada.githubrepositories.model.usecase.GitHubApiUseCase
import com.example.hiroki_kawada.githubrepositories.model.usecase.UseCaseResult
import kotlinx.coroutines.launch

/**
 * Repository一覧、ReadMeDialogFragment関連ViewModel
 */
class RepositoriesViewModel(
    private val gitHubApiUseCase: GitHubApiUseCase
) : ViewModel() {

    private val _listItems = MutableLiveData<MutableList<String>>()
    val listItems: LiveData<MutableList<String>> = _listItems

    private val _oneTimeEvent = MutableLiveData<OneTimeEvent>()
    val oneTimeEvent: LiveData<OneTimeEvent> = _oneTimeEvent

    var htmlUrl: String = ""

    fun getGitHubRepositoryList() {
        viewModelScope.launch {
            when (val useCaseResult = gitHubApiUseCase.getGitHubRepositoryList()) {
                is UseCaseResult.Success -> {
                    val repositoryTitleList: MutableList<String> = mutableListOf()
                    useCaseResult.value.forEach {
                        repositoryTitleList.add(it.itemTitle)
                    }
                    _listItems.postValue(repositoryTitleList)
                }
                is UseCaseResult.Failure -> {
                    _oneTimeEvent.postValue(OneTimeEvent.ShowSnackBar(useCaseResult.value))
                }
            }
        }
    }

    fun tapListItem(title: String) {
        viewModelScope.launch {
            when (val useCaseResult = gitHubApiUseCase.getReadme(title)) {
                is UseCaseResult.Success -> {
                    htmlUrl = useCaseResult.value
                    _oneTimeEvent.postValue(OneTimeEvent.ShowReadMeDialog)
                }
                is UseCaseResult.Failure -> {
                    _oneTimeEvent.postValue(OneTimeEvent.ShowSnackBar(useCaseResult.value))
                }
            }
        }
    }

}

sealed class OneTimeEvent {

    class ShowSnackBar(val message: String) : OneTimeEvent()

    object ShowReadMeDialog : OneTimeEvent()

}

package com.hammer.talkbbokki.presentation.comment

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hammer.talkbbokki.data.local.cache.UserInfoCache
import com.hammer.talkbbokki.domain.model.CommentRequest
import com.hammer.talkbbokki.domain.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildCommentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: CommentRepository,
    private val userInfoCache: UserInfoCache,
) : ViewModel() {
    val parentComment = savedStateHandle.get<CommentModel>("comment")
    private val _parentCommentId = parentComment?.id
    private val _topicId = parentComment?.topicId
    private var _nextPageId: Int? = null
    private val totalCommentList: MutableList<CommentModel> = mutableListOf()
    private val _commentItems: MutableStateFlow<List<CommentModel>> = MutableStateFlow(listOf())
    val commentItems: StateFlow<List<CommentModel>> get() = _commentItems

    private val _showDeleteDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> get() = _showDeleteDialog.asStateFlow()

    init {
        getChildComments()
    }

    private fun getChildComments() {
        _topicId ?: return
        _parentCommentId ?: return

        viewModelScope.launch {
            repository.getChildCommentList(
                topicId = _topicId,
                parentCommentId = _parentCommentId,
                next = _nextPageId,
            ).catch {
            }.collect {
                totalCommentList.clear()
                totalCommentList.addAll(
                    it.result?.contents?.map { it.toModel(userInfoCache) }.orEmpty()
                        .sortedBy { it.id },
                )
                _commentItems.value = totalCommentList.toList()
                _nextPageId = it.result?.next
            }
        }
    }

    fun postComment(body: String) {
        _topicId ?: return

        viewModelScope.launch {
            repository.postComment(
                _topicId,
                CommentRequest(
                    body = body,
                    userId = userInfoCache.id,
                    parentCommentId = _parentCommentId,
                ),
            ).catch {
                Log.e("@@@", "${it.message}")
            }.collect {
                Log.e("@@@", it.toString() + "test")
                getChildComments()
            }
        }
    }

    fun showDeleteDialog() {
        _showDeleteDialog.value = true
    }

    fun closeDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun deleteComment(comment: CommentModel) {
        viewModelScope.launch {
            repository.deleteComment(
                comment.id,
            ).catch { }.collect {
                _showDeleteDialog.value = false
                getChildComments()
                Log.e("@@@", it.toString() + "test")
            }
        }
    }
}

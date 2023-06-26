package com.hammer.talkbbokki.presentation.comment

import android.net.Uri
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.hammer.talkbbokki.R
import com.hammer.talkbbokki.ui.theme.Gray05
import com.hammer.talkbbokki.ui.theme.Gray06
import com.hammer.talkbbokki.ui.theme.MainBackgroundColor
import com.hammer.talkbbokki.ui.theme.MainColor01
import com.hammer.talkbbokki.ui.theme.TalkbbokkiTypography
import com.hammer.talkbbokki.ui.theme.Transparent
import com.hammer.talkbbokki.ui.theme.White
import kotlinx.parcelize.Parcelize

@Composable
fun CommentsRoute(
    onBackClick: () -> Unit,
    onRecommentClick: (Int, Int) -> Unit,
    onReportClick: (CommentModel) -> Unit,
    viewModel: CommentsViewModel = hiltViewModel(),
) {
    val comments = viewModel.commentItems.collectAsState()
    CommentsScreen(
        comments = comments.value,
        onBackClick = { onBackClick() },
        onRecommentClick = {
            onRecommentClick(viewModel.selectedTopicId, it)
        },
        onClickPostComment = { viewModel.postComment(it) },
        onReportClick = {
            onReportClick(it)
        },
        onDeleteClick = {
            viewModel.deleteComment(it)
        },
    )
}

@Composable
fun CommentsScreen(
    onBackClick: () -> Unit,
    onRecommentClick: (Int) -> Unit,
    onClickPostComment: (String) -> Unit,
    onReportClick: (CommentModel) -> Unit,
    onDeleteClick: (CommentModel) -> Unit,
    comments: List<CommentModel>,
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MainBackgroundColor),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 78.dp),
        ) {
            CommentsHeader(onBackClick)
            if (comments.isEmpty()) {
                CommentEmpty()
            } else {
                CommentList(
                    comments = comments,
                    onRecommentClick = { onRecommentClick(it) },
                    onReportClick = { onReportClick(it) },
                    onDeleteClick = { onDeleteClick(it) },
                )
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            CommentInputArea { onClickPostComment(it) }
        }
    }
}

@Composable
fun CommentsHeader(
    onBackClick: () -> Unit,
) {
    val replyCount = 0
    Box(
        Modifier
            .height(56.dp)
            .fillMaxWidth(),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            tint = White,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onBackClick()
                }
                .align(Alignment.CenterStart)
                .padding(start = 20.dp),
        )
        Text(
            text = "댓글 ($replyCount)",
            Modifier.align(Alignment.Center),
            color = White,
        )
    }
}

@Composable
fun CommentItem(
    comment: CommentModel,
    onDeleteClick: () -> Unit,
    onRecommentClick: (Int) -> Unit,
    onReportClick: (CommentModel) -> Unit,
) {
    val isMine = false
    Column(modifier = Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = comment.nickname,
                style = TalkbbokkiTypography.b3_bold,
                color = if (isMine) MainColor01 else White,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = comment.date, style = TalkbbokkiTypography.caption, color = Gray06)
        }
        if (isMine) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                tint = White,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onDeleteClick()
                    }
                    .align(Alignment.End),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = comment.content, style = TalkbbokkiTypography.b3_regular, color = White)
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (comment.replyCount == 0) {
                    stringResource(R.string.recomment_button)
                } else {
                    stringResource(R.string.recommnet_count, comment.replyCount)
                },
                style = TalkbbokkiTypography.caption,
                color = Gray06,
                modifier = Modifier.clickable {
                    onRecommentClick(comment.id)
                },
            )
            Spacer(modifier = Modifier.width(12.dp))
            if (!isMine) {
                Text(
                    text = stringResource(R.string.report_comment_button),
                    modifier = Modifier.clickable {
                        onReportClick(comment)
                    },
                    style = TalkbbokkiTypography.caption,
                    color = Gray06,
                )
            }
        }
    }
}

@Composable
fun CommentEmpty() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_comment_none),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 106.dp, height = 116.dp)
                    .padding(bottom = 16.dp),
            )
            Text(
                text = "아직 등록된 댓글이 없어요\n" +
                    "첫 번째 댓글을 달아보세요",
                style = TalkbbokkiTypography.b2_bold,
                color = Gray05,
            )
        }
    }
}

@Composable
fun CommentList(
    comments: List<CommentModel>,
    onRecommentClick: (Int) -> Unit,
    onReportClick: (CommentModel) -> Unit,
    onDeleteClick: (CommentModel) -> Unit,
) {
    LazyColumn {
        itemsIndexed(comments) { idx, comment ->
            CommentItem(
                comment = comment,
                onDeleteClick = { onDeleteClick(comment) },
                onRecommentClick = { onRecommentClick(it) },
                onReportClick = { onReportClick(it) },
            )
        }
    }
}

@Composable
fun CommentInputArea(
    onClickPostComment: (String) -> Unit,
) {
    val commentText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.run {
            fillMaxWidth()
                .defaultMinSize(minHeight = 78.dp)
                .background(MainBackgroundColor)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray06, RoundedCornerShape(8.dp)),
        ) {
            TextField(
                modifier = Modifier
                    .weight(5f)
                    .defaultMinSize(minHeight = 46.dp),
                value = commentText.value,
                onValueChange = {
                    commentText.value = it
                },
                textStyle = TalkbbokkiTypography.b2_regular,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = White,
                    disabledTextColor = Transparent,
                    backgroundColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent,
                    cursorColor = MainColor01,
                ),
            )
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onClickPostComment(commentText.value)
                        commentText.value = ""
                    }.align(Alignment.CenterVertically)
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        end = 12.dp,
                    ),
                text = stringResource(R.string.comment_send_button),
                style = TalkbbokkiTypography.b2_bold,
                color = White,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Parcelize
data class CommentModel(
    val id: Int,
    val userId: String,
    val nickname: String,
    val date: String,
    val content: String,
    val topicId: Int,
    val replyCount: Int,
) : Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}

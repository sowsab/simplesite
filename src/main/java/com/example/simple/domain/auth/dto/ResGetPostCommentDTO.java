package com.example.simple.domain.auth.dto;

import java.util.List;

import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.post.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResGetPostCommentDTO {
    private List<Post> postList;
    private List<Comment> commentList;

    public static ResGetPostCommentDTO convert(List<PostEntity> postEntityList,
            List<CommentEntity> commentEntitiyList) {
        return ResGetPostCommentDTO.builder()
                .postList(postEntityList.stream()
                        .map(postEntity -> Post.convert(postEntity))
                        .toList())
                .commentList(commentEntitiyList.stream()
                        .map(commentEntity -> Comment.convert(commentEntity))
                        .toList())
                .build();

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Post {

        private Long idx;
        private String title;

        public static Post convert(PostEntity postEntity) {
            return Post.builder()
                    .idx(postEntity.getIdx())
                    .title(postEntity.getTitle())
                    .build();
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Comment {

        private Long idx;
        private String content;
        private Long postIdx;

        public static Comment convert(CommentEntity commentEntity) {
            return Comment.builder()
                    .idx(commentEntity.getIdx())
                    .content(commentEntity.getContent())
                    .postIdx(commentEntity.getPostEntity().getIdx())
                    .build();
        }

    }
}

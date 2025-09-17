<template>
    <div class="post-list">
        <div class="post" v-for="post in samplePosts" :key="post.postId">
            <div class="post-header">
                <strong>{{ post.user.name }}</strong> - <small>{{ post.createdAt }}</small>
            </div>
            <p>{{ post.content }}</p>
            <img v-if="post.image" :src="post.image" alt="post image" class="post-image" />

            <div class="comments">
                <div class="comment" v-for="comment in post.comments" :key="comment.commentId">
                    <span><strong>{{ comment.user.name }}</strong>:</span> {{ comment.content }}
                </div>
            </div>

            <div class="add-comment">
                <input placeholder="Write a comment..." />
                <button>Comment</button>
            </div>
        </div>
    </div>
</template>

<script setup>
const samplePosts = [
    {
        postId: 1,
        user: { name: 'UserA' },
        content: 'This is a sample post.',
        image: 'https://via.placeholder.com/150',
        createdAt: '2025-09-17 15:00',
        comments: [
            { commentId: 1, user: { name: 'UserB' }, content: 'Nice post!' },
            { commentId: 2, user: { name: 'UserC' }, content: 'Thanks for sharing.' }
        ]
    },
    {
        postId: 2,
        user: { name: 'UserD' },
        content: 'Another example post.',
        image: null,
        createdAt: '2025-09-17 14:30',
        comments: []
    }
];
</script>

<style scoped>
.post {
    margin-bottom: 2rem;
    border-bottom: 1px solid #ccc;
    padding-bottom: 1rem;
}

.post-image {
    max-width: 300px;
    margin-top: 0.5rem;
}

.comments {
    margin-top: 1rem;
    margin-left: 1rem;
}

.comment {
    margin-bottom: 0.5rem;
}

.add-comment {
    margin-top: 1rem;
}

input {
    padding: 0.4rem;
    width: 70%;
    margin-right: 0.5rem;
}

button {
    padding: 0.4rem 1rem;
}
</style>

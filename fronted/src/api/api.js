import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://後端伺服器地址/api', // 替換為後端實際地址
  headers: {
    'Content-Type': 'application/json',
  },
});

export default {
  // 註冊
  register(userData) {
    return apiClient.post('/register', userData);
  },
  // 登入
  login(credentials) {
    return apiClient.post('/login', credentials);
  },
  // 取得所有發文
  getPosts() {
    return apiClient.get('/posts');
  },
  // 新增發文
  createPost(postData) {
    return apiClient.post('/posts', postData);
  },
  // 編輯發文
  updatePost(postId, postData) {
    return apiClient.put(`/posts/${postId}`, postData);
  },
  // 刪除發文
  deletePost(postId) {
    return apiClient.delete(`/posts/${postId}`);
  },
  // 新增留言
  addComment(postId, commentData) {
    return apiClient.post(`/posts/${postId}/comments`, commentData);
  },
  // 取得留言
  getComments(postId) {
    return apiClient.get(`/posts/${postId}/comments`);
  },
};

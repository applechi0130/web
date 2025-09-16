package com.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.member.dto.RegisterRequest;
import com.member.entity.User;

@Service
public interface UserService {
	
	// 根據註冊請求進行用戶註冊，包含檢查重複資料、密碼加密，並將新用戶資料儲存到資料庫，回傳已註冊的User物件
	User registerUser(RegisterRequest request);

	// 	透過傳入的User實體新增用戶，直接將用戶資料儲存到資料庫並回傳持久化的User。
	User createUser(User user);

	// 根據主鍵ID查詢用戶，回傳Optional<User>，結果可能為空（用戶不存在）。方便避免NullPointerException。
	Optional<User> getUserById(Integer id);
	Optional<User> getUserByPhone(String phone);

	// 查詢所有用戶資料，回傳User物件的列表。通常用於管理後台顯示或批次處理。
	List<User> getAllUsers();

	// 根據ID找到目標User，將傳入的User中新的欄位更新到已有User，再存回資料庫，並回傳更新完成的User。如果找不到目標可拋出自訂異常。
	User updateUser(Integer id, User user);

	// 	根據ID刪除指定用戶。若用戶不存在可拋出異常，也可設計為無操作。主要用途是移除資料庫中的該筆用戶資料
	void deleteUserById(Integer id);
}

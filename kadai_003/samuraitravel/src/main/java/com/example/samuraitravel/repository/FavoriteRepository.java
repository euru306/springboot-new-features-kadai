package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    // ユーザーと民宿の組み合わせが存在するか
    boolean existsByUserAndHouse(User user, House house);

    // ユーザーと民宿の組み合わせを削除
    void deleteByUserAndHouse(User user, House house);

    // 指定ユーザーのお気に入りリスト（全件）
    List<Favorite> findByUser(User user);

    // ✅ 指定ユーザーのお気に入りリスト（ページング）
    Page<Favorite> findByUser(User user, Pageable pageable);
}

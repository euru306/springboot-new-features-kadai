package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByUserAndHouse(User user, House house);
    void deleteByUserAndHouse(User user, House house);
    List<Favorite> findByUser(User user);
}

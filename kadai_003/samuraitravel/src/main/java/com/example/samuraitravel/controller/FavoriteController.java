package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.FavoriteService;
import com.example.samuraitravel.service.HouseService;

@Controller
@RequestMapping("/favorites")  // ここを追加
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final HouseService houseService;

    public FavoriteController(FavoriteService favoriteService, HouseService houseService) {
        this.favoriteService = favoriteService;
        this.houseService = houseService;
    }

    // お気に入り一覧表示
    @GetMapping
    public String favorites(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        User user = userDetails.getUser();
        List<Favorite> favorites = favoriteService.getFavoritesByUser(user);
        model.addAttribute("favorites", favorites);
        return "favorites/list";
    }

    // お気に入り追加
    @PostMapping("/add/{houseId}")
    public String addFavorite(@PathVariable Integer houseId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User loginUser = userDetails.getUser();
        House house = houseService.findById(houseId);
        favoriteService.addFavorite(loginUser, house);

        return "redirect:/houses/" + houseId;
    }

    // お気に入り削除
    @PostMapping("/remove/{houseId}")
    public String removeFavorite(@PathVariable Integer houseId,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User loginUser = userDetails.getUser();
        House house = houseService.findById(houseId);
        favoriteService.removeFavorite(loginUser, house);

        return "redirect:/houses/" + houseId;
    }
}

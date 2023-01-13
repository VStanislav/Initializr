package com.voronkov.Initializr.api;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.dto.ProductDto;
import com.voronkov.Initializr.entity.User;
import com.voronkov.Initializr.exceptions.ResourceNotFoundException;
import com.voronkov.Initializr.service.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class User_Controller {
    private final UserServise userServise;

    @GetMapping("/{name}")
    public User findByName(@PathVariable String name) {
        return userServise.findByUsername(name).orElseThrow(() ->
                new ResourceNotFoundException("Пользователь не найден с таким именем " + name));
    }

    @DeleteMapping("/{name}")
    public String deleteByName(@PathVariable String name) {
        userServise.deleteBy(name);
        return "Удален пользователь с именем "+name;
    }

    @GetMapping
    public List<User> findAll() {
        return userServise.findAll();
    }

}

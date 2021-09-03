package ru.ponies.pink.web;

import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class CrudController<ID, T, R> {

    public abstract ResponseEntity<List<R>> findAll();

    public abstract ResponseEntity<R> create(T create);

    public abstract ResponseEntity<R> update(T update);

    public abstract ResponseEntity<R> patch(T update);

    public abstract ResponseEntity<R> get(ID id);

//    ROLE_ADMIN,
//    ROLE_OPERATOR,
//    ROLE_SHOP_MANAGER,
//    ROLE_MANAGER


}

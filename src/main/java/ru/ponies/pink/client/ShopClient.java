package ru.ponies.pink.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ponies.pink.client.dto.GoodDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "shop-mock-client", url = "localhost:8081")
@RequestMapping("/mock/goods")
public interface ShopClient {

    @GetMapping("/{goodId}")
    ResponseEntity<GoodDto> getGoodById(@PathVariable("goodId") UUID goodId);

    @GetMapping
    ResponseEntity<List<GoodDto>> getAllGoods();

    @PostMapping
    ResponseEntity<UUID> createGood(@RequestBody GoodDto good);

    @PutMapping
    ResponseEntity<GoodDto> updateGood(@RequestBody GoodDto good);
}

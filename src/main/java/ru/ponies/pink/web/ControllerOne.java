package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ponies.pink.domain.entity.EntityOne;
import ru.ponies.pink.service.ServiceOne;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.ServiceTwo;
import ru.ponies.pink.web.mapper.MapperDtoOne;
import ru.ponies.pink.web.mapper.MapperDtoThree;
import ru.ponies.pink.web.mapper.MapperDtoTwo;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/one")
public class ControllerOne {
    private final ServiceOne serviceOne;
    private final ServiceTwo serviceTwo;
    private final ServiceThree serviceThree;

    private final MapperDtoOne mapperDtoOne;
    private final MapperDtoTwo mapperDtoTwo;
    private final MapperDtoThree mapperDtoThree;

    @GetMapping
    public ResponseEntity get(@RequestParam UUID id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity update(@RequestBody EntityOne entityOne) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody EntityOne entityOne) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam UUID id) {
        return ResponseEntity.ok().build();
    }

}

package ru.ponies.pink.web;

import ru.ponies.pink.domain.entity.EntityTwo;
import ru.ponies.pink.service.*;
import ru.ponies.pink.web.mapper.MapperDtoOne;
import ru.ponies.pink.web.mapper.MapperDtoThree;
import ru.ponies.pink.web.mapper.MapperDtoTwo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/two")
public class ControllerTwo {
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
    public ResponseEntity update(@RequestBody EntityTwo entityTwo) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody EntityTwo entityTwo) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam UUID id) {
        return ResponseEntity.ok().build();
    }
}

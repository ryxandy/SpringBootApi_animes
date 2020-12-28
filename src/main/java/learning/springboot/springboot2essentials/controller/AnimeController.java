package learning.springboot.springboot2essentials.controller;

import learning.springboot.springboot2essentials.requests.AnimePostRequestBody;
import learning.springboot.springboot2essentials.requests.AnimePutRequestBody;
import learning.springboot.springboot2essentials.service.AnimeService;
import learning.springboot.springboot2essentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import learning.springboot.springboot2essentials.domain.Anime;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
//RequiredterArgs creates a constructor in final fields
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeservice;

    @GetMapping
    public ResponseEntity<List<Anime>> List(){
        log.info(dateUtil.FormatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return  ResponseEntity.ok(animeservice.ListAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime>FindById (@PathVariable long id) {
        return ResponseEntity.ok(animeservice.findByIdOrThrowBadRequestException(id));
    }


    @PostMapping
    public  ResponseEntity<Anime> Save(@RequestBody AnimePostRequestBody animePostRequestBody){
        log.info("Item adicionado com sucesso");
        return new ResponseEntity<>(animeservice.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<Void> Delete(@PathVariable long id){
        animeservice.Delete(id);
        log.info("Item deletado com sucesso");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public  ResponseEntity<Anime> Update(@RequestBody AnimePutRequestBody animePutRequestBody){
        animeservice.Update(animePutRequestBody);
        log.info("Item substituído com sucesso");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

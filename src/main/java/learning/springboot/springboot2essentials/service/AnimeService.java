package learning.springboot.springboot2essentials.service;

import learning.springboot.springboot2essentials.domain.Anime;
import learning.springboot.springboot2essentials.repository.AnimeRepository;
import learning.springboot.springboot2essentials.requests.AnimePostRequestBody;
import learning.springboot.springboot2essentials.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {




    private final AnimeRepository animeRepository;

    public List<Anime> ListAll(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(Anime.builder().name(animePostRequestBody.getName()).build());
   }

    public void Delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void Update(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .name(animePutRequestBody.getName())
         .build();

        animeRepository.save(anime);

   }
}

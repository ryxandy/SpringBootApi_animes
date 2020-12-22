package learning.springboot.springboot2essentials.service;

import learning.springboot.springboot2essentials.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {

   private List<Anime> animes = List.of(new Anime(1L,"Naruto"),new Anime(2L,"Boruto"), new Anime(3L,"Shingeki No Kyojin"),new
           Anime(4L,"Hunter x Hunter"));

    public List<Anime> ListAll(){
        return animes;
    }

    public Anime findById(long id) {
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

}

package learning.springboot.springboot2essentials.repository;


import learning.springboot.springboot2essentials.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {


}

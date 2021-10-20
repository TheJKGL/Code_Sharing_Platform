package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.SharingCode;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<SharingCode, String> {
    Optional<SharingCode> findSharingCodesById(String id);

    List<SharingCode> findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(int time, int views);

}

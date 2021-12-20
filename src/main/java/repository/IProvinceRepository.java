package repository;

import model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, Long> {
    Iterable<Province> findAllByName(String name);
    Iterable<Province> findAllByNameOrderByName(String name);
}

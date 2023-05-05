package kz.springboot.springbootdemo2.repositories;

import jakarta.transaction.Transactional;
import kz.springboot.springbootdemo2.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Countries,Long> {
}

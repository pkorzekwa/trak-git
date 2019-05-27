package pl.pzu.trak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pzu.trak.domain.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}

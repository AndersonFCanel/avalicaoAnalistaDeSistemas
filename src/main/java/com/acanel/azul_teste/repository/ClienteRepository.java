package com.acanel.azul_teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acanel.azul_teste.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAll();

    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    Cliente findOneByCpf(@Param("cpf")String cpf);

}

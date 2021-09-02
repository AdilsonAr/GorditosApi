package com.dev.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Cliente;
import com.dev.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	List<Pedido> findByCliente(Cliente cliente);
	List<Pedido> findByFechaAfterAndFechaBefore(LocalDateTime after, LocalDateTime before);
}

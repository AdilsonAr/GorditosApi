package com.dev.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Pedido;
import com.dev.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer>{
	Venta findByPedido(Pedido pedido);
	List<Venta> findByFechaAfterAndFechaBefore(LocalDateTime despues, LocalDateTime antes);
}

package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dtos.ResultadoResponse;
import com.tienda.models.Boleta;
import com.tienda.models.DetalleBoleta;
import com.tienda.models.Producto;
import com.tienda.repositories.IBoletaRepository;
import com.tienda.repositories.IProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

	@Autowired
	private IBoletaRepository _boletaRepository;
	
	@Autowired
	private IProductoRepository _productoRepository;
	
	public List<Boleta> search() {
		return _boletaRepository.findAllByOrderByNumBoletaDesc();
	}
	
	@Transactional
	public ResultadoResponse create(Boleta boleta) {
		try {
			StringBuilder errores = new StringBuilder();
			
			//Validando que existe stock suficiente del producto
			for (DetalleBoleta item : boleta.getLstDetalleBoleta()) {
				int idProducto = item.getProducto().getIdProducto();
				Producto prod = _productoRepository.findById(idProducto).orElseThrow();
				
				if (prod.getStockActual() < item.getCantidad())
					errores.append(String.format("Stock insuficiente para %s <br>", prod.getNombreProducto()));
			}
			
			if (errores.length() > 0)
				return new ResultadoResponse(false, errores.toString());
			
			//Si se realiza la venta entonces se actualiza el stock actual
			boleta.getLstDetalleBoleta().forEach(detalle -> {
				Producto prod = _productoRepository.findById(detalle.getProducto().getIdProducto()).orElseThrow();
				prod.setStockActual(prod.getStockActual() - detalle.getCantidad());
				_productoRepository.save(prod);
			});
			
			//Registrando la boleta
			Boleta registrado = _boletaRepository.save(boleta);
			String mensaje = String.format("Boleta con número %s registrada", registrado.getNumBoleta());
			return new ResultadoResponse(true, mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error al registrar: " + e.getMessage());
		}
	}
}

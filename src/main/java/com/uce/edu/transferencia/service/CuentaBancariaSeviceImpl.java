package com.uce.edu.transferencia.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.transferencia.repository.ICuentaBancariaRepository;
import com.uce.edu.transferencia.repository.modelo.CuentaBancaria;

@Service
public class CuentaBancariaSeviceImpl implements ICuentaBancariaService {
	@Autowired
	private ICuentaBancariaRepository iCuentaBancariaRepository;

	@Override
	public CuentaBancaria buscar(String numero) {
		// TODO Auto-generated method stub
		return this.iCuentaBancariaRepository.seleccionar(numero);
	}

	@Override
	public void guardar(CuentaBancaria cuentaBancaria) {
		this.iCuentaBancariaRepository.insertar(cuentaBancaria);
		;

	}

	@Override
	public void actualizar(CuentaBancaria cuentaBancaria) {
		// TODO Auto-generated method stub
		this.iCuentaBancariaRepository.actualizar(cuentaBancaria);
	}

	@Override
	public void eliminar(String numero) {
		// TODO Auto-generated method stub
		this.iCuentaBancariaRepository.eliminar(numero);
	}

	@Override
	public void deposito(String numero, BigDecimal monto) {
		
		CuentaBancaria cuentaDeposito = this.buscar(numero); //uwu unu no sirve unu
		BigDecimal desc= new BigDecimal(0.1);
		BigDecimal calculo=monto.multiply(desc);
		BigDecimal montoFinal=monto.subtract(calculo);
		BigDecimal saldoInicio = cuentaDeposito.getSaldo();
		BigDecimal nuevoSaldoDeposito = saldoInicio.add(montoFinal);
		cuentaDeposito.setSaldo(nuevoSaldoDeposito);
		actualizar(cuentaDeposito);
		System.out.println("Deposito realizada con exito");
		
		
	}

}

package com.nttdata.bootcamp.retofuncional.funcion;

import java.util.List;

import com.nttdata.bootcamp.retofuncional.repository.Usuario;

@FunctionalInterface
public interface ConfigurarListaFuncion {
	
	public Usuario listar(Usuario o);
}

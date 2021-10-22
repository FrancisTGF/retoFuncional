package com.nttdata.bootcamp.retofuncional.repository;

import java.util.ArrayList;
import java.util.List;

public class Aula {
	private List<Usuario> usuarioList;

	public Aula() {
		this.usuarioList = new ArrayList<>();
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	@Override
	public String toString() {
		return "Aula [usuarioList=" + usuarioList + "]";
	}
}

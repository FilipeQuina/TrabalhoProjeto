package locadora.model.VO;

import locadora.model.FilmeBluRay;

public class FabricaBluRay implements FabricaDeFilme  {

	@Override
	public Filme criarFilme() {
		return new FilmeBluRay();
	}
	
}

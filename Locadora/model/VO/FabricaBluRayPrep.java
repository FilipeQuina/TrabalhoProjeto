package locadora.model.VO;

import locadora.model.DAO.FilmeBluRayPrep;
import locadora.model.FilmeBluRay;

public class FabricaBluRayPrep implements FabricaDeFilmePrep  {

	@Override
	public FilmePrep criarFilme() {
		return new FilmeBluRayPrep();
	}
	
}

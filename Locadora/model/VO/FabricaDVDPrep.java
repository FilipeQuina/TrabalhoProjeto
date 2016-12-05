package locadora.model.VO;

import locadora.model.DAO.FilmeDVDPrep;

public class FabricaDVDPrep implements FabricaDeFilmePrep {

	@Override
	public FilmePrep criarFilme() {
		return new FilmeDVDPrep();
	}

}

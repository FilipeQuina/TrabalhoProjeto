package locadora.model.VO;

import locadora.model.FilmeDVD;

public class FabricaDVD implements FabricaDeFilme {

	@Override
	public Filme criarFilme() {
		return new FilmeDVD();
	}

}

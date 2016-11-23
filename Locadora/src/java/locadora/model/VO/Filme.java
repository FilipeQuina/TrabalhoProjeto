package locadora.model.VO;

import locadora.model.FilmeBluRay;

public interface Filme {
        void carregaPrep() throws Exception;
	void inserirFilme(FilmeBluRay filme);
        void listarFilme();
}

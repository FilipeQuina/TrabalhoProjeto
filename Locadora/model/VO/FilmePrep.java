package locadora.model.VO;

import java.util.List;
import locadora.model.FilmeBluRay;

public interface FilmePrep {
        void carregaPrep() throws Exception;
	void inserirFilme(FilmeBluRay filme)throws Exception;
        List<FilmeBluRay> listarFilmes()throws Exception;
}

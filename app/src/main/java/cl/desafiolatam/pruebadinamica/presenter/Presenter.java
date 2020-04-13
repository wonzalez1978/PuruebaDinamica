package cl.desafiolatam.pruebadinamica.presenter;

import cl.desafiolatam.pruebadinamica.model.bean.IModel;
import cl.desafiolatam.pruebadinamica.model.bean.PreguntasLista;
import cl.desafiolatam.pruebadinamica.view.fragment.IPresenterView;

public class Presenter implements IPresenterModel, IPresenter{

    IModel iModelPresenter;
    IPresenterView iPresenterView;

    public Presenter(IPresenterView iPresenterView) {
        this.iPresenterView = iPresenterView;
    }


    @Override
    public void getDatosDesdeModel() {
        iModelPresenter.getDato();

    }

    @Override
    public void setModel(IModel iModel) {
        this.iModelPresenter = iModel;

    }

    @Override
    public void notificarPresenter(PreguntasLista preguntasLista) {
        iPresenterView.notificarVista(preguntasLista);

    }
}

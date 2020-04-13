package cl.desafiolatam.pruebadinamica.presenter;

import cl.desafiolatam.pruebadinamica.model.bean.IModel;

public interface IPresenter {

    void getDatosDesdeModel();

    void setModel(IModel iModel);
}

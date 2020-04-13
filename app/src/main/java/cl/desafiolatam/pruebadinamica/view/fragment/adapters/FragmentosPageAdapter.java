package cl.desafiolatam.pruebadinamica.view.fragment.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentosPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentosLista;

    public FragmentosPageAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentos) {
        super(fm);
        this.fragmentosLista = fragmentos;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return this.fragmentosLista.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentosLista.size();
    }
}

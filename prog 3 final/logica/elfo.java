package logica;

public class elfo extends personaje{

    public elfo(String nombre, String apodo, raza raza) {
        super(nombre, apodo, raza);
    }

    @Override
    public double ataque(personaje personaje) {
        double va, ed, pdef;
        va = this.valorAtaque();
        ed = this.efectividadDisparo();
        pdef = personaje.poderDefensa();
        return ((((va * ed) - pdef)/500)/ 4) * 1.05;
    }   
}

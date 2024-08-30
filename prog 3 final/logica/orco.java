package logica;

public class orco extends personaje{

    public orco(String nombre, String apodo, raza raza) {
        super(nombre, apodo, raza);
    }

    @Override
    public double ataque(personaje personaje) {
        double va, ed, pdef;
        va = this.valorAtaque();
        ed = this.efectividadDisparo();
        pdef = personaje.poderDefensa();
        return ((((va * ed) - pdef)/500) / 4) * 1.1;
    }

    
}

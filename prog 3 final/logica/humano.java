package logica;

public class humano extends personaje{

    public humano(String nombre, String apodo, raza raza) {
        super(nombre, apodo, raza);
    }
    
    @Override
    public double ataque(personaje personaje) {
        double va, ed, pdef;
        va = this.valorAtaque();
        ed = this.efectividadDisparo();
        pdef = personaje.poderDefensa();
        return (((va*ed)-pdef)/500) * 2;
    }   
}

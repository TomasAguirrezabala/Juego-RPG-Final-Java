package logica;
import java.util.Random;
import interfaz.acciones;



public abstract class personaje implements acciones{
    
    protected raza raza;
    protected String nombre;
    protected String apodo;
    protected int edad;
    protected int salud = 100;
    protected int velocidad;
    protected int destreza;
    protected int fuerza;
    protected int nivel;
    protected int armadura;

    public personaje(String nombre, String apodo, raza raza) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.raza = raza;

        
        Random rand = new Random();
        this.edad = rand.nextInt(301);
        this.velocidad = rand.nextInt(10) + 1;
        this.destreza = rand.nextInt(5) + 1;
        this.fuerza = rand.nextInt(10) + 1;
        this.nivel = rand.nextInt(10) + 1;
        this.armadura = rand.nextInt(10) + 1;
    }
    


    public int poderDisparo(){
        int poderDisparo = this.destreza * this.fuerza * this.nivel;
        return poderDisparo;
    };
    public int efectividadDisparo(){
        Random random = new Random();
        int efectividadDisparo = random.nextInt(100) + 1;
        return efectividadDisparo;
    };
    public int valorAtaque(){
        int poderDisparo = this.poderDisparo();
        int efectividadDisparo = this.efectividadDisparo();
        int valorAtaque = poderDisparo * efectividadDisparo;
        return valorAtaque;
    };
    public int poderDefensa(){
        int poderDefensa = this.armadura * this.velocidad;
        return poderDefensa;
    }; 


    @Override
    public String toString() {
        String razaStr;

        switch (raza) {
            case orco:
                razaStr = "un poderoso Orco";
                break;
            case humano:
                razaStr = "un bravo Humano";
                break;
            case elfo:
                razaStr = "un mistico Elfo";
                break;
            default:
                razaStr = "un misterioso ser";
        }

        String presentacion = nombre + " AKA " + apodo + " es " + razaStr + "."
                            + " Estadisticas: " + "Vida: " + salud + " Nivel: " + nivel + " Edad: " + edad
                            + " Velocidad: " + velocidad + " Destreza: " + destreza +
                            " Fuerza: " + fuerza + " Armadura: " + armadura;



        return presentacion;}

    protected void actualizarVida(int daño){
        if (daño < 0){
            daño = 0;
        }
        if (daño > 100){
            daño = daño/10;
        }
        salud -= daño;

        if (salud < 0) {
            salud = 0;
        }
    };

    protected int calcularVida(){
        int ataque = 1;
        return ataque;
    };

    // Getters y setters
    public raza getRaza() {
        return raza;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public int getEdad() {
        return edad;
    }

    public int getSalud() {
        return salud;
    }
    
    public int getVelocidad() {
        return velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    

}

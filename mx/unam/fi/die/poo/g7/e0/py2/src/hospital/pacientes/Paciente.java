// Importes
package mx.unam.fi.die.poo.g7.e0.py2.src.hospital.pacientes;

import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.interfaces.AtencionPaciente;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.sistema.Sistema;

public abstract class Paciente implements AtencionPaciente {

  // Atributos protegidos del paciente para que las subclases puedan acceder a ellos directamente
  protected String nombre;
  protected String especialidadAtencion;

  protected boolean enConsulta;
  protected boolean enTratamiento;

  // Constructor
  public Paciente(String nombre, String especialidadAtencion) {
    this.nombre = nombre;
    this.especialidadAtencion = especialidadAtencion;
    this.enConsulta = false;
    this.enTratamiento = false;
  }

  // GETTERS

  // Get que retorna el nombre
  public String getNombre() {
    return nombre;
  }

  public String getEspecialidadAtencion() {
    return especialidadAtencion;
  }

  public boolean estaEnConsulta() {
    return enConsulta;
  }

  public boolean estaEnTratamiento() {
    return enTratamiento;
  }

  // SETTERS

  public void setEnConsulta(boolean enConsulta) {
    this.enConsulta = enConsulta;
  }

  public void setEnTratamiento(boolean enTratamiento) {
    this.enTratamiento = enTratamiento;
  }

  // MÉTODOS

  // Método que registra todo el objeto Paciente llamando al método 'registroPaciente' del sistema con el objeto entero como argumento
  @Override 
  public void registroEnSistema(Sistema sistema) {
    sistema.registroPaciente(this);
  }

  // Método que muestra un mensaje si el usuario está en tratamiento
  @Override 
  public void verTratamiento() {
    if (enTratamiento) {
      System.out.println("El paciente " + nombre + " está en tratamiento.");
    } else {
      System.out.println("El paciente " + nombre + " no está en tratamiento.");
    }
  }

  // Método que muestra un mensaje si el usuario está en consulta
  @Override 
  public void verConsulta() {
    if (enConsulta) {
      System.out.println("El paciente " + nombre + " está en consulta.");
    } else {
      System.out.println("El paciente " + nombre + " no está en consulta.");
    }
  }

  // Método que imprime una solicitud por consulta (abstracto porque va a ser implementado por las subclases)
  @Override 
  public abstract void solicitarConsulta();

  // Sobreescribimos el método toString para mostrar información del paciente (abstracto porque va a ser implementado por las subclases)
  @Override
  public abstract String toString();
}

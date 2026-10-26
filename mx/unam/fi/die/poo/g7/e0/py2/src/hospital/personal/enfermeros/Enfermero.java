// Importes
package mx.unam.fi.die.poo.g7.e0.py2.src.hospital.personal.enfermeros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.interfaces.AtencionEnfermero;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.pacientes.Paciente;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.pacientes.PacienteEspecialidad;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.sistema.Sistema;

public abstract class Enfermero implements AtencionEnfermero {

  protected String nombre;
  protected String cedula;
  protected String especialidad;
  protected int noPacientes;

  // Lista de pacientes siendo cuidados
  protected List<Paciente> listaPacientes;

  // Constructor
  public Enfermero(String nombre, String cedula, String especialidad) {
    this.nombre = nombre;
    this.cedula = cedula;
    this.especialidad = especialidad;
    this.noPacientes = 0;
    this.listaPacientes = new ArrayList<>();
  }

  // GETTERS

  // Get que retorna el nombre
  public String getNombre() {
    return nombre;
  }

  // Get que retorna la especialidad
  public String getEspecialidad() {
    return especialidad;
  }

  // Get que retorna el número de pacientes
  public int getNoPacientes() {
    return noPacientes;
  }

  // Get que retorna la cédula del enfermero
  public String getCedula() {
    return cedula;
  }

  // MÉTODOS

  // Método que registra todo el objeto Enfermero llamando al método 'registroEnfermero' del sistema con el objeto entero como argumento
  @Override
  public void registroEnSistema(Sistema sistema) {
    sistema.registroEnfermero(this);
  }

  // Método que agrega paciente a la lista de pacientes siendo cuidados
  @Override
  public void agregarPaciente(Paciente paciente) {

    // Si el paciente es de tipo PacienteEspecialidad, se procede a verificar la especialidad
    if (paciente instanceof PacienteEspecialidad) {

      // Verificamos si la especialidad del paciente coincide con la del enfermero
      if (paciente.getEspecialidadAtencion().equals(this.especialidad)) {

        // Si todavía no ha alcanzado el máximo de pacientes
        if (this.noPacientes < 3) {

          // Verificamos si ya está en la lista de pacientes
          if (!this.listaPacientes.contains(paciente)) {
            this.listaPacientes.add(paciente);
            this.noPacientes++;
          } 

          // Si ya está en la lista, se imprime un mensaje
          else {
            System.out.println(
              "El paciente " +
                paciente.getNombre() +
                " ya está en la lista del enfermero " +
                nombre +
                "."
            );
          }

        }

        // Si ya alcanzó el máximo de pacientes, se imprime un mensaje
        else {
          System.out.println(
            "El enfermero " +
              nombre +
              " ya tiene el máximo de 3 pacientes a su cargo."
          );
        }
      }

      // Si la especialidad del paciente no coincide con la del enfermero, se imprime un mensaje
      else {
        System.out.println(
          "La especialidad del paciente (" +
            paciente.getEspecialidadAtencion() +
            ") no coincide con la del enfermero (" +
            especialidad +
            ")."
        );
      }
    }

    // Si el paciente no es de tipo PacienteEspecialidad, se imprime un mensaje
    else {
      System.out.println(
        "Los enfermeros solo pueden tener pacientes con especialidad."
      );
    }
  }

  // Método para ver los pacientes del enfermero
  @Override
  public void verListaPacientes() {

    // Ordenamos alfabeticamente de manera descendente 'listaPacientes'
    listaPacientes.sort(Comparator.comparing(Paciente::getNombre).reversed());

    // Imprimimos
    System.out.println("Pacientes a cargo del enfermero " + nombre + ":");
    for (Paciente paciente : listaPacientes) {
      System.out.println(
        paciente.getNombre() + " (" + paciente.getEspecialidadAtencion() + ")."
      );
    }
  }

  // Método para dar tratamiento al paciente
  @Override
  public void darTratamiento(Paciente paciente) {

    // Si el enfermero no tiene pacientes
    if (listaPacientes.isEmpty()) {
      System.out.println(
        "El enfermero " + nombre + " no tiene pacientes en su lista."
      );
      return;
    }

    // Si el paciente está en consulta (solo se pueden dar tratamientos después de una consulta)
    if (paciente.estaEnConsulta()) {
      System.out.println(
        "El paciente " +
          paciente.getNombre() +
          " se encuentra aún en consulta médica."
      );
      return;
    }

    // Si el enfermero tiene al paciente y no se le ha asignado tratamiento aún
    else if (
      listaPacientes.contains(paciente) && !paciente.estaEnTratamiento()
    ) {
      paciente.setEnTratamiento(true);
      System.out.println(
        "El enfermero " +
          nombre +
          " está ahora administrando el tratamiento del paciente " +
          paciente.getNombre() +
          "."
      );
      return;
    }

    // Si el enfermero tiene al paciente pero ya se le asignó tratamiento
    else if (listaPacientes.contains(paciente)) {
      System.out.println(
        "El paciente " +
          paciente.getNombre() +
          " ya se encuentra recibiendo tratamiento."
      );
      return;
    }
    
    // Si el enfermero no tiene al paciente
    else {
      System.out.println(
        "El paciente " +
          paciente.getNombre() +
          " no se encuentra en la lista del enfermero " +
          nombre +
          "."
      );
      return;
    }
  }

  // Sobreescribimos el método toString para mostrar información del enfermero (abstracto porque va a ser implementado por las subclases)
  @Override
  public abstract String toString();
}

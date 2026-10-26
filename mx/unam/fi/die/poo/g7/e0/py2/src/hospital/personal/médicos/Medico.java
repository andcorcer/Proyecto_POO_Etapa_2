// Importes
package mx.unam.fi.die.poo.g7.e0.py2.src.hospital.personal.médicos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.interfaces.AtencionMedica;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.pacientes.Paciente;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.sistema.Sistema;

public abstract class Medico implements AtencionMedica {

  protected String nombre;
  protected String cedula;
  protected String especialidad;
  protected int noPacientes;

  // Clave para checar si ya está ocupado y el paciente que está siendo consultado
  protected Paciente pacienteSiendoConsultado;

  // Lista para llevar registro
  protected List<Paciente> listaPacientes;

  // Constructor
  public Medico(String nombre, String cedula, String especialidad) {
    this.nombre = nombre;
    this.cedula = cedula;
    this.especialidad = especialidad;
    this.noPacientes = 0;
    this.listaPacientes = new ArrayList<>();
    this.pacienteSiendoConsultado = null;
  }

  // GETTERS

  // Get que retorna el nombre
  public String getNombre() {
    return nombre;
  }

  // Get que retorna el número de pacientes
  public int getNoPacientes() {
    return noPacientes;
  }

  // Get que retorna la cédula del médico
  public String getCedula() {
    return cedula;
  }

  // Get que retorna la especialidad del médico
  public String getEspecialidad() {
    return especialidad;
  }

  // MÉTODOS

  // Método que registra todo el objeto Medico llamando al método 'registroMedico' del sistema con el objeto entero como argumento
  @Override
  public void registroEnSistema(Sistema sistema) {
    sistema.registroMedico(this);
  }

  // Método para dar tratamiento a un paciente después de una consulta
  @Override
  public void darTratamiento(Paciente paciente) {
    // Verificamos si la lista de pacientes no está vacía
    if (!listaPacientes.isEmpty()) {
      // Verificamos si el médico ya se encuentra en consulta
      if (pacienteSiendoConsultado != null) {
        // Verificamos si el paciente que está siendo consultado es el mismo que el que se quiere tratar
        if (pacienteSiendoConsultado.equals(paciente)) {
          // Verificamos si el paciente no ha recibido tratamiento
          if (!paciente.estaEnTratamiento()) {
            paciente.setEnConsulta(false);
            paciente.setEnTratamiento(true);
            pacienteSiendoConsultado = null;
            System.out.println(
              "El Dr. " +
                nombre +
                " está dando tratamiento a " +
                paciente.getNombre() +
                "."
            );
          }
          // Si el paciente ya está en tratamiento, imprimimos un mensaje
          else {
            System.out.println(
              "El paciente " +
                paciente.getNombre() +
                " ya se encuentra recibiendo tratamiento."
            );
          }
        }
        // Si el médico está ocupado con otro paciente, imprimimos un mensaje
        else {
          System.out.println(
            "El Dr. " +
              nombre +
              " se encuentra ocupado atendiendo al paciente " +
              pacienteSiendoConsultado.getNombre() +
              "."
          );
          return;
        }
      }
      // Si el médico no ha consultado al paciente, imprimimos un mensaje
      else {
        System.out.println(
          "El Dr. " +
            nombre +
            " no ha consultado al paciente " +
            paciente.getNombre() +
            "por lo que no puede darle tratamiento."
        );
      }
    }
    // Si la lista de pacientes está vacía, imprimimos un mensaje
    else {
      System.out.println(
        "El Dr. " + nombre + " no tiene pacientes en su lista."
      );
    }
  }

  // Método para dar consulta a un paciente
  @Override
  public void darConsulta(Paciente paciente) {
    // Verificamos si el paciente está en la lista de pacientes
    if (listaPacientes.contains(paciente)) {
      // Verificamos si el médico no está ocupado y puede dar consulta
      if (pacienteSiendoConsultado == null) {
        // Verificamos si el paciente no está en consulta
        if (!paciente.estaEnConsulta()) {
          // Verificamos si el paciente no está recibiendo tratamiento
          if (!paciente.estaEnTratamiento()) {
            paciente.setEnConsulta(true);
            pacienteSiendoConsultado = paciente;
            System.out.println(
              "El Dr. " +
                nombre +
                " está consultando a " +
                paciente.getNombre() +
                "."
            );
          }
          // Si el paciente está recibiendo tratamiento, se imprime un mensaje
          else {
            System.out.println(
              "El paciente " +
                paciente.getNombre() +
                " ya está recibiendo tratamiento."
            );
          }
        }
        // Si el paciente ya está en consulta, se imprime un mensaje
        else {
          System.out.println(
            "El paciente " + paciente.getNombre() + " ya está en consulta."
          );
        }
      }
      // Si el médico está ocupado, se imprime un mensaje
      else {
        System.out.println(
          "El Dr. " + nombre + " ya está consultando a otro paciente."
        );
      }
    }
    // Si el paciente no está en la lista, se imprime un mensaje
    else {
      System.out.println(
        "El paciente " +
          paciente.getNombre() +
          " no está en la lista del médico " +
          nombre +
          "."
      );
    }
  }

  // Método para ver los pacientes del médico
  @Override
  public void verListaPacientes() {
    // Ordenamos alfabeticamente de manera descendente 'listaPacientes'
    listaPacientes.sort(Comparator.comparing(Paciente::getNombre).reversed());

    // Imprimimos
    System.out.println("Pacientes a cargo del médico " + nombre + ":");
    for (Paciente paciente : listaPacientes) {
      System.out.println(
        paciente.getNombre() + " (" + paciente.getEspecialidadAtencion() + ")."
      );
    }
  }

  // Método para finalizar la consulta del paciente actual
  @Override
  public void finalizarConsulta() {
    // Verificamos si hay un paciente siendo consultado actualmente
    if (pacienteSiendoConsultado != null) {
      pacienteSiendoConsultado.setEnConsulta(false);
      System.out.println(
        "El Dr. " + nombre + " ha finalizado la consulta con " + pacienteSiendoConsultado.getNombre() + "."
      );
      pacienteSiendoConsultado = null;
    } 
    // Si no hay paciente siendo consultado actualmente, se imprime un mensaje
    else {
      System.out.println("El Dr. " + nombre + " no está consultando a ningún paciente.");
    }
  }

  // Método que agrega paciente a la lista de pacientes (abstracto porque va a ser implementado por las subclases)
  public abstract void agregarPaciente(Paciente paciente);

  // Método que imprime una solicitud por paciente (abstracto porque va a ser implementado por las subclases)
  public abstract void solicitarPaciente();

  // Sobreescribimos el método toString para mostrar información del médico (abstracto porque va a ser implementado por las subclases)
  @Override
  public abstract String toString();
}

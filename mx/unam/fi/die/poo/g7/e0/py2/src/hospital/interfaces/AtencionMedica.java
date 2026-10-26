// Importes
package mx.unam.fi.die.poo.g7.e0.py2.src.hospital.interfaces;

import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.sistema.Sistema;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.pacientes.Paciente;

public interface AtencionMedica {
    // Método que registra todo el objeto Medico llamando al método 'registroMedico' del sistema con el objeto entero como argumento
  public void registroEnSistema(Sistema sistema);

  // Se agrega paciente a la lista de espera
  public void agregarPaciente(Paciente paciente);

  // Método que imprime una solicitud por paciente
  public void solicitarPaciente();

  // Método que revisa si el médico puede dar consulta y de ser posible la da al primer Paciente en la lista de espera disponible
  public void darConsulta(Paciente paciente);

  // Método para dar tratamiento a un paciente después de una consulta
  public void darTratamiento(Paciente paciente);

  // Método para finalizar la consulta del paciente actual
  public void finalizarConsulta();

  // Imprimimos todos los pacientes en la lista de manera alfabética descendente
  public void verListaPacientes();
}

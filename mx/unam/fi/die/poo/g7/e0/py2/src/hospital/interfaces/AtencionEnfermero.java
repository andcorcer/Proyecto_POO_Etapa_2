// Importes
package mx.unam.fi.die.poo.g7.e0.py2.src.hospital.interfaces;

import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.pacientes.Paciente;
import mx.unam.fi.die.poo.g7.e0.py2.src.hospital.sistema.Sistema;


public interface AtencionEnfermero {
  // Método que registra todo el objeto Enfermero llamando al método 'registroEnfermero' del sistema con el objeto entero como argumento
  public void registroEnSistema(Sistema sistema);

  // Se agrega paciente a la lista de pacientes siendo cuidados
  public void agregarPaciente(Paciente paciente);

  // Método para ver los pacientes del enfermero
  public void verListaPacientes();

  // Método para dar tratamiento al paciente
  public void darTratamiento(Paciente paciente);
}
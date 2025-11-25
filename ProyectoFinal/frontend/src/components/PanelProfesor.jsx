import React, { useState, useEffect } from 'react';
import { api } from '../services/api';

const PanelProfesor = () => {
  const [bloqueos, setBloqueos] = useState([]);
  const [laboratorios, setLaboratorios] = useState([]);
  const [equipos, setEquipos] = useState([]);
  const [reservas, setReservas] = useState([]);
  const [formVisible, setFormVisible] = useState(false);
  const [verReservas, setVerReservas] = useState(false);
  const [labSeleccionado, setLabSeleccionado] = useState('');
  const [formData, setFormData] = useState({
    laboratorioId: '',
    equipoCodigo: '',
    inicio: '',
    fin: '',
    motivo: ''
  });

  useEffect(() => {
    cargarDatos();
  }, []);

  useEffect(() => {
    if (formData.laboratorioId) {
      cargarEquipos(formData.laboratorioId);
    }
  }, [formData.laboratorioId]);

  useEffect(() => {
    if (labSeleccionado) {
      cargarReservas(labSeleccionado);
    }
  }, [labSeleccionado]);

  const cargarDatos = async () => {
    try {
      const [labsRes, bloqRes] = await Promise.all([
        api.get('/disponibilidad/laboratorios'),
        api.get('/bloqueos/mis-bloqueos')
      ]);

      setLaboratorios(labsRes.data);
      if (bloqRes.data.success) {
        setBloqueos(bloqRes.data.data);
      }
    } catch (error) {
      console.error('Error al cargar datos:', error);
    }
  };

  const cargarEquipos = async (laboratorioId) => {
    try {
      const response = await api.get(`/disponibilidad/equipos/${laboratorioId}`);
      if (response.data.success) {
        setEquipos(response.data.data || []);
      } else {
        setEquipos([]);
      }
    } catch (error) {
      console.error('Error al cargar equipos:', error);
      setEquipos([]);
    }
  };

  const cargarReservas = async (laboratorioId) => {
    try {
      const response = await api.get(`/reservas/laboratorio/${laboratorioId}`);
      if (response.data.success) {
        setReservas(response.data.data);
      }
    } catch (error) {
      console.error('Error al cargar reservas:', error);
      setReservas([]);
    }
  };

  const crearBloqueo = async (e) => {
    e.preventDefault();

    try {
      const response = await api.post('/bloqueos', formData);
      if (response.data.success) {
        alert('✅ Bloqueo creado exitosamente');
        setFormVisible(false);
        setFormData({
          laboratorioId: '',
          equipoCodigo: '',
          inicio: '',
          fin: '',
          motivo: ''
        });
        setEquipos([]);
        cargarDatos();
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al crear bloqueo');
    }
  };

  const desbloquear = async (id) => {
    if (!window.confirm('¿Desbloquear?')) return;

    try {
      const response = await api.put(`/bloqueos/${id}/desbloquear`);
      if (response.data.success) {
        alert('✅ Desbloqueado exitosamente');
        cargarDatos();
      }
    } catch (error) {
      alert('Error al desbloquear');
    }
  };

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h2 className="text-3xl font-bold text-gray-900">
          Panel de Profesor
        </h2>
        <div className="flex gap-3">
          <button
            onClick={() => {
              setVerReservas(!verReservas);
              setFormVisible(false);
            }}
            className={`px-4 py-2 rounded-lg font-medium transition ${
              verReservas ? 'bg-green-600 text-white' : 'bg-white text-green-600 border-2 border-green-600'
            }`}
          >
            {verReservas ? '❌ Cerrar' : '📋 Ver Reservas'}
          </button>
          <button
            onClick={() => {
              setFormVisible(!formVisible);
              setVerReservas(false);
            }}
            className={`px-4 py-2 rounded-lg font-medium transition ${
              formVisible ? 'bg-red-600 text-white' : 'bg-orange-600 text-white'
            }`}
          >
            {formVisible ? '❌ Cancelar' : '🔒 Nuevo Bloqueo'}
          </button>
        </div>
      </div>

      {formVisible && (
        <div className="card p-6 mb-6">
          <h3 className="text-xl font-bold text-gray-900 mb-4">
            Crear Bloqueo de Equipo
          </h3>

          <form onSubmit={crearBloqueo} className="space-y-4">
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">
                Laboratorio *
              </label>
              <select
                value={formData.laboratorioId}
                onChange={(e) => setFormData({...formData, laboratorioId: e.target.value, equipoCodigo: ''})}
                className="input-field"
                required
              >
                <option value="">Seleccionar laboratorio</option>
                {laboratorios.map(lab => (
                  <option key={lab.id} value={lab.id}>{lab.nombre}</option>
                ))}
              </select>
            </div>

            {formData.laboratorioId && (
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Equipo (opcional - dejar vacío para bloquear todo el laboratorio)
                </label>
                <select
                  value={formData.equipoCodigo}
                  onChange={(e) => setFormData({...formData, equipoCodigo: e.target.value})}
                  className="input-field"
                >
                  <option value="">🔒 Bloquear TODO el laboratorio</option>
                  {equipos.length > 0 ? (
                    equipos.map(equipo => (
                      <option key={equipo.id} value={equipo.codigo}>
                        {equipo.codigo} ({equipo.tipo})
                      </option>
                    ))
                  ) : (
                    <option disabled>Cargando equipos...</option>
                  )}
                </select>
                {equipos.length === 0 && formData.laboratorioId && (
                  <p className="text-sm text-amber-600 mt-1">
                    ⚠️ No se encontraron equipos en este laboratorio
                  </p>
                )}
              </div>
            )}

            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Fecha y Hora de Inicio *
                </label>
                <input
                  type="datetime-local"
                  value={formData.inicio}
                  onChange={(e) => setFormData({...formData, inicio: e.target.value})}
                  className="input-field"
                  min={new Date().toISOString().slice(0, 16)}
                  required
                />
                <p className="text-xs text-gray-500 mt-1">
                  Ejemplo: 24/11/2025 3:00 PM
                </p>
              </div>

              <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                  Fecha y Hora de Fin *
                </label>
                <input
                  type="datetime-local"
                  value={formData.fin}
                  onChange={(e) => setFormData({...formData, fin: e.target.value})}
                  className="input-field"
                  min={formData.inicio || new Date().toISOString().slice(0, 16)}
                  required
                />
                <p className="text-xs text-gray-500 mt-1">
                  Ejemplo: 24/11/2025 7:00 PM
                </p>
              </div>
            </div>

            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">
                Motivo *
              </label>
              <textarea
                value={formData.motivo}
                onChange={(e) => setFormData({...formData, motivo: e.target.value})}
                className="input-field"
                rows="3"
                placeholder="Ej: Mantenimiento preventivo, Actualización de software, etc."
                required
              />
            </div>

            <button type="submit" className="btn-primary w-full">
              🔒 Crear Bloqueo
            </button>
          </form>
        </div>
      )}

      {verReservas && (
        <div className="card p-6 mb-6">
          <h3 className="text-xl font-bold text-gray-900 mb-4">
            Ver Reservas por Laboratorio
          </h3>

          <div className="mb-4">
            <label className="block text-sm font-semibold text-gray-700 mb-2">
              Seleccionar Laboratorio
            </label>
            <select
              value={labSeleccionado}
              onChange={(e) => setLabSeleccionado(e.target.value)}
              className="input-field"
            >
              <option value="">Seleccionar laboratorio</option>
              {laboratorios.map(lab => (
                <option key={lab.id} value={lab.id}>{lab.nombre}</option>
              ))}
            </select>
          </div>

          {labSeleccionado && (
            <div className="space-y-3">
              <h4 className="font-semibold text-gray-700">
                Reservas Activas ({reservas.length})
              </h4>
              {reservas.length > 0 ? (
                reservas.map(reserva => (
                  <div key={reserva.id} className="border border-gray-200 rounded-lg p-4 bg-gray-50">
                    <div className="flex items-start justify-between">
                      <div className="flex-1">
                        <div className="flex items-center space-x-2 mb-2">
                          <span className="font-semibold text-gray-900">
                            {reserva.equipoCodigo}
                          </span>
                          <span className={`badge ${
                            reserva.estado === 'PROGRAMADA' ? 'badge-info' :
                            reserva.estado === 'EN_CURSO' ? 'badge-success' :
                            'badge-gray'
                          }`}>
                            {reserva.estado.replace('_', ' ')}
                          </span>
                        </div>
                        <div className="text-sm text-gray-600 space-y-1">
                          <p>👤 Estudiante: {reserva.estudianteNombre}</p>
                          <p>📅 {new Date(reserva.franja.inicio).toLocaleString('es-CO', {
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit',
                            hour: '2-digit',
                            minute: '2-digit'
                          })} - {new Date(reserva.franja.fin).toLocaleTimeString('es-CO', {
                            hour: '2-digit',
                            minute: '2-digit'
                          })}</p>
                        </div>
                      </div>
                    </div>
                  </div>
                ))
              ) : (
                <p className="text-center text-gray-500 py-4">
                  No hay reservas activas en este laboratorio
                </p>
              )}
            </div>
          )}
        </div>
      )}

      <div>
        <h3 className="text-xl font-bold text-gray-900 mb-4">
          Mis Bloqueos
        </h3>

        {bloqueos.length > 0 ? (
          <div className="space-y-4">
            {bloqueos.map(bloqueo => (
              <div key={bloqueo.id} className="card p-6">
                <div className="flex items-start justify-between">
                  <div className="flex-1">
                    <div className="flex items-center space-x-3 mb-2">
                      <h4 className="text-lg font-bold text-gray-900">
                        {bloqueo.motivo}
                      </h4>
                      <span className={`badge ${bloqueo.activo ? 'badge-danger' : 'badge-gray'}`}>
                        {bloqueo.activo ? 'Activo' : 'Inactivo'}
                      </span>
                    </div>

                    <div className="text-sm text-gray-600 space-y-1">
                      <p>
                        📅 {new Date(bloqueo.franja.inicio).toLocaleString('es-CO')}
                        {' - '}
                        {new Date(bloqueo.franja.fin).toLocaleString('es-CO')}
                      </p>
                    </div>
                  </div>

                  {bloqueo.activo && (
                    <button
                      onClick={() => desbloquear(bloqueo.id)}
                      className="btn-success text-sm"
                    >
                      🔓 Desbloquear
                    </button>
                  )}
                </div>
              </div>
            ))}
          </div>
        ) : (
          <div className="text-center py-12">
            <p className="text-gray-600">No hay bloqueos</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default PanelProfesor;
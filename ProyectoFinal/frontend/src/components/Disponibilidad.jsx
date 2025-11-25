import React, { useState, useEffect } from 'react';
import { api } from '../services/api';

const Disponibilidad = ({ usuario }) => {
  const [laboratorios, setLaboratorios] = useState([]);
  const [filtros, setFiltros] = useState({
    laboratorioId: '',
    fecha: new Date().toISOString().split('T')[0],
    franja: 'FRANJA_07_09'
  });
  const [equiposDisponibles, setEquiposDisponibles] = useState([]);
  const [loading, setLoading] = useState(false);
  const [reservando, setReservando] = useState(false);

  useEffect(() => {
    cargarLaboratorios();
  }, []);

  const cargarLaboratorios = async () => {
    try {
      const response = await api.get('/disponibilidad/laboratorios');
      setLaboratorios(response.data);
      if (response.data.length > 0) {
        setFiltros({...filtros, laboratorioId: response.data[0].id});
      }
    } catch (error) {
      console.error('Error al cargar laboratorios:', error);
    }
  };

  const consultarDisponibilidad = async () => {
    setLoading(true);
    try {
      const response = await api.post('/disponibilidad/consultar', filtros);
      if (response.data.success) {
        setEquiposDisponibles(response.data.data.equiposDisponibles);
      }
    } catch (error) {
      alert('Error al consultar disponibilidad');
    } finally {
      setLoading(false);
    }
  };

  const reservarEquipo = async (equipoCodigo, laboratorioId) => {
    if (!window.confirm('¿Confirmar reserva?')) return;

    setReservando(true);
    try {
      const reservaData = {
        laboratorioId: laboratorioId,
        equipoCodigo: equipoCodigo,
        fecha: filtros.fecha,
        franja: filtros.franja
      };

      const response = await api.post('/reservas', reservaData);
      if (response.data.success) {
        alert('✅ Reserva creada exitosamente');
        consultarDisponibilidad();
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al crear reserva');
    } finally {
      setReservando(false);
    }
  };

  return (
    <div>
      <h2 className="text-3xl font-bold text-gray-900 mb-6">
        Disponibilidad de Equipos
      </h2>

      <div className="card p-6 mb-6">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
          <div>
            <label className="block text-sm font-semibold text-gray-700 mb-2">
              Laboratorio
            </label>
            <select
              value={filtros.laboratorioId}
              onChange={(e) => setFiltros({...filtros, laboratorioId: e.target.value})}
              className="input-field"
            >
              <option value="">Todos</option>
              {laboratorios.map(lab => (
                <option key={lab.id} value={lab.id}>{lab.nombre}</option>
              ))}
            </select>
          </div>

          <div>
            <label className="block text-sm font-semibold text-gray-700 mb-2">
              Fecha
            </label>
            <input
              type="date"
              value={filtros.fecha}
              onChange={(e) => setFiltros({...filtros, fecha: e.target.value})}
              className="input-field"
              min={new Date().toISOString().split('T')[0]}
            />
          </div>

          <div>
            <label className="block text-sm font-semibold text-gray-700 mb-2">
              Franja Horaria (2 horas)
            </label>
            <select
              value={filtros.franja}
              onChange={(e) => setFiltros({...filtros, franja: e.target.value})}
              className="input-field"
            >
              <option value="FRANJA_07_09">7:00 - 9:00</option>
              <option value="FRANJA_09_11">9:00 - 11:00</option>
              <option value="FRANJA_11_13">11:00 - 13:00</option>
              <option value="FRANJA_13_15">13:00 - 15:00</option>
              <option value="FRANJA_15_17">15:00 - 17:00</option>
              <option value="FRANJA_17_19">17:00 - 19:00</option>
            </select>
          </div>

          <div className="flex items-end">
            <button
              onClick={consultarDisponibilidad}
              disabled={loading}
              className="w-full btn-primary"
            >
              {loading ? '⏳' : '🔍'} Buscar
            </button>
          </div>
        </div>
      </div>

      {equiposDisponibles.length > 0 ? (
        <div>
          <div className="mb-4">
            <span className="text-lg font-semibold text-gray-900">
              {equiposDisponibles.length} equipos disponibles
            </span>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {equiposDisponibles.map(equipo => (
              <div key={equipo.id} className="card p-6">
                <div className="flex items-start justify-between mb-4">
                  <div>
                    <h3 className="text-xl font-bold text-gray-900">
                      {equipo.codigo}
                    </h3>
                    <p className="text-sm text-gray-600">{equipo.tipo}</p>
                  </div>
                  <span className="badge badge-success">Disponible</span>
                </div>

                <p className="text-sm text-gray-600 mb-4">
                  {equipo.especificaciones}
                </p>

                <button
                  onClick={() => reservarEquipo(equipo.codigo, equipo.laboratorioId)}
                  disabled={reservando}
                  className="w-full btn-success"
                >
                  📅 Reservar
                </button>
              </div>
            ))}
          </div>
        </div>
      ) : (
        <div className="text-center py-12">
          <div className="text-6xl mb-4">🔍</div>
          <p className="text-gray-600">
            {loading ? 'Buscando...' : 'No hay equipos disponibles en este horario'}
          </p>
        </div>
      )}
    </div>
  );
};

export default Disponibilidad;
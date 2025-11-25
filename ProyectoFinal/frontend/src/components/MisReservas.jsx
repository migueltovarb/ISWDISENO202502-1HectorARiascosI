import React, { useState, useEffect } from 'react';
import { api } from '../services/api';

const MisReservas = ({ usuario }) => {
  const [reservas, setReservas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [mostrarFinalizadas, setMostrarFinalizadas] = useState(true);

  useEffect(() => {
    cargarReservas();
  }, []);

  const cargarReservas = async () => {
    try {
      const response = await api.get('/reservas/mis-reservas');
      if (response.data.success) {
        console.log('Reservas cargadas:', response.data.data);
        setReservas(response.data.data);
      }
    } catch (error) {
      console.error('Error al cargar reservas:', error);
    } finally {
      setLoading(false);
    }
  };

  const cancelarReserva = async (id) => {
    if (!window.confirm('¿Estás seguro de cancelar esta reserva?')) return;

    try {
      const response = await api.put(`/reservas/${id}/cancelar`);
      if (response.data.success) {
        alert('✅ Reserva cancelada exitosamente');
        cargarReservas();
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al cancelar');
    }
  };

  const checkIn = async (id) => {
    if (!window.confirm('¿Confirmar check-in?')) return;

    try {
      const response = await api.put(`/reservas/${id}/checkin`);
      if (response.data.success) {
        alert('✅ Check-in exitoso');
        cargarReservas();
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al hacer check-in');
    }
  };

  const checkOut = async (id) => {
    if (!window.confirm('¿Confirmar check-out?')) return;

    try {
      const response = await api.put(`/reservas/${id}/checkout`);
      if (response.data.success) {
        alert('✅ Check-out exitoso');
        cargarReservas();
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al hacer check-out');
    }
  };

  const limpiarCanceladas = async () => {
    const reservasCanceladas = reservas.filter(
      r => r.estado === 'CANCELADA' || r.estado === 'CANCELADA_POR_MANTENIMIENTO'
    );

    if (reservasCanceladas.length === 0) {
      alert('No hay reservas canceladas para limpiar');
      return;
    }

    if (!window.confirm(
      `¿Estás seguro de eliminar permanentemente ${reservasCanceladas.length} reserva(s) cancelada(s)?\n\n` +
      'Esta acción no se puede deshacer.'
    )) return;

    try {
      const response = await api.delete('/reservas/limpiar-canceladas');
      if (response.data.success) {
        alert(`✅ ${response.data.data.eliminadas} reserva(s) eliminada(s) permanentemente`);
        cargarReservas();
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al limpiar reservas');
    }
  };

  const getEstadoBadge = (estado) => {
    const badges = {
      PROGRAMADA: 'badge-info',
      EN_CURSO: 'badge-warning',
      COMPLETADA: 'badge-success',
      CANCELADA: 'badge-danger',
      EXPIRADA: 'badge-gray',
      CANCELADA_POR_MANTENIMIENTO: 'badge-danger'
    };
    return badges[estado] || 'badge';
  };

  if (loading) {
    return (
      <div className="text-center py-12">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-lab-blue mx-auto"></div>
        <p className="mt-4 text-gray-600">Cargando reservas...</p>
      </div>
    );
  }

  const reservasFiltradas = mostrarFinalizadas 
    ? reservas 
    : reservas.filter(r => r.estado !== 'CANCELADA' && r.estado !== 'COMPLETADA' && r.estado !== 'CANCELADA_POR_MANTENIMIENTO');

  const reservasActivas = reservas.filter(r => r.estado === 'PROGRAMADA' || r.estado === 'EN_CURSO').length;
  const reservasFinalizadas = reservas.filter(r => r.estado === 'CANCELADA' || r.estado === 'COMPLETADA' || r.estado === 'CANCELADA_POR_MANTENIMIENTO').length;
  const reservasCanceladas = reservas.filter(r => r.estado === 'CANCELADA' || r.estado === 'CANCELADA_POR_MANTENIMIENTO').length;

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h2 className="text-3xl font-bold text-gray-900">
          Mis Reservas
        </h2>
        
        <div className="flex items-center space-x-4">
          <div className="text-sm text-gray-600">
            <span className="font-semibold">{reservasActivas}</span> activas • 
            <span className="font-semibold ml-1">{reservasFinalizadas}</span> finalizadas
          </div>
          
          <div className="flex gap-2">
            {reservasFinalizadas > 0 && (
              <button
                onClick={() => setMostrarFinalizadas(!mostrarFinalizadas)}
                className="btn-secondary text-sm"
              >
                {mostrarFinalizadas ? '👁️‍🗨️ Ocultar finalizadas' : '👁️ Mostrar todas'}
              </button>
            )}
            
            {reservasCanceladas > 0 && (
              <button
                onClick={limpiarCanceladas}
                className="bg-red-600 text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-red-700 transition"
              >
                🗑️ Limpiar Canceladas ({reservasCanceladas})
              </button>
            )}
          </div>
        </div>
      </div>

      {reservasFiltradas.length > 0 ? (
        <div className="space-y-4">
          {reservasFiltradas.map(reserva => (
            <div key={reserva.id} className="card p-6">
              <div className="flex items-start justify-between">
                <div className="flex-1">
                  <div className="flex items-center space-x-3 mb-2">
                    <h3 className="text-xl font-bold text-gray-900">
                      {reserva.equipoCodigo}
                    </h3>
                    <span className={`badge ${getEstadoBadge(reserva.estado)}`}>
                      {reserva.estado.replace('_', ' ')}
                    </span>
                  </div>

                  <div className="space-y-1 text-sm text-gray-600">
                    <p className="text-xs text-gray-400">ID: {reserva.id}</p>
                    <p>📍 {reserva.laboratorioNombre}</p>
                    <p>📅 {new Date(reserva.franja.inicio).toLocaleDateString('es-CO')}</p>
                    <p>
                      ⏰ {new Date(reserva.franja.inicio).toLocaleTimeString('es-CO', {hour: '2-digit', minute: '2-digit'})}
                      {' - '}
                      {new Date(reserva.franja.fin).toLocaleTimeString('es-CO', {hour: '2-digit', minute: '2-digit'})}
                    </p>
                  </div>
                </div>

                <div className="flex flex-col space-y-2">
                  {reserva.estado === 'PROGRAMADA' && (
                    <>
                      <button
                        onClick={() => checkIn(reserva.id)}
                        className="btn-success text-sm px-4 py-2"
                      >
                        ✅ Check-in
                      </button>
                      <button
                        onClick={() => cancelarReserva(reserva.id)}
                        className="btn-danger text-sm px-4 py-2"
                      >
                        ❌ Cancelar
                      </button>
                    </>
                  )}
                  
                  {reserva.estado === 'EN_CURSO' && (
                    <>
                      <button
                        onClick={() => checkOut(reserva.id)}
                        className="btn-primary text-sm px-4 py-2"
                      >
                        🚪 Check-out
                      </button>
                      <button
                        onClick={() => cancelarReserva(reserva.id)}
                        className="btn-danger text-sm px-4 py-2"
                      >
                        ❌ Cancelar
                      </button>
                    </>
                  )}

                  {(reserva.estado === 'COMPLETADA' || reserva.estado === 'CANCELADA') && (
                    <span className="text-xs text-gray-500 text-center">
                      {reserva.estado === 'COMPLETADA' ? 'Finalizada' : 'Cancelada'}
                    </span>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <div className="text-center py-12">
          <div className="text-6xl mb-4">📋</div>
          <p className="text-gray-600">
            {reservas.length === 0 
              ? 'No tienes reservas' 
              : 'No hay reservas activas. Click en "Mostrar todas" para ver el historial.'}
          </p>
        </div>
      )}
    </div>
  );
};

export default MisReservas;
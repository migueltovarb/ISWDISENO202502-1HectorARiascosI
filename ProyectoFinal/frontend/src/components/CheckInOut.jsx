import React, { useState } from 'react';
import { api } from '../services/api';

const CheckInOut = ({ usuario }) => {
  const [reservaId, setReservaId] = useState('');
  const [loading, setLoading] = useState(false);

  const handleCheckIn = async () => {
    if (!reservaId) {
      alert('Ingresa el ID de la reserva');
      return;
    }

    setLoading(true);
    try {
      const response = await api.put(`/reservas/${reservaId}/checkin`);
      if (response.data.success) {
        alert('✅ Check-in exitoso');
        setReservaId('');
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al hacer check-in');
    } finally {
      setLoading(false);
    }
  };

  const handleCheckOut = async () => {
    if (!reservaId) {
      alert('Ingresa el ID de la reserva');
      return;
    }

    setLoading(true);
    try {
      const response = await api.put(`/reservas/${reservaId}/checkout`);
      if (response.data.success) {
        alert('✅ Check-out exitoso');
        setReservaId('');
      }
    } catch (error) {
      alert(error.response?.data?.message || 'Error al hacer check-out');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2 className="text-3xl font-bold text-gray-900 mb-6">
        Check-in / Check-out
      </h2>

      <div className="max-w-2xl mx-auto">
        <div className="card p-8">
          <div className="text-center mb-8">
            <div className="text-6xl mb-4">✅</div>
            <p className="text-gray-600">
              Ingresa el ID de tu reserva para hacer check-in o check-out
            </p>
          </div>

          <div className="space-y-6">
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">
                ID de Reserva
              </label>
              <input
                type="text"
                value={reservaId}
                onChange={(e) => setReservaId(e.target.value)}
                placeholder="Ej: 507f1f77bcf86cd799439011"
                className="input-field"
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <button
                onClick={handleCheckIn}
                disabled={loading || !reservaId}
                className="btn-success py-3"
              >
                {loading ? '⏳' : '🟢'} Check-in
              </button>

              <button
                onClick={handleCheckOut}
                disabled={loading || !reservaId}
                className="btn-danger py-3"
              >
                {loading ? '⏳' : '🔴'} Check-out
              </button>
            </div>
          </div>

          <div className="mt-8 bg-blue-50 border border-blue-200 rounded-lg p-4 text-sm text-blue-800">
            <p className="font-semibold mb-2">ℹ️ Información:</p>
            <ul className="list-disc list-inside space-y-1">
              <li>Check-in: Desde 10 min antes hasta 20 min después del inicio</li>
              <li>Check-out: Al finalizar tu sesión</li>
              <li>Sin check-in: La reserva se libera automáticamente</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CheckInOut;
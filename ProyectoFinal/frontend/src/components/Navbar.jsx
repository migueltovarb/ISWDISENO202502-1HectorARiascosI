import React from 'react';

const Navbar = ({ currentView, setCurrentView, usuario, onLogout }) => {
  return (
    <nav className="bg-white shadow-lg sticky top-0 z-50">
      <div className="container mx-auto px-4">
        <div className="flex items-center justify-between h-20">
          <div className="flex items-center space-x-3">
            <div className="text-4xl">🖥️</div>
            <div>
              <h1 className="text-2xl font-bold text-lab-blue">Sistema de Turnos</h1>
              <p className="text-xs text-gray-500">Gestión de Laboratorios</p>
            </div>
          </div>

          <div className="hidden md:flex items-center space-x-4">
            {usuario.rol === 'ESTUDIANTE' && (
              <>
                <button
                  onClick={() => setCurrentView('disponibilidad')}
                  className={`px-4 py-2 rounded-lg font-medium transition ${
                    currentView === 'disponibilidad'
                      ? 'bg-lab-blue text-white'
                      : 'text-gray-700 hover:bg-gray-100'
                  }`}
                >
                  📅 Disponibilidad
                </button>

                <button
                  onClick={() => setCurrentView('mis-reservas')}
                  className={`px-4 py-2 rounded-lg font-medium transition ${
                    currentView === 'mis-reservas'
                      ? 'bg-lab-blue text-white'
                      : 'text-gray-700 hover:bg-gray-100'
                  }`}
                >
                  📋 Mis Reservas
                </button>
              </>
            )}

            {usuario.rol === 'PROFESOR' && (
              <button
                onClick={() => setCurrentView('profesor')}
                className={`px-4 py-2 rounded-lg font-medium transition ${
                  currentView === 'profesor'
                    ? 'bg-orange-600 text-white'
                    : 'text-orange-600 hover:bg-orange-50 border border-orange-600'
                }`}
              >
                🔧 Panel Profesor
              </button>
            )}

            <div className="border-l border-gray-300 pl-4 ml-4">
              <div className="text-sm text-gray-600">
                {usuario.nombre}
                <span className={`ml-2 badge ${
                  usuario.rol === 'PROFESOR' ? 'badge-warning' : 'badge-info'
                }`}>
                  {usuario.rol}
                </span>
              </div>
              <button
                onClick={onLogout}
                className="text-xs text-red-600 hover:text-red-700 mt-1"
              >
                Cerrar sesión
              </button>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
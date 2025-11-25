import React, { useState, useEffect } from 'react';
import { api } from './services/api';
import Navbar from './components/Navbar';
import Login from './components/Login';
import Register from './components/Register';
import Disponibilidad from './components/Disponibilidad';
import MisReservas from './components/MisReservas';

import PanelProfesor from './components/PanelProfesor';

function App() {
  const [currentView, setCurrentView] = useState('login');
  const [usuario, setUsuario] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    verificarSesion();
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  const verificarSesion = async () => {
    try {
      const response = await api.get('/usuarios/session');
      if (response.data.success) {
        const userData = response.data.data;
        setUsuario(userData);
        setCurrentView(userData.rol === 'PROFESOR' ? 'profesor' : 'disponibilidad');
      }
    } catch (error) {
      console.log('No hay sesión activa');
    } finally {
      setLoading(false);
    }
  };

  const handleLogin = (usuarioData) => {
    setUsuario(usuarioData);
    setCurrentView(usuarioData.rol === 'PROFESOR' ? 'profesor' : 'disponibilidad');
  };

  const handleLogout = async () => {
    try {
      await api.post('/usuarios/logout');
      setUsuario(null);
      setCurrentView('login');
    } catch (error) {
      console.error('Error al cerrar sesión:', error);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-center">
          <div className="animate-spin rounded-full h-16 w-16 border-b-2 border-lab-blue mx-auto"></div>
          <p className="mt-4 text-gray-600">Cargando...</p>
        </div>
      </div>
    );
  }

  if (!usuario) {
    return (
      <div className="min-h-screen bg-gray-50">
        {currentView === 'login' ? (
          <Login 
            onLogin={handleLogin}
            onSwitchToRegister={() => setCurrentView('register')}
          />
        ) : (
          <Register 
            onRegister={() => setCurrentView('login')}
            onSwitchToLogin={() => setCurrentView('login')}
          />
        )}
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar 
        currentView={currentView}
        setCurrentView={setCurrentView}
        usuario={usuario}
        onLogout={handleLogout}
      />

      <main className="container mx-auto px-4 py-8">
        {currentView === 'disponibilidad' && (
          <Disponibilidad usuario={usuario} />
        )}

        {currentView === 'mis-reservas' && (
          <MisReservas usuario={usuario} />
        )}

        {currentView === 'profesor' && usuario.rol === 'PROFESOR' && (
          <PanelProfesor usuario={usuario} />
        )}
      </main>

      <footer className="bg-white border-t border-gray-200 mt-16">
        <div className="container mx-auto px-4 py-6 text-center text-gray-600">
          <p className="text-sm">
            © 2025 Sistema de Gestión de Turnos - Laboratorio
          </p>
          <p className="text-xs mt-2">
            Universidad Cooperativa de Colombia - Proyecto Final Diseño de Software
          </p>
        </div>
      </footer>
    </div>
  );
}

export default App;
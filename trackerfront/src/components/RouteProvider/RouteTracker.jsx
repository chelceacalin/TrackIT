import React, { createContext, useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { saveRecentURL } from '../../services/RecentlyOpenedUrlService';

export const RouteTrackerContext = createContext();

export const RouteTrackerProvider = ({ children }) => {
  const location = useLocation();
  const [visitedRoutes, setVisitedRoutes] = useState([]);

  useEffect(() => {
    if (location.pathname !== '/home' && location.pathname !== '/signUp' && location.pathname !== '/') {
      setVisitedRoutes((prevRoutes) => [...prevRoutes, location.pathname]);
    }
  }, [location]);

  useEffect(() => {
    if (visitedRoutes.length > 0) {
      saveVisitedRoutesToDatabase();
    }
  }, [visitedRoutes]);

  const saveVisitedRoutesToDatabase = async () => {
    try {
      for (let route of visitedRoutes) {
        await saveRecentURL(route);
      }
      console.log('Visited routes saved to the database');
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <RouteTrackerContext.Provider value={{ visitedRoutes }}>
      {children}
    </RouteTrackerContext.Provider>
  );
};

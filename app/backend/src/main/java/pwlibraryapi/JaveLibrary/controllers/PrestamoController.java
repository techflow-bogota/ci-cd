package pwlibraryapi.JaveLibrary.controllers;

import pwlibraryapi.JaveLibrary.entities.LibroPrestamo;
import pwlibraryapi.JaveLibrary.entities.Prestamo;
import pwlibraryapi.JaveLibrary.models.PrestamoDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrestamoController {
    public Prestamo createPrestamo (Prestamo prestamoToCreate){
        Prestamo prestamo = new Prestamo();
        prestamo.setResponsable(prestamoToCreate.getResponsable());
        prestamo.setFechaprestamo( fechaPrestamo());
        prestamo.setFechavencimiento( fechaVencimiento(prestamo.getFechaprestamo()));
        return prestamo;
    }

    private Date fechaPrestamo(){
        Date fechaPrestamo;
        Calendar fecha = Calendar.getInstance();
        fechaPrestamo = fecha.getTime();
        return fechaPrestamo;
    }

    private Date fechaVencimiento (Date fechaPrestamo){
        Date fechaVencimiento;
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaPrestamo);
        fecha.add(Calendar.MONTH, 1);
        fechaVencimiento = fecha.getTime();
        return fechaVencimiento;
    }


}

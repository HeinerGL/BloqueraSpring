package com.project.bloquera.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.bloquera.dtos.factura.FacturaCreateRequest;
import com.project.bloquera.exceptions.notfound.FacturaNotFoundException;
import com.project.bloquera.exceptions.notfound.OrdenNotFoundException;
import com.project.bloquera.models.Articulo;
import com.project.bloquera.models.EstadoOrden;
import com.project.bloquera.models.Factura;
import com.project.bloquera.models.Orden;
import com.project.bloquera.repositories.ArticuloRepository;
import com.project.bloquera.repositories.EstadoOrdenRepository;
import com.project.bloquera.repositories.FacturaRepository;
import com.project.bloquera.repositories.OrdenRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final OrdenRepository ordenRepository;
    private final ArticuloRepository articuloRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;

    private final DataSource dataSource;

    public FacturaService(FacturaRepository facturaRepository,
        OrdenRepository ordenRepository,
        ArticuloRepository articuloRepository,
        EstadoOrdenRepository estadoOrdenRepository,
        DataSource dataSource) {
        this.facturaRepository = facturaRepository;
        this.ordenRepository = ordenRepository;
        this.articuloRepository = articuloRepository;
        this.estadoOrdenRepository = estadoOrdenRepository;
        this.dataSource = dataSource;
    }

    public Page<Factura> getAllFacturas(Pageable page) {
        return facturaRepository.findAll(page);
    }

    public Factura getFacturaById(Long id) {
        Factura factura = facturaRepository.findById(id)
            .orElseThrow(() -> new FacturaNotFoundException(id));
        return factura;
    }

    public Factura facturar(FacturaCreateRequest facturaRequest, String username) {
        Orden orden = ordenRepository.findById(facturaRequest.ordenId())
            .orElseThrow(() -> new OrdenNotFoundException(facturaRequest.ordenId()));

        Factura factura = new Factura();
        factura.setOrden(orden);
        factura.setSubtotal(orden.getTotal());
        factura.setUsername(username);
        factura.setTotal(factura.getSubtotal() * 1.15);

        orden.getDetalleOrden().forEach(detalle -> {
            Articulo articulo = detalle.getArticulo();

            if (articulo.getStock() >= detalle.getCantidad()) {
                Double newStock = articulo.getStock() - detalle.getCantidad();
                articulo.setStock(newStock);

                articuloRepository.save(articulo);
            }
            // si no lanzar excepcion de producto sin stock
        });

        // asignar estado pendiente a la factura
        EstadoOrden estadoOrden = estadoOrdenRepository.findById(3L)
            .orElseThrow();

        orden.setEstadoOrden(estadoOrden);

        return facturaRepository.save(factura);
    }

    public byte[] report(Long id) throws IOException, SQLException, JRException {
        ClassPathResource resource = new ClassPathResource("factura.jasper");
        InputStream input = resource.getInputStream();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("facturaId", id);

        JasperPrint print = JasperFillManager.fillReport(input, parametros, dataSource.getConnection());

        return JasperExportManager.exportReportToPdf(print);
    }

    public byte[] awa(Long id) throws JRException, SQLException, IOException {
        return report(id);
    }
}

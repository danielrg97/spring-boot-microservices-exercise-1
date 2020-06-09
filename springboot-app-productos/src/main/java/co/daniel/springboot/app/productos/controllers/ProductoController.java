package co.daniel.springboot.app.productos.controllers;

import co.daniel.springboot.app.productos.models.entity.Producto;
import co.daniel.springboot.app.productos.models.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    @Autowired
    private Environment environment;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.findAll().stream().map(p -> {
                    p.setPort(port.longValue());
                    return p;
                }
        ).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) throws Exception {
        Producto producto = productoService.findById(id);
        producto.setPort(port.longValue());
        return producto;
    }
}

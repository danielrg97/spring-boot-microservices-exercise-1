package co.daniel.springboot.app.item.clientes;

import co.daniel.springboot.app.item.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
    @GetMapping("/listar")
    List<Producto> listar();

    @GetMapping("/ver/{id}")
    Producto ver(@PathVariable Long id);

    @PostMapping("/crear")
    public Producto crear(@RequestBody Producto producto);

    @PutMapping("/editar/{id}")
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id);

}

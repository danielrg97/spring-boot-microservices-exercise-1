package co.daniel.springboot.app.item.controller;

import co.daniel.springboot.app.item.models.Item;
import co.daniel.springboot.app.item.models.Producto;
import co.daniel.springboot.app.item.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {
    private static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @Value("${configuracion.texto}")
    private String texto;

    @GetMapping("/listar")
    public List<Item> listar() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item ver(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad){
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("fallback");
        producto.setPrecio(500.0);
        item.setProducto(producto);
        return item;
    }
    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
        LOGGER.info(texto);
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);
        if(environment.getActiveProfiles().length>0 && environment.getActiveProfiles()[0].equals("dev"))
            json.put("nombre", environment.getProperty("configuracion.autor.nombre"));
            json.put("email", environment.getProperty("configuracion.autor.email"));
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
}

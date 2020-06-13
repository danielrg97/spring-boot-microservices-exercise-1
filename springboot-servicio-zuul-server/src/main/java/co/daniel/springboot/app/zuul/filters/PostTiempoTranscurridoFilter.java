package co.daniel.springboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

    @Override
    public String filterType() {
        //Se debe usar las palabras claves pre, post o route
        return "post";
    }

    @Override
    public int filterOrder() {
        //Orden de ejcucion
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //Determina si vamos a ejecutar o no el metodo run, condicional
        //True hace que siempre se ejecute
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //Logica del filtro
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info("Entrando a Post");
        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
        LOGGER.info(String.format("Tiempo transcurrido en segundos: %s", tiempoTranscurrido.doubleValue()/1000.00));
        LOGGER.info(String.format("Tiempo transcurrido en mili: %s", tiempoTranscurrido));
        return null;
    }
}

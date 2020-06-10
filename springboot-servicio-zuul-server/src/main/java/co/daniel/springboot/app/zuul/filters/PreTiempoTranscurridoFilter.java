package co.daniel.springboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

    @Override
    public String filterType() {
        //Se debe usar las palabras claves pre, post o route
        return "pre";
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
        LOGGER.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
        Long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);
        return null;
    }
}

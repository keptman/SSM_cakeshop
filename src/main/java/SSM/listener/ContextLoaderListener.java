package SSM.listener;
import SSM.domain.Type;
import SSM.service.TypeService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContextLoaderListener{
    @Resource(name = "typeService")
    private TypeService typeService;

    @Resource(name = "servletContext")
    private ServletContext servletContext;

    @EventListener(ContextRefreshedEvent.class)
    public void setTypeService(){
        List<Type> typeList = typeService.selectAll();
        servletContext.setAttribute("typeList",typeList);
    }
}
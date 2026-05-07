package hiber.service;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private final InitService initService;

    public StartupListener(InitService initService) {
        this.initService = initService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //  один раз для корня
        if (event.getApplicationContext().getParent() == null) {
            initService.init();
        }
//  //  public void onApplicationEvent(ContextRefreshedEvent event) {
//        initService.init();
//    }

    }
}